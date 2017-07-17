package com.wdg.base.sync003;

/**
 * @author wangdg
 * @ClassName: 对象锁的同步和异步问题
 * @Description: MyObject
 * @date 2017-06-11 00:50:22
 */
public class MyObject {

    public synchronized  void method1(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    public void method2(){
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        final MyObject mo = new MyObject();
        /**
         * 执行结果: t2   t1
         * 分析:
         *      t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
         *      t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
         */
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                mo.method1();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                mo.method2();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
