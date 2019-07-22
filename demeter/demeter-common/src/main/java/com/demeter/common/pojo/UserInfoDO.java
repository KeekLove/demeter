package com.demeter.common.pojo;

import java.io.Serializable;
import java.sql.Date;

/**
*@Description
*@Author 胡传威
*@DateTime 2019/7/20 2019/7/20
 *
 * 用户信息表
*/
public class UserInfoDO implements Serializable {
    private Long id;
    private int phone;
    private String password;
    private Date createTime;
    private Date updateTime;
    private int del;

    public UserInfoDO() {
    }

    public UserInfoDO(Long id, int phone, String password, Date createTime, Date updateTime, int del) {
        this.id = id;
        this.phone = phone;
        this.password = password;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "UserInfoDO{" +
                "id=" + id +
                ", phone=" + phone +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", del=" + del +
                '}';
    }
}
