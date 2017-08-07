package com.wdg.base009;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.wdg.base008.Trade;

/**
 * @author wangdg
 * @Description: 数据处理4
 * @date 2017-06-11 00:50:22
 */
public class Handler4 implements EventHandler<Trade>,WorkHandler<Trade>{


    @Override
    public void onEvent(Trade trade, long l, boolean b) throws Exception {
        this.onEvent(trade);
    }

    @Override
    public void onEvent(Trade trade) throws Exception {
        System.out.println("handler4: get name : " + trade.getName());
        System.out.println(trade.getName() + "h4");
    }
}
