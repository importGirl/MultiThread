package com.wdg.multi010;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //创建ringBuffer
        RingBuffer<Order> ringBuffer = RingBuffer.create(ProducerType.MULTI, new EventFactory<Order>() {
            @Override
            public Order newInstance() {
                return new Order();
            }
        }, 1024 * 1024, new YieldingWaitStrategy());

        SequenceBarrier barriers = ringBuffer.newBarrier();
        Comsumer[] comsumers = new Comsumer[3];
        for (int i = 0; i < comsumers.length; i++) {
            comsumers [i] = new Comsumer("c" + i);
        }

        WorkerPool<Order> workerPool = new WorkerPool<>(ringBuffer, barriers, new IgnoreExceptionHandler(), comsumers);
        // 告诉ringBuffer,消费者的位置
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        workerPool.start(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            Producer p = new Producer(ringBuffer);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 100; j++) {
                        p.onData(UUID.randomUUID().toString());// 一个生产者生产100个订单
                    }
                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println("---------------开始生产------------------");
        latch.countDown();// 发令枪
        Thread.sleep(5000);
        System.out.println("总数:" + comsumers[0].getCount());
    }
}
