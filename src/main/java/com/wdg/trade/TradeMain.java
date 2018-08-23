package com.wdg.trade;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TradeMain {
    public static void main(String[] args) {
        generateOrder();
    }

    public static void generateOrder(){
        long beginTime = System.currentTimeMillis();
        int bufferSize = 1024;
        ExecutorService executor = Executors.newFixedThreadPool(8);
        GenerateOrderEvent event = new GenerateOrderEvent();
        event.setCoupon(BigDecimal.TEN);
        event.setScore(BigDecimal.ONE);
        Map<String,Integer> stock = Collections.EMPTY_MAP;
        event.setItemsStock(stock);
        CountDownLatch latch = new CountDownLatch(5);
        event.setLatch(latch);

        Disruptor<GenerateOrderEvent> disruptor = new Disruptor(()->{return event;},bufferSize,executor, ProducerType.SINGLE,new BusySpinWaitStrategy());
        disruptor.setDefaultExceptionHandler(new MyTradeExceptionHandler());

        TOrdersHandler ordersHandler = new TOrdersHandler();
        TOrdersItemHandler itemHandler = new TOrdersItemHandler();
        TOrdersCouponHandler couponHandler = new TOrdersCouponHandler();
        TOrdersScoreHandler scoreHandler  = new TOrdersScoreHandler();
        TOrdersStockHandler stockHandler = new TOrdersStockHandler();

        disruptor.handleEventsWith(ordersHandler,couponHandler,scoreHandler,stockHandler);
        disruptor.after(ordersHandler).handleEventsWith(itemHandler);

        disruptor.start();


        disruptor.publishEvent(new TOrderEventTranslator());
        try {
            System.out.println("发令枪前："+event);
            boolean overTime = latch.await(3, TimeUnit.SECONDS);
            if (!overTime) {
                System.out.println("超时："+event);
                System.out.println("下单超时,回退数据");
            }
            System.out.println("发令枪后："+event);
//            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        disruptor.shutdown();
        executor.shutdown();
        System.out.println("shutDown后:"+event);
        System.out.println("总耗时：" + (System.currentTimeMillis() - beginTime));

    }
}
