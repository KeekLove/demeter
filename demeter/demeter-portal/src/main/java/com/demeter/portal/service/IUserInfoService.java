package com.demeter.portal.service;

import com.demeter.portal.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface IUserInfoService {
    /**
    *@Description 显示个人信息
    *@Author 胡传威
    *@DateTime 2019/7/23 2019/7/23
    */
    UserInfo FindListUserInfoDao(Integer userid);

    Integer FindListnumRestautant(Integer userid);

    Integer FindListnumOrder(Integer state);
}
