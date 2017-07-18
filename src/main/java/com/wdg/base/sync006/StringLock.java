package com.wdg.base.sync006;

/**
 * @author wangdg
 * @ClassName: StringLock
 * @Description: 字符串的锁,实践中尽量不要使用字符串锁,因为会是同步阻塞的,
 * @date 2017-06-11 00:50:22
 */
public class StringLock {

    public void method(){
        synchronized ("字符串常量"){
            while(true){
                System.out.println("当前线程: " + Thread.currentThread().getName() + "开始");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前线程: " + Thread.currentThread().getName() + "结束");
            }
        }
    }

    public static void main(String[] args) {
        final StringLock stringLock = new StringLock();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                stringLock.method();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                stringLock.method();
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
