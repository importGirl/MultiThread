package com.wdg.base006.lock021;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wangdg
 * @Description: 读写锁
 * @date 2017-06-11 00:50:22
 */
public class UseReentrantReadWriteLock {

	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	private ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
	private ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

	public void m1() {
		try {
			readLock.lock();
			System.out.println("当前线程: " + Thread.currentThread().getName()
					+ "进入");
			Thread.sleep(2000);
            System.out.println("当前线程: " + Thread.currentThread().getName()
                    + "退出...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
		    readLock.unlock();
        }
	}

	public void m2(){
        try {
            writeLock.lock();
            System.out.println("当前线程: " + Thread.currentThread().getName()
                    + "进入");
            Thread.sleep(2000);
            System.out.println("当前线程: " + Thread.currentThread().getName()
                    + "退出...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        /**
         * 分析:读读共享,写写互斥,读写互斥
         * 核心: 实现读写分离的锁
         * 应用场景: 读多写少的情况
         */
        final UseReentrantReadWriteLock rwl = new UseReentrantReadWriteLock();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                rwl.m1();
//            }
//        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                rwl.m1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                rwl.m2();
            }
        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                rwl.m2();
//            }
//        }).start();
    }
}
