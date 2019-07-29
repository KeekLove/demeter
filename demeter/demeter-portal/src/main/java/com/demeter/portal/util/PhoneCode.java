package com.demeter.portal.util;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
/**
*@Description 调用短信接口的类
*@Author 辜锦龙
*@DateTime 2019/7/25 2019/7/25
*/

public class PhoneCode {
    //接收手机号码传递过来的随机验证码
   public static boolean sendCode(String phoneNumber,String code) throws Exception {
       String encode = URLEncoder.encode("#code#=" + code, "utf-8");
        //封装URL
       URL url=new URL("http://v.juhe.cn/sms/send?mobile="
               +phoneNumber+"&tpl_id=175279&tpl_value="+encode
               +"&key=81f66aa8c78000564b78de250f9f7b30");
        //打开链接对象
       URLConnection urlConnection = url.openConnection();
       //发送请求
       urlConnection.connect();

       //获取响应数据
       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
       StringBuffer stringBuffer = new StringBuffer();
       String linDate=null;
       while ((linDate=bufferedReader.readLine())!=null){
           stringBuffer.append(linDate);
       }
       bufferedReader.close();

       if (stringBuffer.toString().indexOf("error_code")>=0){
           return true;
       }
       return false;
   }


}
