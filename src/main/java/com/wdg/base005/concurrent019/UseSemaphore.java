package com.wdg.base005.concurrent019;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author wangdg
 * @Description: 信号量
 * @date 2017-06-11 00:50:22
 */
public class UseSemaphore {

    public static void main(String[] args) {
        /**
         * 执行结果: 只有5个线程能同时开始访问
         * 分析:
         *          通过Semaphore可以现在某些资源的线程访问数量
         *
         */
        ExecutorService executor = Executors.newCachedThreadPool();
        // 允许5个线程同时进行访问
        Semaphore semap = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            final int no = i;
            Thread runner = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semap.acquire();// 获得许可
                        System.out.println("Accessing" + no);
                        Thread.sleep(1000);
                        semap.release();// 访问完成,释放许可
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            executor.execute(runner);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
