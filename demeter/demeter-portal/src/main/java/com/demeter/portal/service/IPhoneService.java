package com.demeter.portal.service;

import com.alibaba.fastjson.JSON;

public interface IPhoneService {

    JSON checkPhoneVerification(String phoneNumber, String code, String password);
    JSON sellPhoneVerification(String phoneNumber, String password);
    Boolean gotoLogin(String phoneNumber, String password);
}
