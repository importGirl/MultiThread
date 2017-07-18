package com.wdg.base.sync006;

/**
 * @author wangdg
 * @ClassName: DeadLock
 * @Description: 死锁
 * @date 2017-06-11 00:50:22
 */
public class DeadLock implements Runnable {

	private String tag;
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void run() {
		if (tag.equals("a")) {
			synchronized (lock1) {
				System.out.println("当前线程" + Thread.currentThread().getName()
						+ "开始");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock2) {
					System.out.println("当前线程: "
							+ Thread.currentThread().getName() + "结束");
				}
			}

		}
		if (tag.equals("b")) {
			synchronized (lock2) {
				System.out.println("当前线程" + Thread.currentThread().getName()
						+ "开始");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock1) {
					System.out.println("当前线程 :"
							+ Thread.currentThread().getName() + "结束");
				}
			}
		}
	}

	public static void main(String[] args) {
		DeadLock d1 = new DeadLock();
		d1.setTag("a");
		DeadLock d2 = new DeadLock();
		d2.setTag("b");

		Thread t1 = new Thread(d1, "t1");
		Thread t2 = new Thread(d2, "t2");
		t1.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}
}
