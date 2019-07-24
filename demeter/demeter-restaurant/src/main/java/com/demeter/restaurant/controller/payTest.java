package com.demeter.restaurant.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.demeter.common.pay.Pay;
import com.demeter.common.pojo.dto.PayDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/pay")
public class payTest {
    @RequestMapping("/test2")
    public void test2(HttpServletRequest httpRequest,
                      HttpServletResponse httpResponse) throws ServletException, IOException {
        Pay pay = new Pay();
        PayDto payDto = new PayDto();
        // 获取前端给的金额，餐馆id
        // 找到当前order_user表中最大的id主键，并且加一
        payDto.setOut_trade_no("999999994");
        payDto.setBody("支付测试");
        payDto.setTotal_amount("99.23");
        payDto.setSubject("支付测试");
        pay.pay(httpRequest,httpResponse,payDto,"http://localhost:8522/pay/test3");
        // 把当前信息存入数据库，insert的数据有"999999994"，餐馆id，用户id套餐id

    }

    @RequestMapping("/test3")
    public void test3(String trade_no,String out_trade_no) throws ServletException, IOException {
     // 更新

    }

}
