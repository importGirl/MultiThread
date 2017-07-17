package com.wdg.base.sync005;

/**
 * @author wangdg
 * @ClassName: SyncDubbo1
 * @Description: 调用方法时 synchronized的重入
 * @date 2017-06-11 00:50:22
 */
public class SyncDubbo1 {
    /**
     * 可重入锁: 对象锁还没释放,当想要再获取这个对象的锁的时候还可以获取的(递归无阻塞的同步锁)
     */
    public synchronized void method1(){
        method2();
    }
    public synchronized void method2(){
        method3();
    }
    public synchronized void method3(){
        System.out.println("method3");
    }

    public static void main(String[] args) {
        final SyncDubbo1 sd = new SyncDubbo1();

        /**
         * 执行结果 : method3
         * 分析     :
         *            如果该锁是不可重入的话,那么m1方法拿到锁了,就一直没有释放掉,又进不去m2方法,会造成死锁问题.
         *            可重入锁是对于当前线程而言的,多线程时,还是竞争同一把锁,不存在多线程锁重入的问题
         */
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                sd.method1();
            }
        }, "t1");

        t1.start();
    }

}
