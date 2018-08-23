package com.wdg.trade;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.wdg.trade.model.TOrderItems;
import com.wdg.trade.model.TOrders;

import java.util.List;
import java.util.concurrent.CountDownLatch;


public class TOrdersItemHandler implements EventHandler<GenerateOrderEvent>,WorkHandler<GenerateOrderEvent> {

    @Override
    public void onEvent(GenerateOrderEvent generateOrderEvent) throws Exception {
        try {
            List<TOrders> orders = generateOrderEvent.getOrders();
            for (TOrders order : orders) {
                System.out.println("插入主订单商品表:"+order.getId()+"->"+order.getOrderSn());
                TOrderItems item = new TOrderItems();
            }
            generateOrderEvent.setItem(true);
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
