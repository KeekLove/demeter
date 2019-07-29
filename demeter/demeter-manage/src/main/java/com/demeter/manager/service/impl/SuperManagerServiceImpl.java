package com.demeter.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.demeter.common.pojo.ManagerInfoDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.manager.dao.SuperManagerMapper;
import com.demeter.manager.service.SuperManagerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SuperManagerServiceImpl implements SuperManagerService {
    /**
    *@Description 传入token,请求的页面pageNum,然后根据权限token的等级决定是否返回分页JSON
    *@Author 曾锦铭
    *@DateTime 7/22/2019 7/22/2019
    */
    @Autowired
    SuperManagerMapper superManagerMapper;
    @Autowired
    JedisClient jedisClient;

    @Override
    public JSON getManagersPage(String token,Integer pageNum) {
        /**
        *7/23/2019 2:36 PM  TODO: 验证token
        */
        String privilege = jedisClient.hget(token,"level");

        Page<ManagerInfoDO> page = PageHelper.startPage(pageNum, 2);
        List<ManagerInfoDO> managerInfoDOS = superManagerMapper.selectAll();
        PageInfo<ManagerInfoDO> managerInfoDOPageInfo = new PageInfo<>(managerInfoDOS);
        String s = JSON.toJSONString(managerInfoDOPageInfo);
        return JSON.parseObject(s);
    }

    @Override
    public JSON deleteManager(String token,Long id) {
        /**
        *TODO 待验证
        *@DateTime 7/22/2019 9:13 PM
        */
        String privilege = jedisClient.hget(token,"level");

        int i = superManagerMapper.deleteById(id);
        String str = "{'affected':'"+String.valueOf(i)+"'}";
        return JSON.parseObject("{'affected':'"+String.valueOf(i)+"'}");
    }

    @Override
    public JSON insertManager(String token, String username, String password, Integer privilege) {
        /**
        *TODO 7/23/2019 1:08 PM token验证
        */
        Date date = new Date(System.currentTimeMillis());
        ManagerInfoDO manager = new ManagerInfoDO();
        manager.setUsername(username);
        manager.setPassword(password);
        manager.setPrivilege(privilege);
        manager.setCreateTime(date);
        manager.setUpdateTime(date);
        int insert = superManagerMapper.insert(manager);
        String str = "{'affected':'"+String.valueOf(insert)+"'}";
        return JSON.parseObject(str);
    }

    @Override
    public JSON updateManager(String token, Long id, String username, String password, Integer privilege) {
        /**
        *7/23/2019 2:42 PM  TODO: token验证
        */
        Date date = new Date(System.currentTimeMillis());
        ManagerInfoDO managerInfoDO = new ManagerInfoDO();
        managerInfoDO.setId(id);
        managerInfoDO.setUsername(username);
        managerInfoDO.setPassword(password);
        managerInfoDO.setPrivilege(privilege);
        int update = superManagerMapper.update(managerInfoDO);
        String str = "{'affected':'"+String.valueOf(update)+"'}";
        return JSON.parseObject(str);
    }

    @Override
    public JSON getManager(String token, Long id) {
        /**
        *7/23/2019 3:30 PM  TODO: token验证
        */
        ManagerInfoDO managerInfoDO = superManagerMapper.selectOne(id);
        String jsonString = JSON.toJSONString(managerInfoDO);
        return JSON.parseObject(jsonString);
    }


}
