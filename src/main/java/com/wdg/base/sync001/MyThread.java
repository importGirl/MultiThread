package com.wdg.base.sync001;

/**
 * @author wangdg
 * @ClassName: 
 * @Description: 模拟多线程并发
 * @date 2017-06-11 00:50:22
 */
public class MyThread extends Thread {

	private int count = 5;

	@Override
	public synchronized void run() {
		count--;
		System.out
				.println(Thread.currentThread().getName() + " count:" + count);
	}

	public static void main(String[] args) {
		/**
		 * 期望结果: 5 4 3 2 1
		 * 打印结果: 2 2 2 1 0
		 * synchronized: 5 4 3 2 1
		 * 
		 * 分析：当多个线程访问myThread的run方法时，以排队的方式进行处理（这里排对是按照CPU分配的先后顺序而定的），
		 * 		一个线程想要执行synchronized修饰的方法里的代码：
		 *      1 尝试获得锁
         * 		2 如果拿到锁，执行synchronized代码体内容；拿不到锁，这个线程就会不断的尝试获得这把锁，直到拿到为止，
         * 		   而且是多个线程同时去竞争这把锁。（也就是会有锁竞争的问题）
		 */
		MyThread myThread = new MyThread();
		Thread t1 = new Thread(myThread, "t1");// 创建多条线程
		Thread t2 = new Thread(myThread, "t2");
		Thread t3 = new Thread(myThread, "t3");
		Thread t4 = new Thread(myThread, "t4");
		Thread t5 = new Thread(myThread, "t5");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}
