package com.wdg.trade;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.wdg.trade.model.TOrders;

import java.util.concurrent.CountDownLatch;


public class TOrdersScoreHandler implements EventHandler<GenerateOrderEvent>,WorkHandler<GenerateOrderEvent> {

    @Override
    public void onEvent(GenerateOrderEvent generateOrderEvent) throws Exception {
        try {
            System.out.println("下单扣减积分");
            generateOrderEvent.setScore(false);
//            if (true) throw  new RuntimeException("下单扣减积分异常");
            CountDownLatch latch = generateOrderEvent.getLatch();
            latch.countDown();
        }catch (Exception e){
            generateOrderEvent.setScore(false);
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public void onEvent(GenerateOrderEvent generateOrderEvent, long l, boolean b) throws Exception {
        this.onEvent(generateOrderEvent);
    }
}
