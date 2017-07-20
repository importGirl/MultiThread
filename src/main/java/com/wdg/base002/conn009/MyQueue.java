package com.wdg.base002.conn009;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangdg
 * @Description: 模拟ArrayBlockQueue队列(有界阻塞队列)
 * @date 2017-06-11 00:50:22
 */
public class MyQueue {

	// 需要一个集合
	private static LinkedList list = new LinkedList();
	// 计数器
	private AtomicInteger count = new AtomicInteger(0);
	// 队列下限
	private int minSize = 0;
	// 队列上限
	private int maxSize;

	// 构造方法
	public MyQueue(int maxSize) {
		this.maxSize = maxSize;
	}

	// 初始化锁对象
	final Object lock = new Object();

	// put方法
	public void put(Object obj) {
		synchronized (lock) {
			if (count.get() == maxSize) {
				System.out.println("队列已满,请等待~~~");
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("队列放入任务:" + obj);
			// 如果队列 没满,则放到集合中
			list.add(obj);
			count.addAndGet(1);// 计数器累加
			lock.notify();
		}
	}

	// take方法
	public Object take() {
		synchronized (lock) {
			if (count.get() == minSize) {
				System.out.println("队列为空,请等待~~~");
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Object ret = list.removeFirst();
			count.decrementAndGet();
			lock.notify();
			return ret;
		}
	}

	public int getSize() {
		return count.get();
	}

	public static void main(String[] args) {
		final MyQueue mq = new MyQueue(5);
		mq.put("1");
		mq.put("2");
		mq.put("3");
		mq.put("4");
		mq.put("5");
		System.out.println("当前容器长度: " + mq.getSize());

		/**
		 * 执行结果: 放入元素达到5时,该集合进入阻塞状态,等到取出集合的元素,换线等待的线程,放入元素
		 * 分析:     有界阻塞队列的模拟,联想消息队列,有助于学习消息队列
		 */
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				mq.put("6");
				mq.put("7");
			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				Object m1 = mq.take();
				System.out.println("队列取出任务: " + m1);
				Object m2 = mq.take();
				System.out.println("队列取出任务: " + m2);
			}
		}, "t2");

		t1.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
	}
}
