package com.wdg.base.sync005;

/**
 * @author wangdg
 * @ClassName: SyncDubbo2
 * @Description: 继承时 synchronized的重入
 * @date 2017-06-11 00:50:22
 */
public class SyncDubbo2 {
	/**
	 * 可重入锁: 对象锁还没释放,当想要再获取这个对象的锁的时候还可以获取的(递归无阻塞的同步锁)
     *          *** 线程可以进入任何一个它已经拥有的锁所同步着的代码块。 ***
	 */
	static class main {
		public int num = 10;

		public synchronized void operationSup() {
			num--;
			System.out.println("Sup print num = " + num);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	static class Sub extends main {
		public synchronized void operationSub() {
			while (num > 0) {
				num--;
				System.out.println("Sub print num = " + num);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.operationSup();
			}

		}
	}

	public static void main(String[] args) {
		final Sub sub = new Sub();
        /**
         * 执行结果:
         *       Sub print num = 3
                 Sup print num = 2
                 Sub print num = 1
                 Sup print num = 0
           分析:
                子类的方法调用父类的方法,还是一条线程中,所以子类方法的锁可以重用到父类中
                *** 线程: 该线程执行到终点,才算一条线程.不是执行到一半,没有返回结果 ***
         */
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				sub.operationSub();
			}
		}, "t1");

		t1.start();
	}
}
