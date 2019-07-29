package com.demeter.restaurant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demeter.common.pojo.OrderFoodDO;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.restaurant.service.IFoodService;
import com.demeter.restaurant.service.IRestaurantService;
import com.demeter.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
*@Description 餐厅端
*@Author 刘海亮
*@DateTime 2019/7/25 16:49
*/
@RequestMapping("/cook")
@Controller
public class CookController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private IRestaurantService iRestaurantService;
    @Autowired
    private IFoodService iFoodService;

    /**
    *@Description 登录页面
    *@Author 刘海亮
    *@DateTime 2019/7/25 16:49
    */
    @RequestMapping("/login")
    public String chef() {
        return "login";
    }

    /**
    *@Description 登录检查
    *@Author 刘海亮
    *@DateTime 2019/7/25 16:50
    */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public Integer chef(String phone ,String card) {
        // 如果输入为空，重定向到登录
        if (phone==null||card==null) {
            return 400;
        }
        if (iUserService.checkLogin(phone,card)){
            jedisClient.hset("restaurant",card,"open");
            RestaurantInfoDO restaurantInfo = iRestaurantService.getResRestaurantInfo(card);
            // 将餐馆信息存入redis
            jedisClient.hset(card,"id",restaurantInfo.getId().toString());
            jedisClient.hset(card,"name",restaurantInfo.getName());
            jedisClient.hset(card,"icon",restaurantInfo.getIcon());
        }
        return 200;
    }
    
    /**
    *@Description 身份选择大厅
    *@Author 刘海亮
    *@DateTime 2019/7/25 17:29
    */
    @RequestMapping("/lobby")
    public String lobby(Model model,@RequestParam("card") String card)  {
        model.addAttribute("name",jedisClient.hget(card,"name"));
        model.addAttribute("icon",jedisClient.hget(card,"icon"));
        model.addAttribute("card",card);
        return "lobby";
    }


    /**
     *@Description 二维码生成
     *@Author 刘海亮
     *@DateTime 2019/7/25 17:29
     */
    @RequestMapping("/qrCode")
    public String qrCode(Model model,@RequestParam("card") String card)  {
        model.addAttribute("name",jedisClient.hget(card,"name"));
        model.addAttribute("icon",jedisClient.hget(card,"icon"));
        model.addAttribute("card",card);
        return "qrCode";
    }

    /**
     *@Description 厨师端
     *@Author 刘海亮
     *@DateTime 2019/7/25 17:29
     */
    @RequestMapping("/chef")
    public String chef(Model model,@RequestParam("card") String card)  {
        model.addAttribute("name",jedisClient.hget(card,"name"));
        model.addAttribute("icon",jedisClient.hget(card,"icon"));
        model.addAttribute("card",card);
        model.addAttribute("waitNum",jedisClient.hget(card,"waitNum"));
        return "chef";
    }

    /**
    *@Description 不断的处理厨师端发送过来的请求
    *@Author 刘海亮
    *@DateTime 2019/7/26 0:38
    */
    @RequestMapping("/dispose")
    @ResponseBody
    public JSON dispose(String waitNum, String card){
        List<OrderFoodDO> listOrder=new ArrayList<>();
        // 比对等待数，看是否有新的订单，如果没有直接返回一个空json
        String oldNum=jedisClient.hget(card,"waitNum");
        if (oldNum==null||oldNum.equals(waitNum)){
            String json=JSON.toJSONString(listOrder);
            return ((JSONArray)JSON.parseArray(json));
        }

        // 如果不一样，表示要重新获取所有订单
//        for (Integer num=1;num<Integer.valueOf(oldNum);num++) {
//            String trade=jedisClient.hget(card,num.toString());
//            if (trade!=null){
//                listOrder.add(trade);
//            }
//        }
        listOrder =iFoodService.getListOrder(Long.valueOf(jedisClient.hget(card,"id")));
        jedisClient.hset(card,"waitNum",String.valueOf(listOrder.size()));
        String json=JSON.toJSONString(listOrder);
        return ((JSONArray)JSON.parseArray(json));
    }
    
    /**
    *@Description 完成订单操作
    *@Author 刘海亮
    *@DateTime 2019/7/26 11:00
    */
    @RequestMapping("/success")
    @ResponseBody
    public String success(Long id,String card) {
        jedisClient.hset(card,"waitNum",String.valueOf(Integer.valueOf(jedisClient.hget(card,"waitNum"))-1));
        iFoodService.setOrderStatus(id,3);
        return "{\"status\":200}";
    }
    /**
    *@Description 打烊
    *@Author 刘海亮
    *@DateTime 2019/7/26 11:32
    */
    @RequestMapping("/close")
    public String close(String card) {
//        jedisClient.hDelAll(card);
        jedisClient.hset("restaurant",card,"close");
        return "login";
    }
    /**
     *@Description 需要服务的
     *@Author 刘海亮
     *@DateTime 2019/7/26 11:32
     */
    @RequestMapping("/service")
    @ResponseBody
    public JSON service(String card) {
        System.out.println(jedisClient.hget(card,"service"));
        String str=jedisClient.hget(card,"service");
        System.out.println("飞洒发"+str);
        System.out.println("{\"seat\":\""+ str+ "\"}");

        return JSON.parseObject("{\"seat\":\""+ str+ "\"}");
    }
}
