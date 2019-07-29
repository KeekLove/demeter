package com.demeter.manager.pojo;

import com.demeter.common.pojo.RestaurantInfoDO;

import java.io.Serializable;
import java.util.List;

/**
*@Description 暂时无用
*@Author 曾锦铭
*@DateTime 7/24/2019 11:22 AM
*/

public class RestaurantActivation implements Serializable {
    private Long id;
    private Integer phone;
    private List<RestaurantInfoDO> restaurantInfoDO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public List<RestaurantInfoDO> getRestaurantInfoDO() {
        return restaurantInfoDO;
    }

    public void setRestaurantInfoDO(List<RestaurantInfoDO> restaurantInfoDO) {
        this.restaurantInfoDO = restaurantInfoDO;
    }

    @Override
    public String toString() {
        return "RestaurantActivation{" +
                "id=" + id +
                ", phone=" + phone +
                ", restaurantInfoDO=" + restaurantInfoDO +
                '}';
    }
}
