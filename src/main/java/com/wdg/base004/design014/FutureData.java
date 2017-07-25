package com.wdg.base004.design014;

/**
 * @author wangdg
 * @Description:
 * @date 2017-06-11 00:50:22
 */
public class FutureData implements Data{

    private RealData realData;
    private Boolean isReady=false;

    public synchronized void setRealData(RealData realData) {
        // 如果已经装载完毕,就直接返回
        if(isReady){
            return;
        }
        // 如果没有装载,进行装载真实对象
        this.realData = realData;
        isReady = true;
        // 进行通知
        notify();
    }

    @Override
    public synchronized String getRequest() {
        // 如果没装载好, 程序处于阻塞状态
        if (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 装载好直接获取数据即可
        return realData.getRequest();
    }
}
