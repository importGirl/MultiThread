package com.wdg.base005.concurrent018;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangdg
 * @Description: 自定义线程池
 * @date 2017-06-11 00:50:22
 */
public class UserThreadPool02 implements Runnable {

	private static AtomicInteger count = new AtomicInteger();

	@Override
	public void run() {
		try {
			int count = this.count.incrementAndGet();
			System.out.println("任务:" + count);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    public static void main(String[] args) throws InterruptedException {
        /**
         * 执行结果: 如果没有自定义拒绝策略,默认是抛出异常
         * 分析:
         *      无界队列: 如果当前线程 < coreThread 则创建coreThread执行,
         *      如果coreThread满了,则把任务放入队列中,队列是无界队列,不会有满的情况
         *      ****************************注意********************************
         *      如果任务的创建速度和处理速度相差太大,则无界队列会快速增长,知道系统内存耗尽
         */
//        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 120L, TimeUnit.SECONDS,
                queue);
        for (int i = 0; i < 21; i++) {
            pool.execute(new UserThreadPool02());
        }
        Thread.sleep(1000);
        System.out.println("queue size: " + queue.size());

    }
}
