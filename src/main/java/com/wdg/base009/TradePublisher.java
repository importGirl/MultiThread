package com.wdg.base009;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import com.wdg.base008.Trade;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangdg
 * @Description: 交易订单发布者
 * @date 2017-06-11 00:50:22
 */
public class TradePublisher implements Runnable{


    Disruptor<Trade> disruptor;
    private CountDownLatch latch;

    private static int LOOP = 10;// 模拟百万次交易的发生

    public TradePublisher(CountDownLatch latch,Disruptor<Trade> disruptor){
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        TradeEventTranslator tradeEventTranslator = new TradeEventTranslator();
        for (int i = 0; i < LOOP; i++) {
            disruptor.publishEvent(tradeEventTranslator);
        }
        latch.countDown();
    }

    class TradeEventTranslator implements EventTranslator<Trade>{

        private Random random = new Random();
        @Override
        public void translateTo(Trade trade, long sequence) {
            this.generateTrade(trade);
        }

        private Trade generateTrade(Trade trade){
            trade.setPrice(random.nextDouble()*9999);
            return trade;
        }
    }

}
