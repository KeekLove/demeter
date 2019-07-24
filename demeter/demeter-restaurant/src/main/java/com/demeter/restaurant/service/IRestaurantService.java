package com.demeter.restaurant.service;

import com.demeter.common.pojo.FoodInfoDO;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.restaurant.pojo.FoodAndMenuDto;

import java.util.List;

/**
*@Description 餐馆信息业务处理接口
*@Author 刘海亮
*@DateTime 2019/7/23 21:49
*/
public interface IRestaurantService {
    /**
    *@Description 获取餐馆全部的信息，根据授权码card
    *@Author 刘海亮
    *@DateTime 2019/7/23 21:52
    */
    RestaurantInfoDO getResRestaurantInfo(String card);
    
    /**
    *@Description 获取该餐馆的全部菜品信息
    *@Author 刘海亮
    *@DateTime 2019/7/23 22:10
    */
    List<FoodAndMenuDto> getListFood(Long restaurantId);
}
