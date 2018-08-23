package com.wdg.trade;

import com.lmax.disruptor.EventTranslator;

public class TOrderEventTranslator implements EventTranslator<GenerateOrderEvent>{


    @Override
    public void translateTo(GenerateOrderEvent generateOrderEvent, long l) {
        generateOrderEvent.setFlag("1");
    }
}
