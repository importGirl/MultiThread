package com.wdg.trade;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.wdg.trade.model.TOrders;

import java.util.concurrent.CountDownLatch;


public class TOrdersStockHandler implements EventHandler<GenerateOrderEvent>,WorkHandler<GenerateOrderEvent> {

    @Override
    public void onEvent(GenerateOrderEvent generateOrderEvent) throws Exception {
        try {
            System.out.println("下单扣减库存");
            generateOrderEvent.setStock(true);
            CountDownLatch latch = generateOrderEvent.getLatch();
            latch.countDown();
        }catch (Exception e){
            generateOrderEvent.setItem(false);
        }

    }


    @Override
    public void onEvent(GenerateOrderEvent generateOrderEvent, long l, boolean b) throws Exception {
        this.onEvent(generateOrderEvent);
    }
}
