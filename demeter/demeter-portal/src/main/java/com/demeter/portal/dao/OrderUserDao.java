package com.demeter.portal.dao;

import com.demeter.common.pojo.OrderUserDO;
import com.demeter.common.pojo.PackageTypeDO;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.portal.pojo.OrderPackageDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
*@Description 支付订单
*@Author 陈龙鑫
*@DateTime 2019/7/24 2019/7/24
*/
@Repository
public interface OrderUserDao {
    /**
     * 查询最后一条数据
     * @return 数据库最新一条数据
     */
    OrderUserDO findByLastOne();

    /**
     *  添加用户订单
     * @param orderUserDO
     * @return 受影响行数
     */
    Integer insertOrderUser(@Param("orderUserDo") OrderUserDO orderUserDO);

    /**
     *  更新用户订单
     * @param orderUserDO
     * @return
     */
    Integer updateOrderUser(@Param("orderUserDo") OrderUserDO orderUserDO);

    /**
     * 查询套餐类型
     * @param orderPackageDTO
     * @return
     */
    OrderPackageDTO findDate(@Param("orderPackageDTO") OrderPackageDTO orderPackageDTO);

    /**
     * 查询到期时间
     * @param orderPackageDTO
     * @return
     */
    RestaurantInfoDO findStopTime(@Param("orderPackageDTO") OrderPackageDTO orderPackageDTO);
    /**
     * 修改restaurantInfo表订单时间
     * @return
     */
    Integer updateStopTime(@Param("restaurantInfoDO") RestaurantInfoDO restaurantInfoDO);

}
