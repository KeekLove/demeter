package com.demeter.common.pojo;

import java.io.Serializable;
import java.sql.Date;

/**
*@Description
*@Author 胡传威
*@DateTime 2019/7/20 2019/7/20
 *
 * 套餐购买信息表
*/
public class PackageTypeDO implements Serializable {
    private Long id;
    private int avticeNum;
    private int prize;

    private Date createTime;
    private Date updateTime;
    private int del;

    public PackageTypeDO() {
    }

    public PackageTypeDO(Long id, int avticeNum, int prize, Date createTime, Date updateTime, int del) {
        this.id = id;
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
        return "PackageTypeDO{" +
                "id=" + id +
                ", avticeNum=" + avticeNum +
                ", prize=" + prize +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", del=" + del +
                '}';
    }
}
