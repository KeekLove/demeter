package com.demeter.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.service.IUserInfoService;
import org.apache.ibatis.annotations.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.ldap.PagedResultsControl;
import java.util.HashMap;
/**
*@Description 
*@Author 胡传威
*@DateTime 2019/7/23 2019/7/23
*/
@Controller
@RequestMapping("/user")
public class UserInfo {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private IUserInfoService iUserInfoService;

    /**
     *
     * @param token
     * @return
     */
    @RequestMapping("/info")
    @ResponseBody
    private JSON SelectUserInfo(String token){

        Integer userids = Integer.valueOf(jedisClient.hget(token, "id"));
//        System.out.println(userids);

        String numRestaurants = jedisClient.hget(token, "numRestaurant");
        Long numRestaurant=0L;
        if (null==numRestaurants){
            Integer cggs = iUserInfoService.FindListnumRestautant(userids);
            System.out.println("--------------");
            System.out.println("餐馆个数："+cggs);
            jedisClient.hset(token, "numRestaurant", cggs.toString());

        }
        numRestaurant = Long.valueOf(jedisClient.hget(token, "numRestaurant")) ;

        String numOrders = jedisClient.hget(token, "numOrder");
        Long numOrder=0L;
        if (null==numOrders){
            Integer ddsl = iUserInfoService.FindListnumOrder(3);
            System.out.println("-----------");
            System.out.println("完成订单数："+ddsl);
            jedisClient.hset(token, "numOrder", ddsl.toString());

        }
        numOrder = Long.valueOf(jedisClient.hget(token, "numOrder"));
        HashMap<String,Object> hashMap= new HashMap<>();
        hashMap.put("userid",userids);
        hashMap.put("username",jedisClient.hget(token,"username"));
        hashMap.put("numRestaurant",numRestaurant);
        hashMap.put("numOrder",numOrder);
        String result = JSON.toJSONString(hashMap);
        JSONObject jsonResult = JSON.parseObject(result);

        return jsonResult;
    }
}
