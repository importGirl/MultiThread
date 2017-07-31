package com.wdg.base006.lock020;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class UseReentrantLock {

	private Lock lock = new ReentrantLock();

	public void m1() {
		try {
			lock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入m1");
            Thread.sleep(2000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出m1");
        } catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
		    lock.unlock();
        }

	}

	public void m2(){
        try {
            lock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入m2");
            Thread.sleep(2000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出m2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.lock();
        }
    }

    public static void main(String[] args) {
        /**
         * 加锁机制: m1获得锁,m2阻塞,m1释放锁.m2获得锁,m2释放锁
         */
        final UseReentrantLock urtl = new UseReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                urtl.m1();
                urtl.m2();
            }
        }).start();
    }


}


















