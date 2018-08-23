package com.wdg.trade;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.WorkerPool;
import com.wdg.trade.model.TOrders;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;


public class TOrdersHandler implements EventHandler<GenerateOrderEvent>,WorkHandler<GenerateOrderEvent> {

    @Override
    public void onEvent(GenerateOrderEvent generateOrderEvent) throws Exception {
        try {
            System.out.println("插入主订单表");
            TOrders order = new TOrders();
            order.setId(1);
            order.setOrderSn("123");
            TOrders order2 = new TOrders();
            order2.setId(2);
            order2.setOrderSn("1233");
            generateOrderEvent.setOrders(Arrays.asList(order,order2));
            generateOrderEvent.setTOrder(true);
            CountDownLatch latch = generateOrderEvent.getLatch();
            latch.countDown();
        }catch (Exception e){
            generateOrderEvent.setTOrder(false);
        }

    }


    @Override
    public void onEvent(GenerateOrderEvent generateOrderEvent, long l, boolean b) throws Exception {
        this.onEvent(generateOrderEvent);
    }
}
