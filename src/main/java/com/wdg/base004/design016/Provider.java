package com.wdg.base004.design016;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Provider implements Runnable {
    //共享缓存区
    private BlockingQueue<Data> queue;
    // 必须使用static修饰,表示所有实例共享的变量
    private static AtomicInteger count = new AtomicInteger();

    private Random random = new Random();


    private volatile boolean isRunning = true;

    public Provider(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(isRunning){
            try {
                Thread.sleep(random.nextInt(1000));
                int id = count.incrementAndGet();
                Data data = new Data(id, "数据" + id);
                System.out.println("当前线程:" + Thread.currentThread().getName() + "获取了数据,id为:" + id + ",进行装载到公共的缓冲区中...");
                if(!queue.offer(data, 2, TimeUnit.SECONDS)){
                    System.out.println("提交到缓存区数据失败");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.isRunning = false;
    }

}
