package com.wdg.base005.concurrent019;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangdg
 * @Description: CountDownLatch API
 * @date 2017-06-11 00:50:22
 */
public class UseCountDownLatch {

    public static void main(String[] args) {
        /**
         * 执行结果: 满足2个countDown(),才会唤醒await()
         * 分析:
         *          一个线程阻塞等待多个线程瞒住要求
         */
        CountDownLatch countDown = new CountDownLatch(2);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("进入t1线程" + "等待其他线程处理完成");
                try {
                    countDown.await();
                    System.out.println("t1线程继续执行...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2线程初始化操作开始");
                try {
                    Thread.sleep(3000);
                    countDown.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t3线程初始化操作开始");
                try {
                    Thread.sleep(3000);
                    countDown.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
