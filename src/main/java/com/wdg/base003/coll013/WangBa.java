package com.wdg.base003.coll013;

import java.util.concurrent.DelayQueue;

/**
 * @author wangdg
 * @Description: 带有延迟时间的阻塞queue
 * @date 2017-06-11 00:50:22
 */
public class WangBa implements Runnable {

	DelayQueue<Wangmin> queue = new DelayQueue();

	private Boolean yinye = true;

	public void shangji(String name, long money, Long id) {
		Wangmin wangmin = new Wangmin(name, money * 1000+System.currentTimeMillis(), id);
		System.out.println("id: " + id + "名字: " + name + "上机");
		queue.add(wangmin);
	}

	public void xiaji(Wangmin wm) {
		System.out.println("id: " + wm.getId() + "名字: " + wm.getName() + "下机");
	}

	@Override
	public void run() {
		while (yinye) {
			try {
				Wangmin man = queue.take();
				xiaji(man);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
        /**
         * 分析:
         *
         * DelayQueue: 带有延迟时间的Queue,其中的元素只有当其指定的延迟时间到了,才能从队列中获取到根元素
         *
         * 应用场景:
         *              1. 缓存超时的数据移除
         *              2. 任务超时处理
         *              3. 空闲连接的关闭
         * 注意:
         *            队列的延迟任务必须实现Delayed接口,没有大小限制的队列
         */
        System.out.println("网吧开始营业");
        WangBa wangBa = new WangBa();
        wangBa.shangji("张三",3,1L);
        wangBa.shangji("李四",4,2L);
        wangBa.shangji("王五",5,5L);

        new Thread(wangBa).start();
    }
}


















