package com.demeter.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demeter.common.pay.Pay;
import com.demeter.common.pojo.OrderUserDO;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.common.pojo.dto.PayDto;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.pojo.OrderPackageDTO;
import com.demeter.portal.pojo.StatusVO;
import com.demeter.portal.service.IOrderUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
*@Description 订单支付
*@Author 陈龙鑫
*@DateTime 2019/7/24 2019/7/24
*/
@Controller
@RequestMapping(value = "/order/")
public class PayController {
    @Autowired
    private IOrderUserService orderUserService;
    @Autowired
    private JedisClient jedisClient;

    /**
     * 支付宝订单支付
     * 路径：/order/pay
     * @param packageId 套餐id
     * @param restaurantId 餐馆id
     * @param token token
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/pay",method = RequestMethod.GET )
    public void payment(Long packageId,
                        Long restaurantId,
                        String token,
                        HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        // 找到当前order_user表中最大的id主键，并且加一
        OrderUserDO orderUser = orderUserService.findByLastOne();
        Long id= orderUser.getId()+1;
        // 获取前端给的金额，餐馆id
        Integer userId = Integer.valueOf(jedisClient.hget(token, "id"));
        OrderUserDO orderUserDO = new OrderUserDO();
        orderUserDO.setPackageId(packageId);
        orderUserDO.setRestaurantId(restaurantId);
        orderUserDO.setId(id);
        orderUserDO.setUserId((long) userId.intValue());
        // 把当前信息存入数据库，insert的数据有"999999994"，餐馆id，用户id套餐id
        Integer result = orderUserService.insertOrderUser(orderUserDO);
        if (result > 0)
        {
            // 创建支付接口
            Pay pay = new Pay();
            PayDto payDto = new PayDto();
            payDto.setOut_trade_no("" +id);
            payDto.setBody("支付测试");
            payDto.setTotal_amount("99.23");
            payDto.setSubject("支付测试");
            pay.pay(request,response,payDto,"http://25a8m95299.wicp.vip:43058/order/callback");
        }

    }

    /**
     * 支付宝数据回执地址（自动获取）
     * 路径：http://25a8m95299.wicp.vip:43058/order/callback
     * @param trade_no 支付宝订单号
     * @param out_trade_no 餐馆订单号
     * @return JSON格式的提示信息
     */

    @RequestMapping(value = "/callback",method = RequestMethod.GET)
    @ResponseBody
    public JSON update(String trade_no,String out_trade_no)
    {
        OrderUserDO orderUserDO = new OrderUserDO();
        //long l = Long.parseLong(trade_no);
        orderUserDO.setOrderId(trade_no);
        //long l1 = Long.parseLong(out_trade_no);
        orderUserDO.setId(Long.valueOf(out_trade_no));
        // 修改用户订单信息
        Integer result = orderUserService.updateOrderUser(orderUserDO);
        // 修改成功，查询套餐类型
        if (result > 0)
        {
            OrderPackageDTO orderPackageDTO = new OrderPackageDTO();
            orderPackageDTO.setId(Long.parseLong(out_trade_no));
            OrderPackageDTO orderPackage = orderUserService.findDate(orderPackageDTO);
            System.out.println(123);
            // 获取套餐天数
            int avticeNum = orderPackage.getAvticeNum();
            // 获取餐馆ID
            Long restaurantId = orderPackage.getRestaurantId();
            // 通过 restaurantId 找到 stopTime
            RestaurantInfoDO stop = orderUserService.findStopTime(orderPackage);
            Date stopTime = stop.getStopTime();
            // 设置新的 stopTime
            RestaurantInfoDO restaurantInfoDO = new RestaurantInfoDO();
            long time = stopTime.getTime();
            long day = avticeNum*(24*60*60*1000);
            Date date = new Date(time + day);
            restaurantInfoDO.setStopTime(date);
            restaurantInfoDO.setId(restaurantId);
            // 修改信息
            Integer integer = orderUserService.updateStopTime(restaurantInfoDO);
            if (integer>0)
            {
                StatusVO statusVO = new StatusVO();
                statusVO.setStatus(200);
                statusVO.setMsg("充值成功");
                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(statusVO));
                return jsonObject;
            }else
            {
                 StatusVO statusVO = new StatusVO();
                statusVO.setStatus(400);
                statusVO.setMsg("时间修改失败");
                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(statusVO));
                return jsonObject;

            }

        }else{
            StatusVO statusVO = new StatusVO();
            statusVO.setStatus(400);
            statusVO.setMsg("充值失败");
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(statusVO));
            return jsonObject;
        }
    }
}
