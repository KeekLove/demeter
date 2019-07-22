package com.demeter.common.pojo;

import java.io.Serializable;
import java.sql.Date;
/**
*@Description
*@Author 胡传威
*@DateTime 2019/7/20 2019/7/20
 *
 * 食品信息表
 *
*/
public class FoodInfoDO implements Serializable {
    private Long id;
    private String icon;
    private int prize;
    private String name;
    private String about;
    private String models;
    private Long menuId;
    private Long restaurantId;
    private Date createTime;
    private Date updateTime;
    private int del;

    /**
     * 空参有参构造
     */
    public FoodInfoDO() {
    }

    public FoodInfoDO(Long id, String icon, int prize, String name, String about, String models, Long menuId, Long restaurantId, Date createTime, Date updateTime, int del) {
        this.id = id;
        this.icon = icon;
        this.prize = prize;
        this.name = name;
        this.about = about;
        this.models = models;
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.del = del;
    }

    /**
     * get set 方法实现
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
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
        return "FoodInfoDO{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", prize=" + prize +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", models='" + models + '\'' +
                ", menuId=" + menuId +
                ", restaurantId=" + restaurantId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", del=" + del +
                '}';
    }
}
