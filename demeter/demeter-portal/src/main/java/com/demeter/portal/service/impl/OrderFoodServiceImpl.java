package com.demeter.portal.service.impl;

import com.demeter.common.pojo.OrderFoodDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.dao.OrderFoodDao;
import com.demeter.portal.pojo.OrderFoodDTO;
import com.demeter.portal.service.IOrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
*@Description 查询历史账单
*@Author 陈龙鑫
*@DateTime 2019/7/23 2019/7/23
*/
import java.util.List;
@Service
public class OrderFoodServiceImpl implements IOrderFoodService {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private OrderFoodDao orderFoodDao;
    @Override
    public List<OrderFoodDTO> findOrderFoodDO(String pageSize, String pageNum, String token) {
       // Page<OrderFoodDO> page = PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        //jedisClient.hget("token","id");

        return orderFoodDao.findOrderFoodDO(pageSize, pageNum, token);
    }
}
