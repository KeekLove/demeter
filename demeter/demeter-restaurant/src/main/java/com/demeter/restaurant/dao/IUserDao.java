package com.demeter.restaurant.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface IUserDao {
    Date getStopDateByPhoneAndCard(@Param("phone") String phone, @Param("card") String card);
}
