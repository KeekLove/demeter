package com.demeter.restaurant.service;

import com.demeter.common.pojo.OrderFoodDO;
import com.demeter.common.pojo.RestaurantInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*@Description 处理餐单业务
*@Author 刘海亮
*@DateTime 2019/7/25 9:09
*/
public interface IFoodService {
    /**
    *@Description 获取食物账单的最后id
    *@Author 刘海亮
    *@DateTime 2019/7/25 9:13
    */
    Long getOrderFoodMaxId();
    
    /**
    *@Description 插入待付款账单
    *@Author 刘海亮
    *@DateTime 2019/7/25 10:54
    */
    Integer setOrder(Long id,Integer seat_num,String card,String food_context,String remark,String prize);
    /**
    *@Description 更改订单状态
    *@Author 刘海亮
    *@DateTime 2019/7/25 11:03
    */
    Integer setOrderStatus(Long id,Integer status);

    /**
    *@Description   通过一方订单号插入支付宝订单号
    *@Author 刘海亮
    *@DateTime 2019/7/25 11:04
    */
    Integer setTrade_no(Long id,String order_id);
    /**
    *@Description 根据订单号的id获取餐馆的全部信息
    *@Author 刘海亮
    *@DateTime 2019/7/25 14:47
    */
    RestaurantInfoDO getRestaurantInfoByOrderId(Long id);
    /**
    *@Description 获取正在等待完成的订单
    *@Author 刘海亮
    *@DateTime 2019/7/26 10:18
    */
    List<OrderFoodDO> getListOrder(Long id);
}
