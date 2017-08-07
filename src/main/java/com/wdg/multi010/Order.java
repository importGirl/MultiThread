package com.wdg.multi010;

/**
 * @author wangdg
 * @Description: 数据单元
 * @date 2017-06-11 00:50:22
 */
public class Order {

    private String id;
    private String name;
    private double price;// 金额

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
}
