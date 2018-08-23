package com.wdg.trade;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.wdg.trade.model.TOrders;

import java.util.concurrent.CountDownLatch;


public class TOrdersCouponHandler implements EventHandler<GenerateOrderEvent>,WorkHandler<GenerateOrderEvent> {

    @Override
    public void onEvent(GenerateOrderEvent generateOrderEvent) throws Exception {
        try {
            System.out.println("下单扣减积分");
            generateOrderEvent.setCoupon(true);
            CountDownLatch latch = generateOrderEvent.getLatch();
            latch.countDown();
        }catch (Exception e){
            generateOrderEvent.setCoupon(false);
        }


    }


    @Override
    public void onEvent(GenerateOrderEvent generateOrderEvent, long l, boolean b) throws Exception {
        this.onEvent(generateOrderEvent);
    }
}
