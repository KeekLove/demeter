package com.demeter.portal.pojo;

import java.io.Serializable;

public class RestaurantRegisterDTO implements Serializable {
    private int registerId;
    private String about;
    private String icon;
    private String name;
    private String province;
    private String city;
    private String address;

    private int del;
    private Long userId;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RestaurantRegisterDTO() {
    }

    public RestaurantRegisterDTO(int registerId, String about, String icon, String name, String province, String city, String address, int del, Long userId) {
        this.registerId = registerId;
        this.about = about;
        this.icon = icon;
        this.name = name;
        this.province = province;
        this.city = city;
        this.address = address;
        this.del = del;
        this.userId = userId;
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

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RestaurantRegisterDTO{" +
                "registerId=" + registerId +
                ", about='" + about + '\'' +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", del=" + del +
                ", userId=" + userId +
                '}';
    }
}
