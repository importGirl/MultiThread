package com.wdg.base007;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author wangdg
 * @Description: 数据生成者
 * @date 2017-06-11 00:50:22
 */
/**
 * 很明显的是：当用一个简单队列来发布事件的时候会牵涉更多的细节，这是因为事件对象还需要预先创建。
 * 发布事件最少需要两步：获取下一个事件槽并发布事件（发布事件的时候要使用try/finnally保证事件一定会被发布）。
 * 如果我们使用RingBuffer.next()获取一个事件槽，那么一定要发布对应的事件。
 * 如果不能发布事件，那么就会引起Disruptor状态的混乱。
 * 尤其是在多个事件生产者的情况下会导致事件消费者失速，从而不得不重启应用才能会恢复。
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 */
public class LongEventProducer {

	private RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	/**
	 *  onData用来发布事件,每调用一次就发布一次时间,
	 *  它的参数会用过事件传递给消费者
	 *
	 */
	public void onData(ByteBuffer bb) {
		// 1. 可以吧ringBuffer看做一个事件队列,.那么next就是获得下一个事件槽
		long sequence = ringBuffer.next();
		try {
			// 2. 根据事件槽的索引位置取出一个空的事件用于填充(获取该序号对应的对象 )
			LongEvent longEvent = ringBuffer.get(sequence);
			// 3. 获取通过事件传递的业务数据
			longEvent.setValue(bb.getLong(0));
		} finally {
			// 4. 发布事件
			// ***************************注意*************************************
			// 最后的ringbuffer.publish() 必须包含在finally中以确保得到调用,如果某个请求的sequence未被提交,
			// 将会阻塞后续的发布操作或其他的producer
			ringBuffer.publish(sequence);
		}
	}
}
