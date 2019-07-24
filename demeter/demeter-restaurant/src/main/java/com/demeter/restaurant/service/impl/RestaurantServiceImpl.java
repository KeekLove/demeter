package com.demeter.restaurant.service.impl;

import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.restaurant.dao.IRestaurantDao;
import com.demeter.restaurant.pojo.FoodAndMenuDto;
import com.demeter.restaurant.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    @Autowired
    private IRestaurantDao iRestaurantDao;

    @Override
    public RestaurantInfoDO getResRestaurantInfo(String card) {
        return iRestaurantDao.findResRestaurantInfo(card);
    }

    @Override
    public List<FoodAndMenuDto> getListFood(Long restaurantId) {

        return iRestaurantDao.findListFood(restaurantId);
    }
}
