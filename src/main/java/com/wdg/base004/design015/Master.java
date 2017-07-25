package com.wdg.base004.design015;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class Master {
	// 1.盛装所有任务的容器
	ConcurrentLinkedQueue workQueue = new ConcurrentLinkedQueue<>();
	// 2.盛装所有的Worker对象
	Map<String, Thread> workers = new HashMap<String, Thread>();
	// 3.盛装每一个Worker并发处理任务的结果集
	ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

	// 7.构造方法
	public Master(Worker worker, int workerCount) {
		worker.setTask(workQueue);
		worker.setResult(resultMap);
		for (int i = 0; i < workerCount; i++) {
			workers.put(Integer.toString(i), new Thread(worker));
		}
	}

	// 4.提交任务
	public void submit(Task task) {
		workQueue.add(task);
	}

	// 5.启动任务
	public void execute() {
		for (Map.Entry<String, Thread> ele : workers.entrySet()) {
			ele.getValue().start();
		}
	}

	// 6.计算结果
	public int getResult() {
		Integer priceSum = 0;
		for (Map.Entry<String, Object> ele : resultMap.entrySet()) {
			priceSum += (Integer)ele.getValue();
		}
		return priceSum;
	}

	// 8.是否运行结束的方法
	public boolean isComplete() {
		for (Map.Entry<String, Thread> ele : workers.entrySet()) {
			// 线程状态是否退出
			if (ele.getValue().getState() != Thread.State.TERMINATED) {
				return false;
			}
		}
		return true;
	}
}
