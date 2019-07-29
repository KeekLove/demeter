package com.demeter.portal.pojo;

import java.io.Serializable;

public class SelectMenuDTO implements Serializable {
    private String name;//套餐名
    private Long menuId;//套餐id

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "SelectMenuDTO{" +
                "name='" + name + '\'' +
                ", menuId=" + menuId +
                '}';
    }
}
