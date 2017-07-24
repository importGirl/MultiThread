package com.wdg.base003.coll013;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class Wangmin implements Delayed {
	private String name;
	private Long id;
	private Long endTime;
	private TimeUnit timeUnit = TimeUnit.SECONDS;

	public Wangmin(String name, Long endTime, Long id) {
		this.name = name;
		this.endTime = endTime;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	/**
	 *  获取剩余延迟时间
	 * @param unit
	 * @return
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		return endTime - System.currentTimeMillis();
	}

	/**
	 *  队列排序使用
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Delayed delayed) {
		Wangmin wm = (Wangmin) delayed;
		return this.getDelay(this.timeUnit) > wm.getDelay(this.timeUnit) ? 1
				: 0;
	}
}
