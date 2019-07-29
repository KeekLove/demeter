package com.demeter.portal.service;

import com.demeter.common.pojo.RestaurantInfoDO;
import org.apache.ibatis.annotations.Param;
import org.junit.Before;
import org.springframework.stereotype.Service;

import java.util.List;
/**
*@Description 
*@Author 胡传威
*@DateTime 2019/7/22 2019/7/22
*/

public interface IRestaurantInfoService {
    /**
    *@Description 获取餐馆信息
    *@Author 胡传威
    *@DateTime 2019/7/22 2019/7/22
    */
    List<RestaurantInfoDO> getRestaurantInfo(Integer userid);
    /**
    *@Description 根据餐厅id查找显示餐厅信息
    *@Author 胡传威
    *@DateTime 2019/7/22 2019/7/22
    */
    List<RestaurantInfoDO> getRestaurantInfoById(Integer id);
    



}

