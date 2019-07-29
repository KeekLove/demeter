package com.demeter.portal.dao;


import com.demeter.common.pojo.UserInfoDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PhoneMapperDao {
    /**
     * 用户添加
     * @return
     */
    Integer insertPhone(@Param("phoneNumber") String phoneNumber, @Param("password") String password);
    List<UserInfoDO> selectAll();
    UserInfoDO selectUserByid(@Param("phone") String phone);
}
