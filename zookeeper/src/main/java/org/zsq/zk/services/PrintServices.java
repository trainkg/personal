package org.zsq.zk.services;

public class PrintServices implements Print {

	public void print() {
		System.out.println(Thread.currentThread().getName() + " print ...");
	}

}
