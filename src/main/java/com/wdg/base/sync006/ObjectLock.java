package com.wdg.base.sync006;

/**
 * @author wangdg
 * @ClassName: ObjectLock
 * @Description: 代码块加锁
 * @date 2017-06-11 00:50:22
 */
public class ObjectLock {


    public void method1(){
        synchronized (this){ // 对象锁
            try {
                System.out.println("do method1...");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2(){
        synchronized (ObjectLock.class){ // 类锁
            System.out.println("do method2 ...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    Object object = new Object();
    public void method3(){ // 任何对象锁
        synchronized (object){
            System.out.println("do method3 ...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ObjectLock objectLock = new ObjectLock();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                objectLock.method1();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                objectLock.method2();
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            public void run() {
                objectLock.method3();
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
