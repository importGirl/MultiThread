package com.wdg.base.sync007;

/**
 * @author wangdg
 * @ClassName:
 * @Description: volatile关键字,可见性
 * @date 2017-06-11 00:50:22
 */
public class RunThread implements Runnable{
    private volatile Boolean isRunning = true;

    public void setRunning(Boolean running) {
        this.isRunning = running;
    }


    public void run() {

        int i = 0;
        while(isRunning){
            System.out.println("当前线程:" + Thread.currentThread().getName() + "开始");
        }
        System.out.println("线程停止");
    }

    public static void main(String[] args) {
        RunThread thread = new RunThread();
        // 不能直接调用run()方法,直接调用run()方法，只会执行同一个线程中的任务，而不会启动新线程。调用start()方法将会创建一个执行run()方法的线程
//        thread.run();
        /**
         * 执行结果:
         *          使用volatile: 线程会停止
         *          不用volatile: 线程不会终止
         * 分析:
         *          使用volatile关键字可以使isRunning变量在多个线程间可见,所以当主线程设置isRunning=false
         *          时,就停止了
         */
        Thread t1 = new Thread(thread, "t1");
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.setRunning(false);
        System.out.println("isRunning的值已经被设置为false了");
    }
}
