package com.wdg.trade;

import com.wdg.trade.model.TOrders;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class GenerateOrderEvent implements Serializable{

    private List<TOrders> orders;

    private BigDecimal coupon;

    private BigDecimal score;

    private Map<String,Integer> itemsStock;

    private CountDownLatch latch;

    private String flag;

    private Boolean isTOrder;
    private Boolean isItem;
    private Boolean isCoupon;
    private Boolean isScore;
    private Boolean isStock;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Boolean getTOrder() {
        return isTOrder;
    }

    public void setTOrder(Boolean TOrder) {
        isTOrder = TOrder;
    }

    public Boolean getItem() {
        return isItem;
    }

    public void setItem(Boolean item) {
        isItem = item;
    }

    public void setCoupon(Boolean coupon) {
        isCoupon = coupon;
    }

    public void setScore(Boolean score) {
        isScore = score;
    }

    public Boolean getStock() {
        return isStock;
    }

    public void setStock(Boolean stock) {
        isStock = stock;
    }

    public List<TOrders> getOrders() {
        return orders;
    }

    public void setOrders(List<TOrders> orders) {
        this.orders = orders;
    }

    public BigDecimal getCoupon() {
        return coupon;
    }

    public void setCoupon(BigDecimal coupon) {
        this.coupon = coupon;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Map<String, Integer> getItemsStock() {
        return itemsStock;
    }

    public void setItemsStock(Map<String, Integer> itemsStock) {
        this.itemsStock = itemsStock;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public String toString() {
        return "GenerateOrderEvent{" +
                "orders=" + orders.toString() +
                ", coupon=" + coupon +
                ", score=" + score +
                ", itemsStock=" + itemsStock.toString() +
                ", latch=" + latch +
                ", flag='" + flag + '\'' +
                ", isTOrder=" + isTOrder +
                ", isItem=" + isItem +
                ", isCoupon=" + isCoupon +
                ", isScore=" + isScore +
                ", isStock=" + isStock +
                '}';
    }
}
