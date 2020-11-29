package com.barley.demo.buffer;

/**
 * 字节数字相关API
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: BufferUseCase.java, V1.0.0 2020年11月28日 下午2:10:14 $
 */
public class BufferUseCase {

	public static void main(String[] args) {
		copyArray();
	}

	/**
	 * 字节叔祖相关copy 操作
	 */
	public static void copyArray() {
		byte byteAy[] = new byte[] { 'A', 65, 66, 1, 1, 2};
		byte byteAy1[] = new byte[byteAy.length];
		
		/*
		 * 从原数组srcPos copy length 到 destPos, 从destPos 位置开始写入
		 */
		System.arraycopy(byteAy, 0, byteAy1, 1, 2);
		System.out.println(new String(byteAy1));
		
		/*
		 * 使用index从2开始2个字节(2-4)覆盖0-2 
		 */
		System.arraycopy(byteAy, 2, byteAy, 0, 2);
		System.out.println(new String(byteAy));
	}
}
