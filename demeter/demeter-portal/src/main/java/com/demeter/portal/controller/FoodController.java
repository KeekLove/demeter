package com.demeter.portal.controller;

import com.alibaba.fastjson.JSON;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.service.IFoodService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *@Description 食品的controller
 *@Author 辜锦龙
 *@DateTime 2019/7/24
 */

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private IFoodService iFoodService;
    @Autowired
    private JedisClient jedisClient;


    /**
     * 查询菜单、餐馆、用户等信息
     * 访问路径：http://localhost:8080//food/listFood?pageSize=1&pageNum=5&token=token&card=383e0cab-838c-408e-8928-513bf753b6df&menuId=1
     * 返回参数：
     *      "createtime": 1562624467000,
     * 		"foodname": "青椒炒肉",
     * 		"menuname": "实惠套餐",
     * 		"foodid": 1,
     * 		"icon": "img.png",
     * 		"menuid": 1,
     * 		"resname": "悦来客栈",
     * 		"prize": 188.0
     * @param pageSize 当前页
     * @param pageNum   页面内容
     * @param token token
     * @param card 授权码
     * @param menuId 套餐id
     * @return
     */
    @RequestMapping(value = "/listFood",method = RequestMethod.GET)
    @ResponseBody
    public JSON findMenuByCard(int pageSize, int pageNum, String token, String card, long menuId){
        //去service进行处理
        JSON json = iFoodService.findFood(pageSize, pageNum, token, card, menuId);
        return json;
    }

    /**
     * 上传菜品信息
     * @param icon 图片
     * @param token token
     * @param prize 价格
     * @param name 名字
     * @param about 描述
     * @param menuId
     * @param restaurantId
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public JSON insertMenu(
             @Param("icon") MultipartFile icon
            ,@Param("token") String token
            ,@Param("prize") Float prize
            ,@Param("name") String name
            ,@Param("about") String about
            ,@Param("menuId") Long menuId
            ,@Param("restaurantId") Long restaurantId) throws IOException {
        //去service处理
        JSON json = iFoodService.insertMenu(icon,token,prize,name,about,menuId,restaurantId);
        return json;
    }


    @RequestMapping(value = "/gotoIndex",method = RequestMethod.GET)
    public ModelAndView test(@Param("token") String token){
            ModelAndView modelAndView =new ModelAndView();
            modelAndView.setViewName("/index");
            modelAndView.addObject("token",token);
        return modelAndView;
    }

}
