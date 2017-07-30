package com.wdg.base005.concurrent019;

import java.util.concurrent.*;

/**
 * @author wangdg
 * @Description: 使用FutureTask获取线程结果
 * @date 2017-06-11 00:50:22
 */
public class UserFuture implements Callable{

    private  String para;

    public UserFuture(String para) {
        this.para = para;
    }

    /**
     *  这里是真实的业务逻辑，其执行可能很慢
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        // 模拟执行耗时
        try {
            Thread.sleep(2000);
            String ret = this.para + "处理完成";
            return ret;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String queryStr = "query";
        // 构造FutureTask,并且传入需要真正业务逻辑处理的类,该类一定是实现了Callable接口的类
        FutureTask f1 = new FutureTask<String>(new UserFuture(queryStr));
        FutureTask f2 = new FutureTask<String>(new UserFuture(queryStr));

        // 创建一个固定线程的线程池切线程数为2
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // submit execute的区别:
        //  1 submit可以传入实现了Callbale接口的实例对象
        //  2 submit有submit的方法有返回值
        Future<?> future1 = executor.submit(f1); // 单独启动一个线程去执行
        Future<?> future2 = executor.submit(f2);
        System.out.println("请求完毕");

        System.out.println("处理业务逻辑完毕");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 如果call()方法还没有执行完成,则依然会进行等待
        System.out.println("数据: " + f1.get());
        System.out.println("数据: " + f2.get());
        executor.shutdown();
    }
}
