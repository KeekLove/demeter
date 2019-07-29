package com.demeter.portal.dao;

import com.demeter.common.pojo.RestaurantInfoDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
*@Description 
*@Author 胡传威
*@DateTime 2019/7/22 2019/7/22
*/
@Repository
public interface RestaurantDao {

    /**
    *@Description 从数据库单表查询餐馆信息根据用户id
    *@Author 胡传威
    *@DateTime 2019/7/22 2019/7/22
    */
    List<RestaurantInfoDO> findListRestaurantInfo(@Param("userid") Integer userid);
    /**
    *@Description 根据id查询餐馆信息
    *@Author 胡传威
    *@DateTime 2019/7/23 2019/7/23
    */
    List<RestaurantInfoDO> findListRestaurantInfoById(Integer id);
    /**
    *@Description
    *@Author 胡传威
    *@DateTime 2019/7/23 2019/7/23
    */

}
