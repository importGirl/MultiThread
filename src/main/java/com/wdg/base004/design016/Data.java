package com.wdg.base004.design016;

/**
 * @author wangdg
 * @Description: 数据封装
 * @date 2017-06-11 00:50:22
 */
public class Data {
    private long id;
    private String name;

    public Data(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
