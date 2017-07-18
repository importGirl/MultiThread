package com.wdg.base.sync007;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangdg
 * @ClassName: AtomicUse
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class AtomicUse {
    private static AtomicInteger count = new AtomicInteger(0);
    //多个addAndGet在一个方法内是非原子性的，需要加synchronized进行修饰，保证4个addAndGet整体原子性
    /**synchronized*/
    public  int multiAdd(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.addAndGet(1);
        count.addAndGet(2);
        count.addAndGet(3);
        count.addAndGet(4); // +10
        return count.get();
    }

    public static void main(String[] args) {
        final AtomicUse atomicUse = new AtomicUse();
        List<Thread> list = new ArrayList<Thread>();
        for(int i =0;i<100;i++){
            Thread t = new Thread(new Runnable() {
                public void run() {
                    System.out.println(atomicUse.multiAdd());
                }
            }, "t" + i);
            list.add(t);
        }
        for (Thread thread : list) {
            thread.start();
        }
    }
}
