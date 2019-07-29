package com.demeter.manager.service;

import com.alibaba.fastjson.JSON;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.common.pojo.UserInfoDO;

import java.sql.Date;
import java.util.List;

public interface OrdinaryManagerService {
    //操作用户数据
    JSON getOrderUsersPage(String token, Integer pageNum);
    JSON getOneOrderUser(String token, Long id);
    JSON updateOrderUser(String token, Long id, Long restaurantId, Long orderId, Long userId, Long packageId, Integer del);
    //操作用户数据

    //操作套餐数据
    JSON getPackagesPage(String token, Integer pageNum);
    JSON getOnesPackagePage(String token, Integer pageNum, Long userId);
    JSON getOnePackage(String token, Long id);
    JSON updatePackage(String token, Long id, Integer prize, Integer del);
    JSON deletePackage(String token, Long id);
    JSON addPackage(String token, Long id, Integer prize, Integer del);
    //操作套餐数据

    //操作餐馆实例
    JSON getAllRestaurantsPage(String token, Integer pageNum);
    JSON getOnesRestaurantsPage(String token, Integer pageNum, Long userId);
    JSON getOneRestaurant(String token, Long id);
    JSON updateRestaurant(String token, Long id, Integer register_id, String about,
                          String icon, String name, String card, String province,
                          String city, String address, String failReason, Date stopTime,
                          Long userId, Integer del);
    JSON updateCard(String token, Long id, String card);
    JSON refuesRes(String token, Long id, String failReason);
    JSON deleteRestaurant(String token, Long id);

    JSON generateCard(String token, Long resId);

    JSON freezeRes(String token, Long resId);

    JSON updateExp(String token, Long resId, Date date);
    //操作餐馆实例
}
