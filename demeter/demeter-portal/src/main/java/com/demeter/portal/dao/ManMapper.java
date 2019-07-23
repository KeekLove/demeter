package com.demeter.portal.dao;

import com.demeter.common.pojo.ManagerInfoDO;
import com.demeter.common.pojo.OrderFoodDO;
import com.demeter.common.pojo.UserInfoDO;
import com.demeter.portal.pojo.testDto;
import jdk.nashorn.internal.parser.Token;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManMapper {
    List<ManagerInfoDO> selectAll();

    List<ManagerInfoDO> selectById(@Param("id2") Integer id);
    List<ManagerInfoDO> selectById2(@Param("testDto") testDto testDto);
    List<OrderFoodDO> findOrderFoodDo(@Param("id") String pageNum, @Param(" pageSize")String pageSize, @Param("id")Token token);
   

}
