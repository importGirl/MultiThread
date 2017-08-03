package com.wdg.base007;

import com.lmax.disruptor.EventFactory;

/**
 * @author wangdg
 * @Description: LongEvent工厂类
 *  需要让disruptor为我们创建事件,我们同事还声明了一个EventFactory来实例化Event对象
 * @date 2017-06-11 00:50:22
 */
public class LongEventFactory implements EventFactory<LongEvent>{

    public LongEvent newInstance(){
        return new LongEvent();
    }
}
