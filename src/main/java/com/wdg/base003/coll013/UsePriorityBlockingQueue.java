package com.wdg.base003.coll013;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author wangdg
 * @Description: 基于优先级的无界阻塞队列,
 * @date 2017-06-11 00:50:22
 */
public class UsePriorityBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

        /**
         * 分析:
         *        该队列只有在取值的时候才会进行优先级排序,每次取值都会把优先级高的先取出来
         *        ,但是不会对该队列集合进行排序
         */
        Task t1 = new Task("t1",1);
        Task t2 = new Task("t2", 4);
        Task t3 = new Task("t3", 3);
        Task t4 = new Task("t4", 2);

        queue.add(t1);
        queue.add(t2);
        queue.add(t3);
        queue.add(t4);

        System.out.println("队列: " + queue);
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println("队列: "+queue);
    }
}
