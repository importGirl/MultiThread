package com.wdg.multi010;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangdg
 * @Description: 消费者
 * @date 2017-06-11 00:50:22
 */
public class Comsumer implements WorkHandler<Order> {

	private String consumerId;

	private static AtomicInteger count = new AtomicInteger(0);

	public Comsumer(String consumerId) {
		this.consumerId = consumerId;
	}

	@Override
	public void onEvent(Order order) throws Exception {
		System.out.println("当前消费者:" + this.consumerId + ", 消费信息:"
				+ order.getId());
		count.incrementAndGet();// 每消费一次,自增1
	}

	public int getCount() {
		return count.get();
	}
}
