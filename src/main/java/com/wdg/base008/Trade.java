package com.wdg.base008;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangdg
 * @Description: 交易订单
 * @date 2017-06-11 00:50:22
 */

public class Trade {

    private String id;
    private String name;
    private double price;
    private AtomicInteger count = new AtomicInteger(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }
}
