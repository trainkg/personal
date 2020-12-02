package com.barley.demo.collection.usecase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * java 元素list家族测试
 * 
 * @see List
 * @feature 1. 有序 <BR>
 *          2. 自动扩容
 * 
 * @author peculiar.1@163.com
 * @version $ID: ListTests.java, V1.0.0 2020年12月1日 下午8:06:21 $
 */
public class ListTests {

	public static void main(String[] args) {
		// testArrayList();
		// testCopyOnWriteArrayList();
		// testLinkedList();
		testStack();
	}

	/**
	 * @Description 可调整大小的数组的实现List接口。 实现所有可选列表操作，并允许所有元素，包括null 。 除了实现List
	 *              接口之外，该类还提供了一些方法来操纵内部使用的存储列表的数组的大小。 <BR>
	 *              （这个类是大致相当于Vector，不同之处在于它是不同步的）。
	 * 
	 * @feature ensureCapacity 有初始容量, 设计的容量越接近真实值,越可以减少扩容的次数,减少消耗
	 * @feature 非线程安全、
	 * @feature 当执行迭代器时候，如果修改元素将会立刻失败
	 */
	public static void testArrayList() {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add("A");
		list.add("B");
		list.add("C");
		// 常规 add,remove,get,indexof不再示例

		ListIterator<Object> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			System.out.println("listIterator.hasPrevious()" + listIterator.hasPrevious());
			Object object = (Object) listIterator.next();
			System.out.println(object);
		}
		System.out.println("---------------------------CUT LINE-------------------------------");
		listIterator = list.listIterator(1);
		while (listIterator.hasNext()) {
			System.out.println("listIterator.hasPrevious()" + listIterator.hasPrevious());
			Object object = (Object) listIterator.next();
			System.out.println(object);
		}
		System.out.println("---------------------------CUT LINE-------------------------------");
		// 调整list容量
		list.trimToSize();
	}

	/**
	 * @see CopyOnWriteArrayList
	 * @feature 线程安全
	 * @feature 所有可变操作（ add ， set ，等等）通过对底层数组的最新副本实现))
	 * 
	 */
	public static void testCopyOnWriteArrayList() {
		CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>();
		String str = "a";
		String str1 = "testing";

		list.add(str);
		list.add(str1); // 数组容量会实时调整 + 1
		list.remove(1);
		System.out.println(str.equals(list.get(0)));
		System.out.println(str1.equals(list.get(1)));
	}

	/**
	 * 
	 * @feature 双链表实现了List和Deque接口。 实现所有可选列表操作
	 * @feature 非线程安全 可以使用 Collections.synchronizedList 转变为线程安全
	 * @feature 链表数据类型 头和尾
	 */
	public static void testLinkedList() {
		LinkedList<String> list = new LinkedList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");

		System.out.println(list.getFirst()); // 如果为空会有异常
		System.out.println(list.getLast());
		list.offerFirst("-1");
		list.offerLast("-2");

		System.out.println(list.peek());// 如果为空没有异常

		list.peekFirst(); // == peek
		list.peekLast();

		list.poll();// 检索并删除此列表的头（第一个元素）
	}

	/**
	 * @feature 线程安全数组,如果不需要线程安全的实现，建议使用ArrayList代替Vector 。
	 */
	public static void testVector() {

	}

	/**
	 * @feature Stack类代表最先进先出（LIFO）堆栈的对象
	 */
	public static void testStack() {
		Stack<String> stack = new Stack<String>();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.pop(); // 弹出C
		System.out.println(stack.peek());// 输出B
		stack.capacity();
		stack.search("A");

		System.out.println(stack);

	}

}
