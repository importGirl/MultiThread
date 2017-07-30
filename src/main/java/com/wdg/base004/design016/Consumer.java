package com.wdg.base004.design016;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author wangdg
 * @Description: 消费者
 * @date 2017-06-11 00:50:22
 */
public class Consumer implements Runnable {

	private BlockingQueue<Data> queue;

	public Consumer(BlockingQueue queue) {
		this.queue = queue;
	}

	private static Random random = new Random();

	@Override
	public void run() {
		while (true) {
			try {
				Data data = queue.take();
				// 进行数据处理耗时
				Thread.sleep(random.nextInt(1000));
				System.out.println("当前消费线程: "
						+ Thread.currentThread().getName() + "消费成功,消费数据id为: "
						+ data.getId());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
