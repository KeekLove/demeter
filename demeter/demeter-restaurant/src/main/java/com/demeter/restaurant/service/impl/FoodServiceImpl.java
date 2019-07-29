package com.demeter.restaurant.service.impl;

import com.demeter.common.pojo.OrderFoodDO;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.restaurant.dao.IFoodDao;
import com.demeter.restaurant.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class FoodServiceImpl implements IFoodService {
    @Autowired
    private IFoodDao iFoodDao;
    @Override
    public Long getOrderFoodMaxId() {
        return iFoodDao.findOrderFoodMaxId();
    }

    @Override
    public Integer setOrder(Long id, Integer seat_num, String card, String food_context, String remark,String prize) {
        return iFoodDao.addOrder(id, seat_num,card,food_context,remark, prize);
    }

    @Override
    public Integer setOrderStatus(Long id, Integer status) {
        return iFoodDao.updateOrderStatus(id,status) ;
    }

    @Override
    public Integer setTrade_no(Long id, String order_id) {
        iFoodDao.updateOrderStatus(id,1);
        return iFoodDao.updateTrade_no(id,order_id);
    }

    @Override
    public RestaurantInfoDO getRestaurantInfoByOrderId(Long id) {
        return iFoodDao.getRestaurantInfoByOrderId(id);
    }

    @Override
    public List<OrderFoodDO> getListOrder(Long id) {
        return iFoodDao.getListOrder(id);
    }
}
