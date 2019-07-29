package com.demeter.common.pay;

import com.alibaba.druid.pool.DruidDataSource;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.demeter.common.pojo.dto.PayDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;


/**
*@Description 支付
*@Author 刘海亮
*@DateTime 2019/7/23 15:47
*/
public class Pay {
    private static String OPEN_API_DOMAIN;
    private static String APP_ID;
    private static String APP_PRIVATE_KEY;
    private static String FORMAT;
    private static String CHARSET;
    private static String ALIPAY_PUBLIC_KEY;
    private static String SIGN_TYPE;
    static {
        ResourceBundle resource = ResourceBundle.getBundle("resources/zfbinfo");
        DruidDataSource dataSource = new DruidDataSource();
        OPEN_API_DOMAIN=resource.getString("open_api_domain");
        APP_ID=resource.getString("appid");
        APP_PRIVATE_KEY=resource.getString("private_key");
        FORMAT="json";
        CHARSET="utf-8";
        ALIPAY_PUBLIC_KEY=resource.getString("alipay_public_key");
        SIGN_TYPE=resource.getString("sign_type");
    }


    public void pay(HttpServletRequest httpRequest,
                     HttpServletResponse httpResponse,PayDto collections,String callBack) throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(OPEN_API_DOMAIN, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(callBack);
        alipayRequest.setNotifyUrl(callBack);//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent(collections.toString());
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println("上的发生发生发射点发"+form);
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    public void pay(HttpServletRequest httpRequest,
                    HttpServletResponse httpResponse,PayDto collections,String callBack,String result) throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(OPEN_API_DOMAIN, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        if (result!=null||result.length()>0){
            alipayRequest.setReturnUrl(result);
        }
        alipayRequest.setNotifyUrl(callBack);//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent(collections.toString());
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }


    public void payWap(HttpServletRequest httpRequest,
                    HttpServletResponse httpResponse,PayDto collections,String callBack,String result) throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(OPEN_API_DOMAIN, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradeWapPayRequest alipayRequest=new AlipayTradeWapPayRequest();//创建API对应的request
        if (result!=null||result.length()>0){
            alipayRequest.setReturnUrl(result);
        }

        //在公共参数中设置回跳和通知地址
        alipayRequest.setNotifyUrl(callBack);
        alipayRequest.setBizContent(collections.toString());
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

}
