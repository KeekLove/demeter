package com.demeter.portal.service.impl;

import com.demeter.common.pojo.PackageTypeDO;
import com.demeter.portal.dao.IPackDao;
import com.demeter.portal.service.IPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackServiceImpl  implements IPackService {
    @Autowired
    private IPackDao iPackDao;
    @Override
    public List<PackageTypeDO> getListPack() {
        return iPackDao.findListPack();
    }
}
