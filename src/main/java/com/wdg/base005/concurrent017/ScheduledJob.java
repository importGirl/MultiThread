package com.wdg.base005.concurrent017;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wangdg
 * @Description: 定时任务
 * @date 2017-06-11 00:50:22
 */
class Temp extends Thread{
    @Override
    public void run() {
        System.out.println("run");
    }
}
public class ScheduledJob {
    public static void main(String[] args) {
        Temp command = new Temp();
        // 定时周期性执行的任务
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> scheduledFuture = service.scheduleWithFixedDelay(command, 1, 2, TimeUnit.SECONDS);
    }
}
