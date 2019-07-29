package com.demeter.portal.pojo;

import java.io.Serializable;
import java.util.Date;

public class FindMenuByIdDTO implements Serializable {

    private Long foodid;//菜品的id
    private String resname;//所在餐馆的名字
    private String foodname;//菜品的名字
    private String icon;//菜品的图片
    private Float prize;//  菜品的价格
    private Long menuid;//所属于的目录id
    private String menuname;//所属于的目录名字
    private Date createtime;//创建日期

    public Long getFoodid() {
        return foodid;
    }

    public void setFoodid(Long foodid) {
        this.foodid = foodid;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Float getPrize() {
        return prize;
    }

    public void setPrize(Float prize) {
        this.prize = prize;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "FindMenuByIdDTO{" +
                "foodid=" + foodid +
                ", resname='" + resname + '\'' +
                ", foodname='" + foodname + '\'' +
                ", icon='" + icon + '\'' +
                ", prize=" + prize +
                ", menuid=" + menuid +
                ", menuname='" + menuname + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
