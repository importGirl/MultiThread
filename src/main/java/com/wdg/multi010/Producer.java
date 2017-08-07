package com.wdg.multi010;

import com.lmax.disruptor.RingBuffer;

/**
 * @author wangdg
 * @Description: 生成者
 * @date 2017-06-11 00:50:22
 */
public class Producer {

    private RingBuffer<Order> ringBuffer;

    public Producer(RingBuffer<Order> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     *  onData用来发布事件,每调用一次就发布一次事件
     *  它的参数会员用过事件传递给消费者
     * @param data
     */
    public void onData(String data){
        // 可以把ringbuffer看做一个事件队列,那么next就是得到下面一个事件槽
        long seq = ringBuffer.next();
        try {
            // 用上面的索引取出一个空的事件用于填充(获取该序号对应的事件对象)
            Order order = ringBuffer.get(seq);
            // 获取要通过时间传递的业务数据
            order.setId(data);
        }finally {
            // 发布事件
            // 注意:
            ringBuffer.publish(seq);
        }
    }
}
