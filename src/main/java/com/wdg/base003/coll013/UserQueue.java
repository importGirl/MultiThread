package com.wdg.base003.coll013;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdg
 * @Description: 非阻塞队列和阻塞队列
 * @date 2017-06-11 00:50:22
 */
public class UserQueue {

    public static void main(String[] args) throws InterruptedException {

        // 高性能无阻塞无界队列: ConcurrentLinkedQueue
        ConcurrentLinkedQueue<Object> q = new ConcurrentLinkedQueue<>();
        q.offer("a");
        q.offer("b");
        q.offer("c");
        q.offer("d");
        q.add("e");

        System.out.println(q.poll());
        System.out.println(q.size());
        System.out.println(q.peek());
        System.out.println(q.size());

        // 有界阻塞队列
        ArrayBlockingQueue array = new ArrayBlockingQueue<>(5);
        array.put("a");
        array.put("b");
        array.add("c");
        array.add("d");
        array.add("e");
//        array.add("f");
        array.offer("a", 3, TimeUnit.SECONDS);

        for (Object ele : array) {
            System.out.println(ele);
        }

        ArrayList<String> list = new ArrayList<>();
        System.out.println("========================");
        System.out.println(array.drainTo(list));
        for (String ele : list) {
            System.out.println(ele);
        }

        System.out.println("=====================");
        SynchronousQueue<String> syn = new SynchronousQueue<>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("取出元素: "+syn.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        t1.start();
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("放入元素: "+syn.add("abcd"));
//            }
//        }, "t2");
//        t2.start();

    }
}
