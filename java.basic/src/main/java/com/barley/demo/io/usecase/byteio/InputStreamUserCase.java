package com.barley.demo.io.usecase.byteio;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PushbackInputStream;
import java.io.SequenceInputStream;
import java.io.StringBufferInputStream;

/**
 * 字节流 + 字节流包裹流
 * 
 * 
 * 
 * 
 * @see https://www.cnblogs.com/CQqf/p/10795656.html
 * 
 * @author peculiar.1@163.com
 * @version $ID: UserCase.java, V1.0.0 2020年11月28日 下午12:20:54 $
 */

public class InputStreamUserCase {

	public static final String fileName = "D:\\zsqworkspace\\personal\\java.basic\\src\\main\\resources\\usernames";
	public static final String alphabet = "D:\\zsqworkspace\\personal\\java.basic\\src\\main\\resources\\alphabet";

	public static void main(String[] args) throws Exception {
		// testFileInputStream();
		// testFileInputStream1();
		// testBufferedInputStream();
		// testBufferedInputStream1();
		// testDataInputStream();
		// testPushbakInputStream();
		// testObjectInputStream();
		// testPipedInputStream();
		testSequenceInputStream();

	}

	public static void printByteArray(byte[] readBuf, int readByteSize) {
		for (int j = 0; j < readByteSize; j++) {// 不能读取全部
			System.out.print(readBuf[j] + ",");
		}
	}

	/**
	 * 文件读取流<br>
	 * 中文乱码,因为是单字节读取方式
	 * 
	 */
	public static void testFileInputStream(File file) throws Exception {
		System.out.println("start testFileInputStream");
		FileInputStream io;
		if(file == null) {
			io = new FileInputStream(fileName);
		}else {
			io = new FileInputStream(file);
		}
		int b;
		try {
			while ((b = io.read()) != -1) {
				System.out.println(b);
			}
		} finally {
			// 需要关闭
			// 不关闭不影响读取，但是影响后续写入动作
			io.close();
		}
	}

	/**
	 * 缓冲区
	 * 
	 * 回车，ASCII码13 换行，ASCII码10 空格，ASCII码32 换行字节中表示为 1310<br>
	 * 回车+换行 \r(return)\n(new line?)
	 * 
	 * @throws Exception
	 */
	public static void testFileInputStream1(File file) throws Exception {
		System.out.println("start testFileInputStream1");
		FileInputStream io;
		if(file == null) {
			io = new FileInputStream(fileName);
		}else {
			io = new FileInputStream(file);
		}
		
		try {
			byte[] buffer = new byte[2];
			while (io.read(buffer) != -1) {
				System.out.println(new String(buffer));
			}
		} finally {
			// 需要关闭
			io.close();
		}
	}

	/**
	 * @类型 属于字符流包裹流，调用自身的close,会自动关闭包裹的流
	 * 
	 * @描述 带有缓冲区的字节读入 默认缓冲区大小 8192 bit = 8K （印象中和NIO的channel管道特别像）
	 * 
	 * @特性 提供了缓冲区 <br>
	 *     buf ： 缓冲区数组 <br>
	 *     count ：当前缓冲区可读字节数 <br>
	 *     pos ：当前位置，相对于缓冲区数组的下标位置 <br>
	 *     markpos ：重复读取标记下标 <br>
	 *     marklimit：从标记下标开始,只能读取marklimit长度数据,后markpos将失效。<br>
	 * 
	 */
	public static void testBufferedInputStream() throws Exception {
		System.out.println("start testBufferedInputStream");
		FileInputStream fin = new FileInputStream(alphabet);

		// 缓冲区大小设置为5个字节
		BufferedInputStream io = new BufferedInputStream(fin, 5);
		int b;
		// ===============================
		// 基本读取每次读取1个字节
		// ===============================
		while ((b = io.read()) != -1) {
			char cr = (char) b;
			System.out.println(cr);
			if ('b' == cr) { // b之后三个字节不输出
				io.skip(3);
			}
		}

		io.close();

	}

	/**
	 * @特性 mark & reset
	 * 
	 * @背景 在读取数据的时候，在缓存区数组中标记一个下标，后续读取过程可能会利用reset重新设置当前位置重新读取 <BR>
	 * @限制 缓冲区只有一个,而且还有大小限制,做不到能无限保留之前的数据,无法全部放入内存. 所以只能在一定的场景下支持reset.<BR>
	 * @方案 在缓存取需要重新填充的时候,保留因为mark想保留下来的数据,继续存留在缓存中.
	 * 
	 * @throws Exception
	 */
	public static void testBufferedInputStream1() throws Exception {
		// ===========================
		// 缓冲区20, 每次读取10字节,利用mark+reset两次读取11-20之间的字节
		// ===========================
		System.out.println("start testBufferedInputStream1");
		byte[] buf = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
				25, 26, 27 };
		// FileInputStream fin = new FileInputStream(alphabet);
		ByteArrayInputStream bin = new ByteArrayInputStream(buf);
		BufferedInputStream io = new BufferedInputStream(bin, 20);
		byte readBuf[] = new byte[4];
		int readByteSize;
		int i = 0;

		while ((readByteSize = io.read(readBuf)) != -1) {
			/*
			 * for (int j = 0; j < readByteSize; j++) {// 不能读取全部
			 * System.out.print(readBuf[j]+","); }
			 */
			printByteArray(readBuf, readByteSize);
			System.out.println();
			// 标记当前位置
			if (i == 0) {
				io.mark(5); // 读取超出最大值(limit & 缓存最大容量)后无法reset.
			}

			if (i++ == 1) {
				System.out.println("[TIP] 数据接收失败，要求重新读取");
				io.reset();
			}
		}
		io.close();
	}

	/**
	 * @type java对象流 <BR>
	 * 
	 * @描述 数据输入流允许应用程序以独立于机器的方式从底层输入流读取原始Java数据类型。 应用程序使用数据输出流来写入稍后可以被数据输入流读取的数据。
	 * 
	 * @see DataInputStream
	 */
	public static void testDataInputStream() throws Exception {
		System.out.println("start testDataInputStream");
		String str = "123456789true";
		byte[] bytes = str.getBytes();
		for (byte b : bytes) {
			System.out.print(b + "-");
		}
		System.out.println("");
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(str.getBytes()));
		/*
		 * 返回四个字节的int值 000110001,00110010,00110011,00110100 8位二静止拼接起来 = 十进制 825373492
		 */
		System.out.println(in.readInt());
		System.out.println(in.readChar());

	}

	/**
	 * 
	 * @类型 回退流
	 * @特性 允许数据回退 unread <br>
	 *     存在缓冲区
	 * 
	 */
	public static void testPushbakInputStream() throws Exception {
		System.out.println("start testPushbakInputStream");
		byte[] buf = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
				25, 26, 27 };
		ByteArrayInputStream bin = new ByteArrayInputStream(buf);
		PushbackInputStream in = new PushbackInputStream(bin, 20);

		byte[] unbuf = new byte[] { 'A', 'B' };// 65,66
		in.unread(unbuf);

		byte[] read = new byte[2];

		int readSize = in.read(read);
		printByteArray(read, readSize);

		int j = 0;
		while ((readSize = in.read(read)) != -1) {
			printByteArray(read, readSize);
			if (j++ == 2) {
				in.unread(new byte[] { 'C', 'D' });// 67,68
			}
		}

		in.close();
	}

	/**
	 * 主要负责java对象的序列化存储,这样就可以达到远程虚拟机之间的沟通。
	 * 
	 * @特性 java对象序列化
	 * @see ObjectInputStream
	 */
	public static void testObjectInputStream() throws Exception {

		User user = new User();
		user.setUsername("张力");
		user.setPassword("123123");
		File file = new File("user");
		System.out.println(file.getAbsolutePath());
		// writer
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		os.writeObject(user);
		os.close();
		// read
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
		User readUser = (User) ois.readObject();
		System.out.println(readUser);
		ois.close();

		// 如果流不关闭,文件会删除失败
		file.delete();
	}

	/**
	 * 
	 * @type 管道流
	 * @特性  负责对接流输入,就好像一个管道一样,将一个数据流,转移到另外一个流中 (多线程场景)<BR>
	 * 		 存在数据缓冲区<BR>
	 *      
	 * @see PipedInputStream
	 * @throws Exception
	 */
	public static void testPipedInputStream() throws Exception {
		final PipedOutputStream pos = new PipedOutputStream();
		final PipedInputStream pis = new PipedInputStream(pos, 20);
		
		new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("thread1 start");
					byte read[] = new byte[5];
					int readSize;
					while ((readSize = pis.read(read)) != -1) {
						printByteArray(read, readSize);
					}
					System.out.println("");
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					try {
						pis.close();
						System.out.println("PIS close");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
				}
			}
		},"Thread1").start();
		
		new Thread(new Runnable() {
			public void run()  {
				try {
					System.out.println("thread2 start");
					byte[] buf = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
							25, 26, 27 };
					
					int off = 0;
					do {
						int avil =  5;
						if(off + avil > buf.length) {
							avil = buf.length - off;
						}
						pos.write(buf, off, avil);
						off += avil;
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {

						}
					} while (off < buf.length);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						pos.close();
						System.out.println("");
						System.out.println("pos close");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		},"Thread2").start();
		
		Thread.sleep(5 * 3600);
	}
	
	
	/**
	 * 
	 * @see SequenceInputStream
	 * @特性  有序合并多个输入流 
	 */
	public static void testSequenceInputStream() throws Exception{
		byte[] buf1 = new byte[] { 17, 18, 19, 20, 21, 22, 23, 24,
				25, 26, 27 };
		byte[] buf2 = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
		SequenceInputStream sio = new SequenceInputStream(new ByteArrayInputStream(buf1), new ByteArrayInputStream(buf2));
		byte read[] = new byte[5];
		int readSize;
		while ((readSize = sio.read(read)) != -1) {
			printByteArray(read, readSize);
		}
		sio.close();
	}
	
	
	/**
	 已经废弃
	 * @see StringBufferInputStream
	 */
	public static void testStringBufferInputStream() {
	}
	
	/**
	 * @see ByteArrayInputStream
	 */
	public static void testByteArrayInputStream() {
		
	}
}
