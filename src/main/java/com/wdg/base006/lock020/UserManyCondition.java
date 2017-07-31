package com.wdg.base006.lock020;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangdg
 * @Description: Condition API
 * @date 2017-06-11 00:50:22
 */
public class UserManyCondition {

	private ReentrantLock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();

	public void m1() {
		try {
			lock.lock();
			System.out.println("当前线程" + Thread.currentThread().getName()
					+ " 执行了...");
			c1.await();
			System.out.println("当前线程:" + Thread.currentThread().getName()
					+ " m1继续");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void m2() {
		try {
			lock.lock();
			System.out.println("当前线程" + Thread.currentThread().getName()
					+ " 执行了...");
			c1.await();
			System.out.println("当前线程:" + Thread.currentThread().getName()
					+ " m2继续");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void m3() {
		try {
			lock.lock();
			System.out.println("当前线程" + Thread.currentThread().getName()
					+ " 执行了...");
			c2.await();
			System.out.println("当前线程:" + Thread.currentThread().getName()
					+ " m3继续");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void m4() {
		try {
			lock.lock();
			System.out.println("当前线程" + Thread.currentThread().getName()
					+ " 唤醒...");
			c1.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            lock.unlock();
        }
    }

	public void m5() {
		try {
			lock.lock();
			System.out.println("当前线程" + Thread.currentThread().getName()
					+ " 唤醒...");
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            lock.unlock();
        }
    }

	public static void main(String[] args) {
		UserManyCondition user = new UserManyCondition();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				user.m1();
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				user.m2();
			}
		}, "t2");
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				user.m3();
			}
		}, "t3");
		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				user.m4();
			}
		}, "t4");
		Thread t5 = new Thread(new Runnable() {
			@Override
			public void run() {
				user.m5();
			}
		}, "t5");

		t1.start();
		t2.start();
		t3.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t4.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t5.start();
	}

}
