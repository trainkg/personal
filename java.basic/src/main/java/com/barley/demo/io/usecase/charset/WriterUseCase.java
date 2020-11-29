package com.barley.demo.io.usecase.charset;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.InputStreamReader;
import java.io.PipedReader;
import java.io.PushbackReader;
import java.io.StringReader;

import com.barley.demo.io.usecase.byteio.InputStreamUserCase;

/**
 * 
 * @描述 字符输入流 <BR>
 *     字符流和 字节流的区别在与字符流每次读取是读取一个char, 字符流是读取一个byte.
 * 
 * @author peculiar.1@163.com
 * @version $ID: WriterUseCase.java, V1.0.0 2020年11月29日 下午1:17:20 $
 */
public class WriterUseCase {

	public static void main(String[] args) throws Exception {
		// testBufferedRead();
		// testStringRead();
		// testCharArrayReader();
		testPushBackReader();
	}

	/**
	 * @see BufferedReader
	 * @特性 缓冲区
	 *     <ul>
	 *     <li>reset</li>
	 *     <li>skip</li>
	 *     </ul>
	 */
	public static void testBufferedRead() throws Exception {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in, "UTF-8"), 1024);
		String str;
		int i = 0;
		while ((str = read.readLine()) != null) {
			System.out.println(str);
			if ("mark".equals(str)) {
				read.mark(10);
			}

			// 只reset一次, mark 后输入字符不能超过5个, 5 + reset = 10
			if (i == 0 && "reset".equals(str)) {
				read.reset();
				i++;
			}
		}
	}

	/**
	 * @描述 需要和 {@link BufferedReader}联合使用, 负责性能改进, {@link InputStreamReader} 负责字符集支持
	 * 
	 * 
	 * @特性 字符集设定
	 * @see InputStreamReader
	 * @see #testBufferedRead
	 * @throws Exception
	 */
	public static void testInputStreamReader() throws Exception {
	}

	/**
	 * 
	 * @see StringReader
	 * @throws Exception
	 */
	public static void testStringRead() throws Exception {
		StringReader rs = new StringReader("我是一个用于测试的字符串");
		char[] bytes = new char[2];
		rs.read(bytes);
		System.out.println(bytes);
		rs.skip(1); // 跳过了一
		rs.read(bytes);
		System.out.println(bytes);
		rs.mark(2); // 2并没有实际限制作用,只要不是负数就可以
		rs.read(bytes);
		System.out.println(bytes);
		rs.read(bytes);
		System.out.println(bytes);
		// 两次读取了4个char,但是还是可以继续
		rs.reset();
		rs.read(bytes);
		System.out.println(bytes);
	}

	/**
	 * @set {@link PipedReader}
	 * @SEE {@link InputStreamUserCase#testPipedInputStream()}
	 */
	public static void testPipedReader() {
	}

	/**
	 * @see CharArrayReader
	 * @throws Exception
	 */
	public static void testCharArrayReader() throws Exception {
		CharArrayReader reader = new CharArrayReader("我是一个好人".toCharArray());
		System.out.println((char) reader.read());
	}

	/**
	 * @see PushbackReader
	 * @throws Exception
	 */
	public static void testPushBackReader() throws Exception {
		PushbackReader reader = new PushbackReader(new InputStreamReader(System.in), 20);
		String str;
		char[] chars = new char[10];
		int size;
		while ((size = reader.read(chars)) != -1) {
			str = new String(chars, 0, size - 2);// 扣除回车+换行
			System.out.println(str);
			if ("in".equals(str)) {
				reader.unread("中国人民解放军第三空军旅团系五二座".toCharArray());
				// reader.unread("abcdce".toCharArray()); -- 长度不能超过缓存长度
			}
		}
	}
}
