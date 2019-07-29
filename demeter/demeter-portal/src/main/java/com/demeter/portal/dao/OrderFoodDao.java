package com.demeter.portal.dao;

        import com.demeter.common.pojo.OrderFoodDO;
        import com.demeter.portal.pojo.OrderFoodDTO;
        import jdk.nashorn.internal.parser.Token;
        import org.apache.ibatis.annotations.Param;
        import org.springframework.stereotype.Repository;

        import java.util.List;
/**
*@Description 查询历史账单
*@Author 陈龙鑫
*@DateTime 2019/7/22 2019/7/22
*/
@Repository
public interface OrderFoodDao {
    List<OrderFoodDTO> findOrderFoodDO(String pageSize, String pageNum, @Param("id") String token);
}
