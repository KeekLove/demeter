package com.demeter.portal.dao;

import com.demeter.common.pojo.PackageTypeDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPackDao {
    List<PackageTypeDO> findListPack();
}
