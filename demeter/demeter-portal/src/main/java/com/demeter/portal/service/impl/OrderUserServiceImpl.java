package com.demeter.portal.service.impl;

import com.demeter.common.pojo.OrderUserDO;
import com.demeter.common.pojo.PackageTypeDO;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.portal.dao.OrderUserDao;
import com.demeter.portal.pojo.OrderPackageDTO;
import com.demeter.portal.service.IOrderUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
*@Description 订单支付
*@Author 陈龙鑫
*@DateTime 2019/7/24 2019/7/24
*/
@Service
public class OrderUserServiceImpl implements IOrderUserService {
    @Autowired
    private OrderUserDao orderUserDao;
    @Override
    public OrderUserDO findByLastOne() {
        return orderUserDao.findByLastOne();
    }

    @Override
    public Integer insertOrderUser(OrderUserDO orderUserDO) {
        return orderUserDao.insertOrderUser(orderUserDO);
    }

    @Override
    public Integer updateOrderUser(OrderUserDO orderUserDO) {
        return orderUserDao.updateOrderUser(orderUserDO);
    }

    @Override
    public OrderPackageDTO findDate(OrderPackageDTO orderPackageDTO) {
        return orderUserDao.findDate(orderPackageDTO);
    }

    @Override
    public RestaurantInfoDO findStopTime(OrderPackageDTO orderPackageDTO) {
        return orderUserDao.findStopTime(orderPackageDTO);
    }

    @Override
    public Integer updateStopTime(RestaurantInfoDO restaurantInfoDO) {
        return orderUserDao.updateStopTime(restaurantInfoDO);
    }


}
