package com.wdg.trade.model;

public class TOrders {

    private int id;
    private String orderSn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    @Override
    public String toString() {
        return "TOrders{" +
                "id=" + id +
                ", orderSn='" + orderSn + '\'' +
                '}';
    }
}
