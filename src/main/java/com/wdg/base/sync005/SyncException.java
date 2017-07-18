package com.wdg.base.sync005;

/**
 * @author wangdg
 * @ClassName: SyncException
 * @Description: synchronized异常
 * @date 2017-06-11 00:50:22
 */
public class SyncException {

	private int num = 0;

	public synchronized void operation() {
		while (true) {
			num++;
			try {
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName()
						+ ", num = " + num);
				if (num == 20) {
                    Integer.parseInt("a");
//					throw new RuntimeException();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		final SyncException se = new SyncException();
        /**
         * 执行结果: catch块中,如果不是InterruptedException,则当前线程不会停止,会继续执行
         *           如果是InterruptedException,则会中断当前线程
         * 分析: InterruptedException会打断当前线程
         */
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				se.operation();
			}
		},"t1");

		t1.start();

	}
}
