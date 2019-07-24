package com.demeter.restaurant.dao;

import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.restaurant.pojo.FoodAndMenuDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*@Description 对餐馆信息的持久层数据操作
*@Author 刘海亮
*@DateTime 2019/7/23 21:58
*/
@Repository
public interface IRestaurantDao {
    /**
     *@Description 获取餐馆全部的信息，根据授权码card
     *@Author 刘海亮
     *@DateTime 2019/7/23 21:52
     */
    RestaurantInfoDO findResRestaurantInfo(@Param("card") String card);
    /**
    *@Description 找到餐馆的餐单和对应的食物
    *@Author 刘海亮
    *@DateTime 2019/7/24 9:06
    */
    List<FoodAndMenuDto> findListFood(@Param("restaurantId") Long restaurantId);
}
