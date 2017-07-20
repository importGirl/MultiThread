package com.wdg.base002.conn008;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangdg
 * @Description: 使用notify /wait
 * @date 2017-06-11 00:50:22
 */
public class ListAdd2 {

	private static volatile List list = new ArrayList<String>();

	public void add() {
		list.add("ddd");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		final ListAdd2 list2 = new ListAdd2();
		final Object lock = new Object();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        /**
         * 执行结果: 使用notify/wait t2线程会在size=5时唤醒,但是出于阻塞状态,只有当
         *           t1执行完任务释放了锁资源,t2拿到锁才会去执行下面的逻辑
         * 分析:
         *           wait会释放当前线程的锁,notify不会释放当前线程的锁
         *
         * 执行结果: 使用countDownLatch 线程在size=5时,计数器的值为0,t2可以恢复运行,
         *           t2执行后面的逻辑
         * 分析:      countDownLatch不是通过锁来限制,t2的运行的,而是使用一个计数器,
         *            1代表1个线程调用了countDown()之后,计数变为0,此时去启动t2线程
         *            t2线程时立即执行
         */
		Thread t1 = new Thread(new Runnable() {
			public void run() {
//				synchronized (lock) {
					for (int i = 0; i < 10; i++) {
						list2.add();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
							e.printStackTrace();
                        }
                        System.out.println("当前线程"
								+ Thread.currentThread().getName() + "开始");
						if (list2.size() == 5) {
							System.out.println("已经发出通知");
//							lock.notify();
                            countDownLatch.countDown();// 计数减一
						}
					}
				}
//			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			public void run() {
//				synchronized (lock) {
					if (list2.size() != 5) {
						System.out.println("t2进入等待");
						try {
//							lock.wait();
                            countDownLatch.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("t2停止");
					throw new RuntimeException();
				}
//			}

		}, "t2");

		t2.start();
		t1.start();

	}
}
