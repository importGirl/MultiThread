package com.wdg.trade;

import com.lmax.disruptor.ExceptionHandler;

public class MyTradeExceptionHandler implements ExceptionHandler<GenerateOrderEvent> {
    @Override
    public void handleEventException(Throwable throwable, long l, GenerateOrderEvent generateOrderEvent) {
        throw new RuntimeException("订单异常:"+throwable.getMessage());
    }

    @Override
    public void handleOnStartException(Throwable throwable) {
        System.out.println("handleOnStartException ...");
    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {
        System.out.println("handleOnShutdownException ...");
    }
}
