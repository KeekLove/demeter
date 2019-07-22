package com.demeter.common.pojo;

import java.io.Serializable;
import java.sql.Date;

/**
*@Description
*@Author 胡传威
*@DateTime 2019/7/20 2019/7/20
 *
 * 菜单信息表
*/
public class MenuInfoDO implements Serializable {
    private Long id;
    private String name;
    private Long restaurantId;
    private Date createTime;
    private Date updateTime;
    private int del;

    public MenuInfoDO() {
    }

    public MenuInfoDO(Long id, String name, Long restaurantId, Date createTime, Date updateTime, int del) {
        this.id = id;
        this.name = name;
        this.restaurantId = restaurantId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "MenuInfoDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", restaurantId=" + restaurantId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", del=" + del +
                '}';
    }
}
