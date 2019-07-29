package com.demeter.portal.dao;

import com.demeter.portal.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
*@Description 
*@Author 胡传威
*@DateTime 2019/7/23 2019/7/23
*/
@Repository
public interface FindUserInfoDao {
    UserInfo FindListUserInfoDao(@Param("userid") Integer userid);
    Integer FindListnumRestautant(@Param("id") Integer userid);
    Integer FindListnumOrder(@Param("state") Integer state);
}
