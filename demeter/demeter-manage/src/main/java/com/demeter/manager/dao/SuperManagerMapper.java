package com.demeter.manager.dao;

import com.demeter.common.pojo.ManagerInfoDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SuperManagerMapper {
    List<ManagerInfoDO> selectAll();
    int deleteById(Long id);
    int insert(ManagerInfoDO manager);
    int update(ManagerInfoDO manager);
    ManagerInfoDO selectOne(Long id);
}
