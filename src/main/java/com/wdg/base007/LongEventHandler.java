package com.wdg.base007;


import com.lmax.disruptor.EventHandler;

/**
 * @author wangdg
 * @Description: 数据处理类(消费者)
 *  事件消费者,也就是一个事件处理器.这个事件处理器简单的把事件中存储的数据打印到终端
 * @date 2017-06-11 00:50:22
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    public void onEvent(LongEvent o, long l, boolean b) throws Exception {
        System.out.println(o.getValue());
    }
}
