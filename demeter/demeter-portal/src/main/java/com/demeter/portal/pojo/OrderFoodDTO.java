package com.demeter.portal.pojo;

import com.demeter.common.pojo.OrderFoodDO;
import com.demeter.portal.dao.OrderFoodDao;

import java.io.Serializable;
import java.sql.Date;
/**
*@Description 自定义数据内容展示实体类
*@Author 陈龙鑫
*@DateTime 2019/7/23 2019/7/23
*/
public class OrderFoodDTO implements Serializable {
    private Long id;
    private String orderId;
    private int seatNum;
    private Long restaurantId;
    private String foodContext;
    private Date createTime;
    private String name;
    private Double prize;

    public OrderFoodDTO() {
    }

    public OrderFoodDTO(Long id, String orderId, int seatNum, Long restaurantId, String foodContext, Date createTime, String name, Double prize) {
        this.id = id;
        this.orderId = orderId;
        this.seatNum = seatNum;
        this.restaurantId = restaurantId;
        this.foodContext = foodContext;
        this.createTime = createTime;
        this.name = name;
        this.prize = prize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getFoodContext() {
        return foodContext;
    }

    public void setFoodContext(String foodContext) {
        this.foodContext = foodContext;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrize() {
        return prize;
    }

    public void setPrize(Double prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "OrderFoodDTO{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", seatNum=" + seatNum +
                ", restaurantId=" + restaurantId +
                ", foodContext='" + foodContext + '\'' +
                ", createTime=" + createTime +
                ", name='" + name + '\'' +
                ", prize=" + prize +
                '}';
    }
}
