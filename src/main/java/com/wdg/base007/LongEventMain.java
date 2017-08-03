package com.wdg.base007;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class LongEventMain {

	public static void main(String[] args) {
	    // 创建一个线程池
		ExecutorService executor = Executors.newCachedThreadPool();
		// 创建LongEvent的工厂实例
		LongEventFactory factory = new LongEventFactory();
		// 指定RingbufferSize的大小 必须是2^n
		int ringBufferSize = 1024 * 1024;

        /**
         //BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
         WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
         //SleepingWaitStrategy 的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
         WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
         //YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
         WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
         */

		// 创建disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, 8, executor, ProducerType.SINGLE,
                new YieldingWaitStrategy());
        // 连接消费事件的方法
        disruptor.handleEventsWith(new LongEventHandler());
        // 启动
        disruptor.start();

        // disruptor发布事件,分为两个阶段
        // 发布事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
//        LongEventProducer producer = new LongEventProducer(ringBuffer);
        LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long i = 0; i < 100; i++) {
           bb.putLong(0,i);
           producer.onData(bb);
        }
        // 关闭disruptor
        disruptor.shutdown();
        // 关闭线程池
        executor.shutdown();
    }
}
