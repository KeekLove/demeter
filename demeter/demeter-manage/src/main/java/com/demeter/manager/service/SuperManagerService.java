package com.demeter.manager.service;

import com.alibaba.fastjson.JSON;
import com.demeter.common.pojo.ManagerInfoDO;
import com.github.pagehelper.Page;

import java.util.Map;

public interface SuperManagerService {
    JSON getManagersPage(String token, Integer pageNum);
    JSON deleteManager(String token, Long id);
    JSON insertManager(String token, String username, String password, Integer privilege);
    JSON updateManager(String token, Long id, String username, String password, Integer privilege);
    JSON getManager(String token, Long id);
}
