package com.wdg.base002.conn011;

/**
 * @author wangdg
 * @Description: 单例模式-饿汉式
 * @date 2017-06-11 00:50:22
 */
public class Singletion {
    private Singletion() {

    }
    private static class InnerSingletion{
        private static Singletion single = new Singletion();
    }

    public Singletion getInstance(){
        return InnerSingletion.single;
    }

}
