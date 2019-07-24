package com.demeter.restaurant.pojo;

import com.demeter.common.pojo.FoodInfoDO;

import java.io.Serializable;
import java.util.List;

public class FoodAndMenuDto implements Serializable {
    // 餐单的id
    private Long id;
    // 餐单的名字
    private String name;
    // 菜品
    private List<FoodInfoDO> foodInfoDO;

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

    public List<FoodInfoDO> getFoodInfoDO() {
        return foodInfoDO;
    }

    public void setFoodInfoDO(List<FoodInfoDO> foodInfoDO) {
        this.foodInfoDO = foodInfoDO;
    }

    public FoodAndMenuDto() {
    }

    public FoodAndMenuDto(Long id, String name, List<FoodInfoDO> foodInfoDO) {
        this.id = id;
        this.name = name;
        this.foodInfoDO = foodInfoDO;
    }

    @Override
    public String toString() {
        return "FoodAndMenuDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foodInfoDO=" + foodInfoDO +
                '}';
    }
}
