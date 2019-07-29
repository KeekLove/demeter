package com.demeter.portal.service.impl;

import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.portal.dao.RestaurantRegisterDao;
import com.demeter.portal.pojo.RestaurantRegisterDTO;
import com.demeter.portal.service.IRestaurantRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
*@Description 餐馆注册
*@Author 陈龙鑫
*@DateTime 2019/7/23 2019/7/23
*/
@Service
public class RestaurantRegisterServiceImpl implements IRestaurantRegisterService {
    @Autowired
    private RestaurantRegisterDao restaurantRegisterDao;

    /**
     * 餐馆注册
     * @param restaurantRegisterDTO
     * @return
     */
    @Override
    public Integer register(RestaurantRegisterDTO restaurantRegisterDTO) {
        return restaurantRegisterDao.register(restaurantRegisterDTO);
    }

    /**
     * 餐馆信息修改
     * @param restaurantRegisterDTO
     * @return
     */
    @Override
    public Integer update(RestaurantRegisterDTO restaurantRegisterDTO) {
        return restaurantRegisterDao.update(restaurantRegisterDTO);
    }

    /**
     * 餐馆信息回显
     * @param id
     * @return
     */
    @Override
    public RestaurantInfoDO findById(Long id) {
        return restaurantRegisterDao.findById(id);
    }
}
