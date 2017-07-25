package com.wdg.base004.design015;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public abstract class Worker implements Runnable {

	// 1. 有一个任务队列的引用
	private ConcurrentLinkedQueue<Task> workQueue;
	// 2.任务结果集容器的引用
	private ConcurrentHashMap<String, Object> resultMap;

	public void setTask(ConcurrentLinkedQueue<Task> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResult(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	// 3. 线程任务
	@Override
	public void run() {
		while (true) {
			Task task = workQueue.poll();
			if (task == null)
				break;
			Object result = handle(task);
			resultMap.put(task.getId().toString(), result);
		}
	}

	// 4. 处理的方法
	public abstract Object handle(Task task);
}
