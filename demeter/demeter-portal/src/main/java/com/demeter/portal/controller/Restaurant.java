package com.demeter.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demeter.common.pojo.ManagerInfoDO;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.service.IRestaurantInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
*@Description   根据用户id显示餐厅信息
*@Author 胡传威
*@DateTime 2019/7/26 2019/7/26
*/

@RequestMapping("/card")
@Controller
public class Restaurant {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private IRestaurantInfoService iRestaurantInfoService;

    /**
     *  路径：http://localhost:8080/card/listCard?token=token&pageNum=1&pageSize=4
     *      * @param pageSize  页显示条数
     *      * @param pageNum   第几页
     *      * @param token     token
     * @return "address": "这是具体地点",
     *     "city": "娄底",
     *     "about": "我们是一家很牛逼的餐馆",
     *     "icon": "img.png",
     *     "del": 1,
     *     "updateTime": 1564030800000,
     *     "userId": 1,
     *     "province": "湖南",
     *     "createTime": 1562648400000,
     *     "registerId": 3123121,
     *     "name": "悦来客栈",
     *     "failReason": "你的营业执照是非法的",
     *     "stopTime": 1566968400000,
     *     "id": 1,
     *     "card": "383e0cab-838c-408e-8928-513bf753b6df"
     *
     */
    @RequestMapping("/listCard")
    @ResponseBody
    public JSON listCard(Integer pageSize, Integer pageNum, String token){
        Integer userId = Integer.valueOf(jedisClient.hget(token,"id"));
        Page<RestaurantInfoDO> page = PageHelper.startPage(pageNum, pageSize);
        PageInfo<RestaurantInfoDO> infoDOPageInfo = new PageInfo<>(
                iRestaurantInfoService.getRestaurantInfo(userId));
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(infoDOPageInfo));

        return jsonObject;
    }

    /**
     *    http://localhost:8080/card/getCard?id=1
     * @param id     餐厅id
     * @return  "address": "这是具体地点",
     *     "city": "娄底",
     *     "about": "简介hah",
     *     "icon": "img.png",
     *     "del": 1,
     *     "updateTime": 1564030800000,
     *     "userId": 1,
     *     "province": "湖南",
     *     "createTime": 1563858000000,
     *     "registerId": 3123121,
     *     "name": "媳妇来客栈",
     *     "failReason": "审核失败的理由",
     *     "stopTime": 1567054800000,
     *     "id": 2,
     *     "card": "ShouQuanMa"
     */
    @RequestMapping("/getCard")
    @ResponseBody
    public JSON getCard(Integer id){
        List<RestaurantInfoDO> restaurantInfoById = iRestaurantInfoService.getRestaurantInfoById(id);
//        Integer byid = Integer.valueOf(jedisClient.hget(token,"id"));
//        List<RestaurantInfoDO> restaurantInfoById = iRestaurantInfoService.getRestaurantInfoById(id, byid);

        JSONObject jsonObject = (JSONObject)JSON.toJSON(restaurantInfoById.get(0));
        return jsonObject;
    }



}
