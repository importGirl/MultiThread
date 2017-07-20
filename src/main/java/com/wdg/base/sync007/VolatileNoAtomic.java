package com.wdg.base.sync007;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class VolatileNoAtomic extends  Thread{
//    private static volatile int count;
    private static AtomicInteger count = new AtomicInteger(0);
    private static void addCount(){
        for(int i=0;i<1000;i++){
//            count++;
            count.addAndGet(1);
        }
        System.out.println(count);
    }

    public void run(){
        addCount();
    }

    public static void main(String[] args) {
        VolatileNoAtomic[] arr = new VolatileNoAtomic[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new VolatileNoAtomic();
        }

        for (VolatileNoAtomic volatileNoAtomic : arr) {
            volatileNoAtomic.start();
        }
    }
}
