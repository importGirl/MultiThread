package com.wdg.base008;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;


/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class TradeHandler implements EventHandler<Trade>,WorkHandler<Trade>{

    @Override
    public void onEvent(Trade event, long l, boolean b) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(Trade event) throws Exception {
        // 这里做具体的消费逻辑
        event.setId(UUID.randomUUID().toString());// 简单生成下交易单id
        System.out.println(event.getId());
    }

}
