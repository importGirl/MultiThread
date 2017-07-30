package com.wdg.base005.concurrent019;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangdg
 * @Description: 多个线程阻塞等待满足条件
 * @date 2017-06-11 00:50:22
 */
public class UseCyclicBarrier {

    static class Runner implements Runnable{
        private String name;
        private CyclicBarrier barrier;

        public Runner(String name, CyclicBarrier barrier) {
            this.name = name;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(new Random().nextInt(1000));
                System.out.println(name + "准备OK");
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + "GO!!");
        }
    }

    public static void main(String[] args) {
        /**
         * 执行结果:  3个线程阻塞等待到满足要求
         * 分析:
         *       多个线程并行阻塞等待满足要求
         */
        CyclicBarrier barrier = new CyclicBarrier(3);
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(new Runner("zhangsan",barrier));
        service.execute(new Runner("lisi",barrier));
        service.execute(new Runner("wangwu",barrier));
    }

}
