package com.wdg.base005.concurrent018;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdg
 * @Description: 自定义线程池使用
 * @date 2017-06-11 00:50:22
 */
public class UserThreadPool01 {

	public static void main(String[] args) {

		/**
		 *  执行结果: 任务6被拒绝
		 *  分析: 
		 *  有界队列特点: 当前线程数 < coreThread则创建coreThread执行,coreThread满了则把任务放入到队列中,
         *  如果队列满了且当前线程数小于maxThread,则创建非核心线程执行任务,
         *  如果maxThread满了,则执行拒绝任务
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
		        1,                        // 核心线程数
                2,                     // 最大线程数
                5L,                      // 空闲时间多久后线程收回
				TimeUnit.SECONDS,                      // 空闲时间的单位
//                new ArrayBlockingQueue<Runnable>(3),
				new LinkedBlockingQueue<Runnable>(),   // 线程池使用的队列
				new MyRejected());                     // 超过最大线程数后的拒绝策略

		MyTask t1 = new MyTask(1L, "任务1");
		MyTask t2 = new MyTask(2L, "任务2");
		MyTask t3 = new MyTask(3L, "任务3");
		MyTask t4 = new MyTask(4L, "任务4");
		MyTask t5 = new MyTask(5L, "任务5");
		MyTask t6 = new MyTask(6L, "任务6");

		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		pool.execute(t6);

		pool.shutdown();

	}
}
