package com.demeter.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demeter.common.pojo.ManagerInfoDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.dao.ManMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/a")
public class test {
    @Autowired
    private ManMapper managerDao;

    @Autowired
    private JedisClient jedisClient;

    @Test
    public void name1() {
    }

    @RequestMapping("/")
    public void name(String token) {
        token = "token";
//        System.out.println(jedisClient.hget("token","name"));
        System.out.println(jedisClient.hset("test1", "name", "即使对方是开发和数据库3"));
        //   System.out.printf((jedisClient.hget("test1","name")));
        jedisClient.expire("test1", 20);
        System.out.println(managerDao.selectAll().get(0).toString());
    }

    @RequestMapping("/test")
    @ResponseBody
    public JSONObject GETSHUJU() {
        String str = "{\n" +
                "\t\"ID\": 1001,\n" +
                "\t\"name\": \"张三\",\n" +
                "\t\"age\": 24\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject;
    }

    @RequestMapping("/test2")
    @ResponseBody
    public JSON GETSHUJU2(String name, String token) {
        HashMap<String, String> hashMap = new HashMap();
        String love = jedisClient.hget(token, "username");
        System.out.println(love);
        hashMap.put("myname", name);
        hashMap.put("love", love);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(hashMap));
        return jsonObject;
    }

    @RequestMapping("/test3")
    @ResponseBody
    public JSON GETSHUJU3() {
        Page<ManagerInfoDO> page = PageHelper.startPage(1, 2);
        List<ManagerInfoDO> list = managerDao.selectAll();
        PageInfo<ManagerInfoDO> infoDOPageInfo = new PageInfo<>(list);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(infoDOPageInfo));
        return jsonObject;
    }
}
