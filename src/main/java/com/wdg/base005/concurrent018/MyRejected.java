package com.wdg.base005.concurrent018;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wangdg
 * @Description: 自定义拒绝策略
 * @date 2017-06-11 00:50:22
 */
public class MyRejected implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("自定义拒绝处理");
        System.out.println("当前拒绝任务为: " + r.toString());
    }
}
