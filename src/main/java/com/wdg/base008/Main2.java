package com.wdg.base008;

import com.lmax.disruptor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangdg
 * @Description: WorkPool
 * @date 2017-06-11 00:50:22
 */
public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        int BUFFER_SIZE = 1024;
        int THREAD_NUMBERS = 4;

        EventFactory<Trade> eventFactory = new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        };

        RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(eventFactory, BUFFER_SIZE);

        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBERS);

        TradeHandler handler = new TradeHandler();
        // 创建工作池(消息处理器)
        WorkerPool<Trade> workerPool = new WorkerPool<>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(),new TradeHandler());

        workerPool.start(executor);

        // 生产数据
        for (int i = 0; i < 8; i++) {
            long seq = ringBuffer.next();
            ringBuffer.get(seq).setPrice(Math.random()*9999);
            ringBuffer.publish(seq);
        }

        Thread.sleep(1000);
        workerPool.halt();
        executor.shutdown();
    }
}
