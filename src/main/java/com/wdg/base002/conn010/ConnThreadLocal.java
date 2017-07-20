package com.wdg.base002.conn010;

/**
 * @author wangdg
 * @Description: 本地线程变量
 * @date 2017-06-11 00:50:22
 */
public class ConnThreadLocal {
    private ThreadLocal th = new ThreadLocal();// 当前线程变量

    public void  setValue(Object obj){
        th.set(obj);
    }

    public void getValue(){
        System.out.println(Thread.currentThread().getName() + " : " + this.th.get());
    }

    public static void main(String[] args) throws InterruptedException {
       final ConnThreadLocal connThreadLocal = new ConnThreadLocal();

        /**
         * 执行结果: t1 : 张三   t2 : null
         * 分析:
         *          本地线程变量类,对于其他线程时不可见的,可以用于保存当前线程的变量
         */
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                connThreadLocal.setValue("张三");
                connThreadLocal.getValue();

            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                connThreadLocal.getValue();
            }
        }, "t2");

        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}
