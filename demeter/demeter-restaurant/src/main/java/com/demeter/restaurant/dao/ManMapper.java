package com.demeter.restaurant.dao;

import com.demeter.common.pojo.ManagerInfoDO;
import com.demeter.common.pojo.OrderFoodDO;
import jdk.nashorn.internal.parser.Token;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManMapper {
    List<ManagerInfoDO> selectAll();

    List<ManagerInfoDO> selectById(@Param("id2") Integer id);

}
