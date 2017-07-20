package com.wdg.base002.conn008;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangdg
 * @Description: 模拟notify/wait
 * @date 2017-06-11 00:50:22
 */
public class ListAdd1 {
	private volatile static List<String> list = new ArrayList<String>();

	public void add() {
		list.add("dddd");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		final ListAdd1 list1 = new ListAdd1();
        /**
         * 执行结果: t2线程不断去监听,list集合的长度,当==5时,中断t2,t1继续执行完任务停止
         * 分析:
         *          该场景是没有使用notify,wait来实现监听某个线程的变化,使用的while
         */
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {

					list1.add();
					System.out.println(Thread.currentThread().getName() + "启动");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (list1.size() == 5) {
						System.out.println("当前线程收到通知:"
								+ Thread.currentThread().getName());
                        throw new RuntimeException();
                    }
				}
			}
		}, "t2");

		t1.start();
		t2.start();
	}

}
