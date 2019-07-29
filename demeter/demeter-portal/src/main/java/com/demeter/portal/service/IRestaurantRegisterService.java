package com.demeter.portal.service;


import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.portal.pojo.RestaurantRegisterDTO;

/**
*@Description 餐馆注册
*@Author 陈龙鑫
*@DateTime 2019/7/23 2019/7/23
*/
public interface IRestaurantRegisterService {
    /**
     * 餐馆注册
     * @param restaurantRegisterDTO
     * @return
     */
    Integer register(RestaurantRegisterDTO restaurantRegisterDTO);

    /**
     * 餐馆信息修改
     * @param restaurantRegisterDTO
     * @return
     */
    Integer update(RestaurantRegisterDTO restaurantRegisterDTO);

    /**
     * 餐馆信息回显
     * @param id
     * @return
     */
    RestaurantInfoDO findById(Long id);
}
