package com.wdg.base009;

import com.lmax.disruptor.EventHandler;
import com.wdg.base008.Trade;

/**
 * @author wangdg
 * @Description: 数据处理2
 * @date 2017-06-11 00:50:22
 */
public class Handler2 implements EventHandler<Trade>{

    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        System.out.println("handler2: set price");
        trade.setPrice(17.0);
        Thread.sleep(1000);
    }
}
