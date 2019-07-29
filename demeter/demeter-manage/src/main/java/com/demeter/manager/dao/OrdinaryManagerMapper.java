package com.demeter.manager.dao;

import com.demeter.common.pojo.OrderUserDO;
import com.demeter.common.pojo.PackageTypeDO;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.common.pojo.UserInfoDO;
import com.demeter.manager.pojo.PackageList;
import com.demeter.manager.pojo.RestaurantActivation;
import com.demeter.manager.pojo.RestaurantList;
import com.demeter.manager.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdinaryManagerMapper {
    /**
     * @Description 获取餐厅实例列表
     * @Author 曾锦铭
     * @DateTime 7/24/2019 2:25 PM
     */
    List<RestaurantList> selectResList();

    /**
     * @Description 获取订单列表
     * @Author 曾锦铭
     * @DateTime 7/24/2019 3:20 PM
     */

    List<PackageList> selectPackage();

    /**
    *@Description 更新餐馆表部分字段
    *@Author 曾锦铭
    *@DateTime 7/24/2019 4:37 PM
    */
    int reviewRes(RestaurantInfoDO restaurantInfoDO);

    User selectUser(String username);

    RestaurantInfoDO selectRestaurantById(Long id);
}