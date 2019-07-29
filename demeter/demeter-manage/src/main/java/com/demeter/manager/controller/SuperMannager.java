package com.demeter.manager.controller;


import com.alibaba.fastjson.JSON;
import com.demeter.common.pojo.ManagerInfoDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.manager.dao.SuperManagerMapper;
import com.demeter.manager.service.impl.SuperManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
//import com.demeter.manager.dao.ManMapper;


@Controller
@RequestMapping("/Managers")
public class SuperMannager {

    @Autowired
    SuperManagerMapper superManagerManager;
    @Autowired
    JedisClient jedisClient;
    @Autowired
    SuperManagerServiceImpl service;

    /**
    *@Description 请求所有管理员的分页
    *@Author 曾锦铭
    *@DateTime 7/22/2019 7/22/2019
    */

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public JSON getUserPage(String token,Integer pageNum){
        JSON managersPage = service.getManagersPage(token, pageNum);
        return managersPage;
    }

    /**
    *@Description 根据ID删除一个用户
    *@Author 曾锦铭
    *@DateTime 7/22/2019 9:19 PM
    */
    @RequestMapping(value = "/manager",method = RequestMethod.DELETE)
    @ResponseBody
    public JSON deleteManager(String token, Long id){
        System.out.println(id+"---------)))))(");
        return service.deleteManager(token, id);
    }

    @RequestMapping(value = "/manager",method = RequestMethod.POST)
    @ResponseBody
    public JSON createManager(String token, String username, String password, Integer privilege){
        return service.insertManager(token, username, password, privilege);
    }
    
    /**
    *@Description 更新一个用户的信息
    *@Author 曾锦铭
    *@DateTime 7/23/2019 2:26 PM
    */
    @RequestMapping(value = "/manager",method = RequestMethod.PUT)
    @ResponseBody
    public JSON updateManager(String token, Long id, String username, String password, Integer privilege){
        return service.updateManager(token,id,username,password,privilege);
    }

    /**
    *@Description 获取单个用户的信息
    *@Author 曾锦铭
    *@DateTime 7/23/2019 3:19 PM
    */
    @RequestMapping(value = "manager",method = RequestMethod.GET)
    @ResponseBody
    public JSON getOneManager(String token, Long id){
        return service.getManager(token,id);
    }
}