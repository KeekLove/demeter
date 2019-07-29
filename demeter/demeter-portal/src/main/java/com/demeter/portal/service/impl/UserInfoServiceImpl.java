package com.demeter.portal.service.impl;

import com.demeter.portal.dao.FindUserInfoDao;
import com.demeter.portal.pojo.UserInfo;
import com.demeter.portal.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.ldap.PagedResultsControl;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private FindUserInfoDao findUserInfoDao;

    @Override
    public UserInfo FindListUserInfoDao(Integer userid) {
        return findUserInfoDao.FindListUserInfoDao(userid);
    }

    @Override
    public Integer FindListnumRestautant(Integer userid) {
        return findUserInfoDao.FindListnumRestautant(userid);
    }

    @Override
    public Integer FindListnumOrder(Integer state) {
        return findUserInfoDao.FindListnumOrder(state);
    }
}
