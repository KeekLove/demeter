package com.demeter.common.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

/**
*@Description
*@Author 胡传威
*@DateTime 2019/7/20 2019/7/20
 *
 *管理员信息表
 * privilege 指管理员权限级别 ： 0为超级管理员，数字越大管理权限越低
*/
public class ManagerInfoDO implements Serializable {
    private Long id;
    private String username;
    private String password;
    private int privilege;
    private Date createTime;
    private Date updateTime;
    private int del;

    public ManagerInfoDO(Long id, String username, String password, int privilege, Date createTime, Date updateTime, int del) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.privilege = privilege;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.del = del;
    }

    public ManagerInfoDO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
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
        return "ManagerInfoDO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", privilege=" + privilege +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", del=" + del +
                '}';
    }
}
