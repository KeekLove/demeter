package com.demeter.manager.controller;

import com.alibaba.fastjson.JSON;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.manager.dao.OrdinaryManagerMapper;
import com.demeter.manager.pojo.RestaurantList;
import com.demeter.manager.pojo.User;
import com.demeter.manager.service.OrdinaryManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/operationArea")
public class OrdinaryManager {
    @Autowired
    private OrdinaryManagerService ordinaryManagerService;
    @Autowired
    private OrdinaryManagerMapper mapper;
    @Autowired
    private JedisClient jedisClient;

    /**
    *@Description 返回餐厅列表
    *@Author 曾锦铭
    *@DateTime 7/24/2019 3:01 PM
    */
    @RequestMapping(value = "/restaurantsPage", method = RequestMethod.GET)
    @ResponseBody
    public JSON test(String token, Integer pageNum){
        return ordinaryManagerService.getAllRestaurantsPage(token, pageNum);
    }

    /**
    *@Description 返回订单列表
    *@Author 曾锦铭
    *@DateTime 7/25/2019 2:34 PM
    */
    @RequestMapping(value = "/packagesPage",method = RequestMethod.GET)
    @ResponseBody
    public JSON packages(String token,Integer pageNum){
        System.out.println(token);
        return ordinaryManagerService.getPackagesPage(token, pageNum);
    }

    /**
    *@Description 更新授权码
    *@Author 曾锦铭
    *@DateTime 7/24/2019 3:56 PM
    */
    @RequestMapping(value = "/card",method = RequestMethod.PUT)
    @ResponseBody
    public JSON updateRes(String token, Long id, String card){
        return ordinaryManagerService.updateCard(token, id, card);
    }

    /**
    *@Description 拒绝并且说明理由
    *@Author 曾锦铭
    *@DateTime 7/24/2019 4:25 PM
    */
    @RequestMapping(value = "/failReason",method = RequestMethod.PUT)
    @ResponseBody
    public JSON refuesRes(String token, Long id, String failReason){
        return ordinaryManagerService.refuesRes(token, id, failReason);
    }

    /**
    *@Description 登录并设置token
    *@Author 曾锦铭
    *@DateTime 7/24/2019 9:25 PM
    */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JSON login(String username,String password){
        User user = mapper.selectUser(username);
        if (user == null){
            return JSON.parseObject("{'error':'登录信息错误，请检查'}");
        }
        if (!user.getPassword().equals(password)){
            return JSON.parseObject("{'error':'登录信息错误，请检查'}");
        }else {
            user.setToken(UUID.randomUUID().toString());
            jedisClient.hset(user.getUsername(),"level",user.getPrivilege()+"");
            jedisClient.hset(user.getUsername(),"token",user.getToken());
            String jsonString = JSON.toJSONString(user);
            return JSON.parseObject(jsonString);
        }
    }

    /**
    *@Description 生成授权码
    *@Author 曾锦铭
    *@DateTime 7/25/2019 4:58 PM
    */
    @RequestMapping(value = "/codeCard",method = RequestMethod.PUT)
    @ResponseBody
    public JSON genCode(String token,Long resId){
        System.out.println(resId);
       return ordinaryManagerService.generateCard(token,resId);
    }
    /**
    *@Description 冻结授权码
    *@Author 曾锦铭
    *@DateTime 7/25/2019 7:52 PM
    */
    @RequestMapping(value = "/nonCode",method = RequestMethod.PUT)
    @ResponseBody
    public JSON updateDel(String token,Long resId){
        System.out.println(resId);
        return ordinaryManagerService.freezeRes(token,resId);
    }
    /**
    *@Description 更新过期时间
    *@Author 曾锦铭
    *@DateTime 7/25/2019 8:39 PM
    */
    @RequestMapping(value = "/newDate",method = RequestMethod.PUT)
    @ResponseBody
    public JSON updateExp(String token, Long resId, Date date){
        return ordinaryManagerService.updateExp(token,resId,date);
    }
}
