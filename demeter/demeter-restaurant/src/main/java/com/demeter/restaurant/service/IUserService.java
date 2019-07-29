package com.demeter.restaurant.service;

/**
*@Description 餐厅方用户管理
*@Author 刘海亮
*@DateTime 2019/7/25 16:51
*/
public interface IUserService {
    boolean checkLogin(String phone,String card);
}
