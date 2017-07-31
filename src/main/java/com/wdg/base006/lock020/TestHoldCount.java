package com.wdg.base006.lock020;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangdg
 * @Description: ReentrantLock API
 * @date 2017-06-11 00:50:22
 */
public class TestHoldCount {

	private ReentrantLock lock = new ReentrantLock();

	public void m1() {
		try {

			lock.lock();
			System.out.println("m1方法:" + lock.getHoldCount());
			// 调用m2方法
			m2();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void m2() {
		try {
			lock.lock();
			System.out.println("m2方法:" + lock.getHoldCount());
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

    public static void main(String[] args) {
        TestHoldCount holdCount = new TestHoldCount();
        holdCount.m1();
    }
}
