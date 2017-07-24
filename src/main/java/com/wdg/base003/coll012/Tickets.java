package com.wdg.base003.coll012;

import java.util.Vector;

/**
 * @author wangdg
 * @Description: 多线程使用Vector或者HashTable的示例(简单线程同步问题)
 * @date 2017-06-11 00:50:22
 */
public class Tickets {

	public static void main(String[] args) {

		// 线程安全集合
		final Vector tickets = new Vector<String>();

		// 火车票
		for (int i = 0; i < 1000; i++) {
			tickets.add("火车票" + i);
		}

		// 买火车票
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						if (tickets.isEmpty()) {
							break;
						}
						System.out.println(Thread.currentThread().getName()
								+ "----" + tickets.remove(0));
					}
				}
			}, "线程" + i).start();
		}
	}

}
