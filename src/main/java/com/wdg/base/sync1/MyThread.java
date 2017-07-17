package com.wdg.base.sync1;

/**
 * @author wangdg
 * @ClassName: 
 * @Description: 模拟多线程并发
 * @date 2017-06-11 00:50:22
 */
public class MyThread extends Thread {

	private int count = 5;

	@Override
	public void run() {
	    count--;
        System.out.println(Thread.currentThread().getName() + " count:" + count);
    }

    public static void main(String[] args) {
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
