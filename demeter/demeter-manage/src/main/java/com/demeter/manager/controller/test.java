package com.demeter.manager.controller;

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
    private com.demeter.manager.dao.ManMapper managerDao;

    @Test
    public void name1() {
        LOGGER.error("哈哈哈哈哈哈");
    }

    @RequestMapping("/")
    public void name() {
        System.out.println(managerDao.selectAll().get(0).toString());
    }
}
