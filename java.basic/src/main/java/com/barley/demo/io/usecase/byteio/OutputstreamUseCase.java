package com.barley.demo.io.usecase.byteio;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;

/**
 * 字节输出流
 * 
 * @author peculiar.1@163.com
 * @version $ID: OutputstreamUseCase.java, V1.0.0 2020年11月28日 下午10:16:08 $
 */
public class OutputstreamUseCase {

	public static void main(String[] args) throws Exception {
		// testFileOutputstream();
		// testBufferedOutputStream();
		// testPrintStream();
		// testObjectOutputStream();
		testByteArrayOutputStream();
	}

	/**
	 * @see FileOutputStream
	 * @特性 支持文件的写入
	 */
	public static void testFileOutputstream() throws Exception {
		System.out.println("start testFileOutputstream");
		File file = new File("testFile");
		System.out.println(file.getAbsolutePath());
		FileOutputStream fos = new FileOutputStream(file);
		byte[] buf = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
				25, 26, 27 };
		fos.write(buf);
		fos.close();

		InputStreamUserCase.testFileInputStream(file);
		file.delete();
	}

	/**
	 * 在写入提供一个缓冲区,减少硬件IO操作次数
	 * 
	 * @see BufferedOutputStream
	 */
	public static void testBufferedOutputStream() throws Exception {
		File file = new File("testFile");
		// 1K的缓冲区
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file), 20);
		byte[] buf = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
				25, 26, 27 };
		bos.write(buf);
		bos.close();

		InputStreamUserCase.testFileInputStream(file);
		file.delete();
	}

	/**
	 * @see PrintStream
	 * @特性 支持字符集设定
	 */
	public static void testPrintStream() throws Exception {
		PrintStream ps = new PrintStream(System.out, true, "UTF-8");
		ps.write(new String("hello word, 你好,我的世界").getBytes());
		ps.close();

		ps = new PrintStream(new FileOutputStream(new File("testFile")), true, "UTF-8");
		ps.write(new String("triankg, 个人测试数据").getBytes());
		ps.close();
	}

	/**
	 * JAVA 对象序列化
	 * 
	 * @see ObjectOutputStream
	 */
	public static void testObjectOutputStream() throws Exception {
		ObjectOutputStream os = new ObjectOutputStream(System.out);
		os.writeBoolean(true);
		os.writeBoolean(false);
		os.writeChars("abcd");
		os.close();
	}

	/**
	 * @描述 内存数据流, close没有实际效果
	 * 
	 * @特性 提供了缓冲区, 利用缓冲区进行持续写入, 并且缓冲区有自动扩容能力
	 * 
	 * @see ByteArrayOutputStream
	 * @throws Exception
	 */
	public static void testByteArrayOutputStream() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(20);
		byte[] buf = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
				25, 26, 27 };
		
		//初始缓冲区大小不够,缓冲区扩容变为40, 
		bos.write(buf);
		bos.reset();
		//缓冲区大小保持为40, count为4(实际有效字节, 实际缓冲区长度大于4)
		buf = new byte[] { 'A', 'B', 'C', 'D' };
		bos.write(buf);
		
		byte[] bytes = bos.toByteArray(); //获取最新的缓冲区有效字节数据(count)
		InputStreamUserCase.printByteArray(bytes, bytes.length);
	}
}
