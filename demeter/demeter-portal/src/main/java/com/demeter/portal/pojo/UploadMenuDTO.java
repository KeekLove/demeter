package com.demeter.portal.pojo;


import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class UploadMenuDTO implements Serializable {
    private String icon;
    private  Float prize;
    private String name;
    private String about;
    private String models;
    private Long menuId;
    private Long restaurantId;


    public UploadMenuDTO() {
    }

    public UploadMenuDTO(String icon, Float prize, String name, String about, String models, Long menuId, Long restaurantId) {
        this.icon = icon;
        this.prize = prize;
        this.name = name;
        this.about = about;
        this.models = models;
        this.menuId = menuId;
        this.restaurantId = restaurantId;
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

    @Override
    public String toString() {
        return "UploadMenuDTO{" +
                "icon='" + icon + '\'' +
                ", prize=" + prize +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", models='" + models + '\'' +
                ", menuId=" + menuId +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
