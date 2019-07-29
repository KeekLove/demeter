package com.demeter.portal.service;

import com.demeter.common.pojo.OrderFoodDO;
import com.demeter.portal.pojo.OrderFoodDTO;
import jdk.nashorn.internal.parser.Token;
import org.springframework.stereotype.Service;

import java.util.List;
/**
*@Description 查询历史账单
*@Author 陈龙鑫
*@DateTime 2019/7/23 2019/7/23
*/
public interface IOrderFoodService {
    List<OrderFoodDTO> findOrderFoodDO(String pageSize, String pageNum, String token);
}
