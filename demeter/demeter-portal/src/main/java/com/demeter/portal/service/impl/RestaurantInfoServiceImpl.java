package com.demeter.portal.service.impl;

import com.demeter.common.pojo.ManagerInfoDO;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.portal.dao.RestaurantDao;
import com.demeter.portal.service.IRestaurantInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RestaurantInfoServiceImpl implements IRestaurantInfoService {
    @Autowired
        private RestaurantDao restaurantDao;
    /**
    *@Description 获取餐馆信息
    *@Author 胡传威
    *@DateTime 2019/7/22 2019/7/22
    */
    @Override
    public List<RestaurantInfoDO> getRestaurantInfo(Integer userid) {

        List<RestaurantInfoDO> list = restaurantDao.findListRestaurantInfo(userid);
        return list;
    }
    /**
    *@Description 根据餐厅id查找显示餐厅信息
    *@Author 胡传威
    *@DateTime 2019/7/22 2019/7/22
    */
    @Override
    public List<RestaurantInfoDO> getRestaurantInfoById(Integer id) {
        return restaurantDao.findListRestaurantInfoById(id);
    }


}
