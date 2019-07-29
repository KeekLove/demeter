package com.demeter.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demeter.common.pojo.OrderFoodDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.pojo.OrderFoodDTO;
import com.demeter.portal.service.IOrderFoodService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/**
*@Description 查询历史账单
*@Author 陈龙鑫
*@DateTime 2019/7/23 2019/7/23
*/
@Controller
@RequestMapping(value = "/order")
public class OrderFoodDoController {
    @Autowired
    private IOrderFoodService orderFoodService;
    @Autowired
    private JedisClient jedisClient;

    /**
     * 获取历史菜单
     * 路径：/order/listSuccess
     * @param pageSize 每页展示数据条数
     * @param pageNum 第几页
     * @param token token
     * @return 历史菜单
     * 路径：http://25a8m95299.wicp.vip:43058/order/listSuccess?token=token(前面是外网穿透地址)
     * 返回数据如下：
     * "seatNum": 12,
     * "createTime": 1564030800000,
     * "name": "媳妇来客栈",
     * "foodContext": "[{\"foodName\":\"青椒炒肉\",\"menu-img\":\"/public/images/demo.jpg\",\"num\":1,\"type\":\"特辣\",\"prize\":188},{\"foodName\":\"卡卡炒肉\",\"menu-img\":\"/public/images/demo.jpg\",\"num\":1,\"type\":\"特辣\",\"prize\":567}]",
     * "id": 10000000004,
     * "prize": 755.0
     * "order_id"(由于没有数据，数据结果没有这一条属性)
     */
    @RequestMapping(value = "/listSuccess",method = RequestMethod.GET)
    @ResponseBody
    public JSON findOrderFoodDo(String pageSize, String pageNum, String token)
    {
        Integer id = Integer.valueOf(jedisClient.hget(token, "id"));
        Page<OrderFoodDO> page = PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<OrderFoodDTO> list = orderFoodService.findOrderFoodDO(pageSize, pageNum,id.toString());
        PageInfo<OrderFoodDTO> orderFoodDTOPageInfo = new PageInfo<>(list);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(orderFoodDTOPageInfo));
        return jsonObject;
    }
}
