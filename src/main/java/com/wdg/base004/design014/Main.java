package com.wdg.base004.design014;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class Main {

    public static void main(String[] args) {

        /**
         * 分析:
         *       客户端FutureClient->FutrueData包装类,立马返回.
         *       然后FutureData启动一个线程去请求RealData数据,
         *       当FutureClient在使用到返回结果的时候,就把RealData的结果
         *       返回给FutruClient
         */
        FutureClient fc = new FutureClient();
        Data data = fc.request("请求参数");
        System.out.println("请求发送成功");
        System.out.println("做其他事情");

        String result = data.getRequest();
        System.out.println(result);
    }
}
