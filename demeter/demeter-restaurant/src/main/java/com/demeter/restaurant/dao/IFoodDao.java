package com.demeter.restaurant.dao;

import com.demeter.common.pojo.OrderFoodDO;
import com.demeter.common.pojo.RestaurantInfoDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*@Description 对餐单的持久操作
*@Author 刘海亮
*@DateTime 2019/7/25 9:23
*/
@Repository
public interface IFoodDao {
    /**
    *@Description 获取当前餐品账单的最大订单
    *@Author 刘海亮
    *@DateTime 2019/7/25 9:23
    */
    Long findOrderFoodMaxId();
    /**
    *@Description 插入待支付订单
    *@Author 刘海亮
    *@DateTime 2019/7/25 10:28
    */
    Integer addOrder(@Param("id") Long id,@Param("seat_num") Integer seat_num,@Param("card") String card,
                     @Param("food_context")String food_context
                        ,@Param("remark")String remark
                        ,@Param("prize")String prize );
    
    /**
    *@Description 根据id修改订单的状态status
    *@Author 刘海亮
    *@DateTime 2019/7/25 11:08
    */
    Integer updateOrderStatus(@Param("id")Long id,@Param("status")Integer status);
    
    /**
    *@Description 修改待支付的支付宝订单，并且把状态改为已支付
    *@Author 刘海亮
    *@DateTime 2019/7/25 11:09
    */
    Integer updateTrade_no(@Param("id")Long id,@Param("order_id")String order_id);

    /**
    *@Description 根据订单号的id获取餐馆的全部信息
    *@Author 刘海亮
    *@DateTime 2019/7/25 14:40
    */
    RestaurantInfoDO getRestaurantInfoByOrderId(@Param("id")Long id);
    
    /**
    *@Description 根据餐馆id获取全部有关订单属于具有
    *@Author 刘海亮
    *@DateTime 2019/7/26 10:15
    */
    List<OrderFoodDO> getListOrder(@Param("restaurantId") Long id);
}
