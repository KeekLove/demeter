package com.demeter.portal.pojo;

import java.sql.Date;

public class OrderPackageDTO {
    private Long id;
    private String orderId;
    private Long restaurantId;
    private Long userId;
    private Long packageId;
    private int avticeNum;
    private int prize;
    private Date createTime;
    private Date updateTime;
    private int del;

    public OrderPackageDTO() {
    }

    public OrderPackageDTO(Long id, String orderId, Long restaurantId, Long userId, Long packageId, int avticeNum, int prize, Date createTime, Date updateTime, int del) {
        this.id = id;
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.packageId = packageId;
        this.avticeNum = avticeNum;
        this.prize = prize;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.del = del;
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

    public int getAvticeNum() {
        return avticeNum;
    }

    public void setAvticeNum(int avticeNum) {
        this.avticeNum = avticeNum;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
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
        return "OrderPackageDTO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", restaurantId=" + restaurantId +
                ", userId=" + userId +
                ", packageId=" + packageId +
                ", avticeNum=" + avticeNum +
                ", prize=" + prize +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", del=" + del +
                '}';
    }
}
