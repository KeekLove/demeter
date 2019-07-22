package com.demeter.portal.controller;

import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.dao.ManMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/a")
public class test {
    public static  Logger LOGGER = LogManager.getLogger(test.class.getName());
    @Autowired
    private ManMapper managerDao;

    @Autowired
    private JedisClient jedisClient;
    @Test
    public void name1() {
    }

    @RequestMapping("/")
    public void name() {
        System.out.println(jedisClient.hset("add","name","21323"));
        System.out.println(managerDao.selectAll().get(0).toString());
    }
}
