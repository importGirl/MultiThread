package com.wdg.base008;

import com.lmax.disruptor.*;

import java.util.concurrent.*;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class Main1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int BUFFER_SIZE = 1024;
        int THREAD_NUMBERS = 4;

        /**
         * createSingleProducer 创建一个单生产者的RingBuffer
         * 第一个参数叫EventFactory,从名字上理解就是"事件工厂",其实它的职责就是产生数据填充RingBuffer的区块
         * 第二个参数是RingBuffer的大小
         * 第三个参数是RingBuffer的生产都在没有可用区块的的时候(可能是消费者(或者说事件处理器)太慢了)的等待策略
         */
        RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        }, BUFFER_SIZE, new YieldingWaitStrategy());

        // 创建线程池
        ExecutorService executors = Executors.newFixedThreadPool(THREAD_NUMBERS);
        // 创建SequenceBarrier
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        // 创建消息处理器
        BatchEventProcessor<Trade> transProcessor = new BatchEventProcessor<>(ringBuffer, sequenceBarrier, new TradeHandler());
        // 这一步的目的就是把消费者的位置信息引用注入到生产者 如果只有一个消费者的情况可以省略
        ringBuffer.addGatingSequences(transProcessor.getSequence());
        // 把消息处理器提交到线程池
        executors.submit(transProcessor);
        // 如果存在多个消费者,那重复执行上面3行代码 把TradeHandler换成其它消费者类
        Future<Void> future = executors.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                long seq;
                for (int i = 0; i < 10; i++) {
                    seq = ringBuffer.next();
                    ringBuffer.get(seq).setPrice(Math.random() * 9999);// 给这个区块放入数据
                    ringBuffer.publish(seq);
                }
                return null;
            }
        });

        future.get();//等待生产者结束
        Thread.sleep(1000);// 登上1秒,等消费都处理完成
        transProcessor.halt();// 通知时间(或者说消息)处理器,可以结束了(并不是马上结束)
        executors.shutdownNow();// 终止线程

    }
}
