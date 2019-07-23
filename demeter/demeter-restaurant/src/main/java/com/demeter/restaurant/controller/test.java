package com.demeter.restaurant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demeter.common.pojo.ManagerInfoDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.restaurant.dao.ManMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/a")
public class test {
    @Autowired
    private ManMapper manMapper;
    @Autowired
    private JedisClient jedisClient;

    @Test
    public void name1() {
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
}
