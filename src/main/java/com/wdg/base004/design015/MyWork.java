package com.wdg.base004.design015;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class MyWork extends Worker {

    @Override
    public Object handle(Task task) {

        Object output = null;
        try {
            //表示处理task任务的耗时，可能是数据的加工，也可能是操作数据库...
            Thread.sleep(500);
            output = task.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}
