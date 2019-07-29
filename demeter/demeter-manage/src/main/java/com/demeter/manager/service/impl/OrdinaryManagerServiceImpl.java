package com.demeter.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demeter.common.pojo.OrderUserDO;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.common.pojo.UserInfoDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.manager.dao.OrdinaryManagerMapper;
import com.demeter.manager.pojo.PackageList;
import com.demeter.manager.pojo.RestaurantList;
import com.demeter.manager.service.OrdinaryManagerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrdinaryManagerServiceImpl implements OrdinaryManagerService {
    @Autowired
    JedisClient jedisClient;
    @Autowired
    OrdinaryManagerMapper managerMapper;

    @Override
    public JSON getOrderUsersPage(String token, Integer pageNum) {
        return null;
    }

    @Override
    public JSON getOneOrderUser(String token, Long id) {
        return null;
    }

    @Override
    public JSON updateOrderUser(String token, Long id, Long restaurantId, Long orderId, Long userId, Long packageId, Integer del) {
        return null;
    }

    @Override
    public JSON getPackagesPage(String token, Integer pageNum) {
        Page<PackageList> objects = PageHelper.startPage(pageNum, 2);
        List<PackageList> packageLists = managerMapper.selectPackage();
        PageInfo pageInfo = new PageInfo(packageLists);
        String jsonString = JSON.toJSONString(pageInfo);
        return JSON.parseObject(jsonString);
    }

    @Override
    public JSON getOnesPackagePage(String token, Integer pageNum, Long userId) {
        return null;
    }

    @Override
    public JSON getOnePackage(String token, Long id) {
        return null;
    }

    @Override
    public JSON updatePackage(String token, Long id, Integer prize, Integer del) {
        return null;
    }

    @Override
    public JSON deletePackage(String token, Long id) {
        return null;
    }

    @Override
    public JSON addPackage(String token, Long id, Integer prize, Integer del) {
        return null;
    }

    /**
    *@Description 获取餐厅列表
    *@Author 曾锦铭
    *@DateTime 7/24/2019 3:07 PM
    */
    @Override
    public JSON getAllRestaurantsPage(String token, Integer pageNum) {
        /**
        *7/24/2019 2:58 PM  TODO: token验证
        */
        Page<RestaurantList> page = PageHelper.startPage(pageNum, 2);
        List<RestaurantList> restaurantLists = managerMapper.selectResList();
        PageInfo pageInfo = new PageInfo(restaurantLists);
        String jsonString = JSON.toJSONString(pageInfo);
        return JSON.parseObject(jsonString);
    }

    @Override
    public JSON getOnesRestaurantsPage(String token, Integer pageNum, Long userId) {
        return null;
    }

    @Override
    public JSON getOneRestaurant(String token, Long id) {
        return null;
    }

    @Override
    public JSON updateRestaurant(String token, Long id, Integer register_id, String about, String icon, String name, String card, String province, String city, String address, String failReason, Date stopTime, Long userId, Integer del) {
        return null;
    }

    @Override
    public JSON updateCard(String token, Long id, String card) {
        RestaurantInfoDO restaurantInfoDO = new RestaurantInfoDO();
        restaurantInfoDO.setId(id);
        restaurantInfoDO.setCard(card);
        restaurantInfoDO.setDel(1);
        int i = managerMapper.reviewRes(restaurantInfoDO);
        return JSON.parseObject("{'affected':'"+String.valueOf(i)+"'}");
    }

    @Override
    public JSON refuesRes(String token, Long id, String failReason) {
        RestaurantInfoDO restaurantInfoDO = new RestaurantInfoDO();
        restaurantInfoDO.setId(id);
        restaurantInfoDO.setFailReason(failReason);
        restaurantInfoDO.setDel(1);
        int i = managerMapper.reviewRes(restaurantInfoDO);
        return JSON.parseObject("{'affected':'"+String.valueOf(i)+"'}");
    }

    @Override
    public JSON deleteRestaurant(String token, Long id) {
        return null;
    }

    @Override
    public JSON generateCard(String token, Long resId) {
        RestaurantInfoDO restaurantInfoDO = managerMapper.selectRestaurantById(resId);
        restaurantInfoDO.setCard(UUID.randomUUID().toString());
        int i = managerMapper.reviewRes(restaurantInfoDO);
        return JSON.parseObject("{'affected':'"+String.valueOf(i)+"'}");
    }

    @Override
    public JSON freezeRes(String token, Long resId) {
        RestaurantInfoDO restaurantInfoDO = managerMapper.selectRestaurantById(resId);
        restaurantInfoDO.setCard("null");
        int i = managerMapper.reviewRes(restaurantInfoDO);
        return JSON.parseObject("{'affected':'"+String.valueOf(i)+"'}");
    }

    @Override
    public JSON updateExp(String token, Long resId, Date date) {
        RestaurantInfoDO restaurantInfoDO = managerMapper.selectRestaurantById(resId);
        System.out.println(date+"---+++___");
        restaurantInfoDO.setStopTime(date);
        int i = managerMapper.reviewRes(restaurantInfoDO);
        return JSON.parseObject("{'affected':'"+String.valueOf(i)+"'}");
    }

}
