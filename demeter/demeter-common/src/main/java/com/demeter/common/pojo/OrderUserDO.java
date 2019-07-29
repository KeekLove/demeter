package com.demeter.common.pojo;

import java.io.Serializable;
import java.sql.Date;

/**
*@Description
*@Author 胡传威
*@DateTime 2019/7/20 2019/7/20
 *
 * 用户订单表
*/
public class OrderUserDO implements Serializable {
    private Long id;
    private String orderId;
    private Long restaurantId;
    private Long userId;
    private Long packageId;
    private Double prize;
    private Date createTime;
    private Date updateTime;
    private int del;

    public OrderUserDO() {
    }

    public OrderUserDO(Long id, String orderId, Long restaurantId, Long userId, Long packageId, Double prize, Date createTime, Date updateTime, int del) {
        this.id = id;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.packageId = packageId;
        this.prize = prize;
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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
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
        return "OrderUserDO{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", restaurantId=" + restaurantId +
                ", userId=" + userId +
                ", packageId=" + packageId +
                ", prize=" + prize +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", del=" + del +
                '}';
    }
}
