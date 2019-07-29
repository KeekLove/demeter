package com.demeter.restaurant.service.impl;

import com.demeter.restaurant.dao.IUserDao;
import com.demeter.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
*@Description 对用户的业务操做
*@Author 刘海亮
*@DateTime 2019/7/25 17:04
*/
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao iUserDao;
    /**
    *@Description 登录验证
    *@Author 刘海亮
    *@DateTime 2019/7/25 17:04
    */
    @Override
    public boolean checkLogin(String phone, String card) {
        // 匹配授权码，获取截至时间，如果截至时间为null，则表示card和phone不匹配
        Date stopDate=iUserDao.getStopDateByPhoneAndCard(phone,card);
        if (stopDate==null){
            return false;
        }
        Long stop = stopDate.getTime();
        java.util.Date nowDate=new java.util.Date();
        Long now = nowDate.getTime();
        if (stop>now) {
            return true;
        }
        return false;
    }
}
