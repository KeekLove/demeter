package com.demeter.common.pojo;

import java.io.Serializable;
import java.sql.Date;

/**
*@Description
*@Author 胡传威
*@DateTime 2019/7/20 2019/7/20
 *
 * 餐厅信息表
*/
public class RestaurantInfoDO implements Serializable {
    private Long id;
    private int registerId;
    private String about;
    private String icon;
    private String name;
    private String card;
    private String province;
    private String city;
    private String address;
    private String failReason;

    private Date stopTime;
    private Long userId;
    private Date createTime;
    private Date updateTime;
    private int del;

    public RestaurantInfoDO() {
    }

    public RestaurantInfoDO(Long id, int registerId, String about, String icon, String name, String card, String province, String city, String address, String failReason, Date stopTime, Long userId, Date createTime, Date updateTime, int del) {
        this.id = id;
        this.registerId = registerId;
        this.about = about;
        this.icon = icon;
        this.name = name;
        this.card = card;
        this.province = province;
        this.city = city;
        this.address = address;
        this.failReason = failReason;
        this.stopTime = stopTime;
        this.userId = userId;
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

    public int getRegisterId() {
        return registerId;
    }

    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        return "RestaurantInfoDO{" +
                "id=" + id +
                ", registerId=" + registerId +
                ", about='" + about + '\'' +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", card='" + card + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", failReason='" + failReason + '\'' +
                ", stopTime=" + stopTime +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", del=" + del +
                '}';
    }
}
