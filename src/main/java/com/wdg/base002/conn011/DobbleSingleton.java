package com.wdg.base002.conn011;

/**
 * @author wangdg
 * @Description: 单例模式-懒汉式
 * @date 2017-06-11 00:50:22
 */
public class DobbleSingleton {

	private  static DobbleSingleton ds;

	public static DobbleSingleton getDs() {
		if (ds == null) {
            // 1
			synchronized (DobbleSingleton.class) {
				if (ds == null) {//一定要使用,不然当多个线程在1出阻塞,第一条线程已经创建了对象,会重复创建对象
					ds = new DobbleSingleton();
				}
			}
		}
		return ds;
	}

    public static void main(String[] args) {

        /**
         * 执行结果: hashcode都是相同的
         * 分析:     饿汉式的单例模式需要两重判断,不然hashcode是不同的
         */
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(DobbleSingleton.getDs().hashCode());
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println(DobbleSingleton.getDs().hashCode());
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                System.out.println(DobbleSingleton.getDs().hashCode());
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();
    }

}
