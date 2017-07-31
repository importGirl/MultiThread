package com.wdg.base006.lock020;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangdg
 * @Description: Condition API
 * @date 2017-06-11 00:50:22
 */
public class UseCondition {

   ReentrantLock lock =  new ReentrantLock();
   Condition condition = lock.newCondition();

   public void method1(){
       lock.lock();
       try {
           condition.await();
           System.out.println("当前线程:" + Thread.currentThread().getName() + "继续执行...");
       } catch (InterruptedException e) {
           e.printStackTrace();
       }finally {
           lock.unlock();
       }
   }

   public void method2(){
       try{
           lock.lock();
           System.out.println("当前线程: " + Thread.currentThread().getName() + "获取锁");
           condition.signal();

       }catch (Exception e){
           e.printStackTrace();
       }finally {
           lock.unlock();
       }
   }

    public static void main(String[] args) {
        /**
         * 分析: await()要等singal()来唤醒
         */
        final UseCondition userCondition = new UseCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
              userCondition.method1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                userCondition.method2();
            }
        }).start();

    }
}
