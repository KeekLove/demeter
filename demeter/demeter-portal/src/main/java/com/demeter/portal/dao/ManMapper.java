package com.demeter.portal.dao;

import com.demeter.common.pojo.ManagerInfoDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManMapper {
    List<ManagerInfoDO> selectAll();

}
