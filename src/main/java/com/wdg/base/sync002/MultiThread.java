package com.wdg.base.sync002;

/**
 * @author wangdg
 * @ClassName: MultiThread
 * @Description: 
 *      关键字synchronized取得的锁都是对象锁，而不是把一段代码（方法）当做锁，
 *  所以代码中哪个线程先执行synchronized关键字的方法，哪个线程就持有该方法所属对象的锁（Lock），
 *  在静态方法上加synchronized关键字，表示锁定.class类，类一级别的锁（独占.class类）。
 * @date 2017-06-11 00:50:22
 */
public class MultiThread {

    private int num = 0;

    public synchronized void printNum(String tag){
        if ("a".equals(tag)) {
            num = 100;
            System.out.println("tag a set num over");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            num = 200;
            System.out.println("tag b set num over");
        }
        System.out.println("tag:" + tag + "num:" + num);
    }

    public static void main(String[] args) {
        final MultiThread m1 = new MultiThread();
        final MultiThread m2 = new MultiThread();
        /**
         * 执行结果:
         *       tag a set num over
         *       tag b set num over
         *       tag:bnum:200
         *       tag:anum:100
         * 分析: synchronized锁是对象锁,每个对象都持有一个,而这里new了两个实例对象,所以会有两把锁,
         *       就并不存在,锁竞争和阻塞的问题.
         *       如果使用调用同一个对象,那么是先答应a在打印b的,t2阻塞在锁外面了
         */
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                m1.printNum("a");
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                m2.printNum("b");
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
