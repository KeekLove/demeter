package com.demeter.common.pojo;

import java.io.Serializable;
import java.sql.Date;

/**
*@Description·
*@Author 胡传威
*@DateTime 2019/7/20 2019/7/20
 *
 *食品订单表
*/
public class OrderFoodDO implements Serializable {
    private Long id;
    private String orderId;
    private Double prize;
    private int seatNum;
    private Long restaurantId;
    private String foodContext;
    private String remark;
    private int state;
    private Date createTime;
    private Date updateTime;
    private int del;

    public OrderFoodDO() {
    }

    public OrderFoodDO(Long id, String orderId, Double prize, int seatNum, Long restaurantId, String foodContext, String remark, int state, Date createTime, Date updateTime, int del) {
        this.id = id;
        this.orderId = orderId;
        this.prize = prize;
        this.seatNum = seatNum;
        this.restaurantId = restaurantId;
        this.foodContext = foodContext;
        this.remark = remark;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.del = del;
    }

    public Double getPrize() {
        return prize;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getFoodContext() {
        return foodContext;
    }

    public void setFoodContext(String foodContext) {
        this.foodContext = foodContext;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    @Override
    public String toString() {
        return "OrderFoodDO{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", prize=" + prize +
                ", seatNum=" + seatNum +
                ", restaurantId=" + restaurantId +
                ", foodContext='" + foodContext + '\'' +
                ", remark='" + remark + '\'' +
                ", state=" + state +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", del=" + del +
                '}';
    }
}
