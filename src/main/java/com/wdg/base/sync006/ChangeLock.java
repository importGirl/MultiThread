package com.wdg.base.sync006;

/**
 * @author wangdg
 * @ClassName:
 * @Description: 锁对象的改变
 * @date 2017-06-11 00:50:22
 */
public class ChangeLock {

    private String lock = "lock";

    public void change(){
        synchronized (lock){
            System.out.println("当前线程" + Thread.currentThread().getName() + "开始");
//            lock = "changeLock";
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程" + Thread.currentThread().getName() + "结束");
        }
    }

    public static void main(String[] args) {
        final ChangeLock cl = new ChangeLock();
        /**
         * 执行结果:
         *          当前线程t1开始
         *          当前线程t1结束
         *          当前线程t2开始
         *          当前线程t2结束
         * 分析:
         *          锁对象不能改变,一旦改变就不具备,值若改变了就不是同一把锁了
         *
         */
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                cl.change();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                cl.change();
            }
        },"t2");

        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
