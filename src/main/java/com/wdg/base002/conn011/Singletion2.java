package com.wdg.base002.conn011;

/**
 * @author wangdg
 * @Description: 单例模式-饿汉式 2
 * @date 2017-06-11 00:50:22
 */
public class Singletion2 {
    private Singletion2() {

    }
    private static Singletion2 singletion = new Singletion2();

    public static Singletion2 getInstance(){
        return singletion;
    }
}
