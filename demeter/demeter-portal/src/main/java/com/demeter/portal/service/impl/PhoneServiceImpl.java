package com.demeter.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demeter.common.pojo.UserInfoDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.dao.PhoneMapperDao;
import com.demeter.portal.service.IPhoneService;
import com.demeter.portal.util.PhoneCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class PhoneServiceImpl implements IPhoneService {


    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private PhoneMapperDao phoneMapperDao;

    /**
     * 注册相关
     * @param phoneNumber
     * @param code
     * @return
     */
    @Override
    public JSON checkPhoneVerification(String phoneNumber, String code,String password) {
        //判断手机号码和验证码是否为空
        JSONObject jsonObject=null;
        if (null==phoneNumber||"".trim().equals(phoneNumber)||null==code||"".trim().equals(code)){
            String str="{'msg':'手机号码或验证码为空'}";
             jsonObject = JSONObject.parseObject(str);
            return jsonObject;
        }
        //判断手机号的格式是否出错
        if (!Pattern.matches("^1(3|4|5|7|8)\\d{9}$",phoneNumber)){
            String str="{'msg':'手机号码格式有误'}";
             jsonObject = JSONObject.parseObject(str);
            return jsonObject;
        }
        //从Redis里拿数据
        String newCode = jedisClient.hget("VerificationCode", "code");
        String newNumber = jedisClient.hget("phoneNumber", "number");
        String newTime = jedisClient.hget("SurvivalTime", "time");
        String newPassword = jedisClient.hget("password", "password");
        //判断验证码是否为空
        if (null==newCode||"".trim().equals(newCode)){
            String str="{'msg':'验证码为空'}";
            jsonObject = JSONObject.parseObject(str);
            return jsonObject;
        }
        //验证码有效时间10分钟
        if ((System.currentTimeMillis()-Long.parseLong(newTime))/1000/60>=10){
            String str="{'msg':'验证码已过期'}";
            jsonObject = JSONObject.parseObject(str);
            return jsonObject;
        }
        //判断手机号是否一致
        if (!phoneNumber.trim().equalsIgnoreCase(newNumber)){
            String str="{'msg':'手机号不一致'}";
            jsonObject = JSONObject.parseObject(str);
            return jsonObject;
        }
        //判断密码是否一致
        if (!password.trim().equalsIgnoreCase(newPassword)){
            String str="{'msg':'密码错误'}";
            jsonObject = JSONObject.parseObject(str);
            return jsonObject;
        }
        //判断是否注册成功
        if (newCode.trim().equalsIgnoreCase(code)&&newPassword.trim().equalsIgnoreCase(password)){
            String str="{'msg':'注册成功'}";
            jsonObject = JSONObject.parseObject(str);
            //注册成功则将用户信息存储到数据库
            phoneMapperDao.insertPhone(phoneNumber,password);
            //清除redis中所存储的参数
            jedisClient.hdel("VerificationCode", "code");
            jedisClient.hdel("phoneNumber", "number");
            jedisClient.hdel("SurvivalTime", "time");
            jedisClient.hdel("password", "password");
            return jsonObject;
        }else {
            String str="{'msg':'注册失败'}";
            jsonObject = JSONObject.parseObject(str);
            return jsonObject;
        }
    }

    /**
     * 发送验证码的请求
     * @param phoneNumber
     * @return
     */
    @Override
    public JSON sellPhoneVerification(String phoneNumber,String password) {
        JSONObject jsonObject=null;
        if (null==phoneNumber||"".trim().equals(phoneNumber)){
            String str="{'msg':'手机号码不能为空'}";
            jsonObject = JSONObject.parseObject(str);
            return jsonObject;
        }
        if (null==password||"".trim().equals(password)){
            String str="{'msg':'密码不能为空'}";
            jsonObject = JSONObject.parseObject(str);
            return jsonObject;
        }
        if (!Pattern.matches("^1(3|4|5|7|8)\\d{9}$",phoneNumber)){
            String str="{'msg':'手机号码格式有误'}";
            jsonObject = JSONObject.parseObject(str);
            return jsonObject;
        }
        //随机生成校验码
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuffer.append(random.nextInt(10));
        }
        try {
            System.out.println("88888:"+phoneNumber+"--99999:"+stringBuffer.toString());
            if (!PhoneCode.sendCode(phoneNumber,stringBuffer.toString())){
                String str="{'msg':'验证码发送失败'}";
                jsonObject = JSONObject.parseObject(str);
                System.out.println("jsonObject:"+jsonObject);
                return jsonObject;
            }else {
                jedisClient.hset("VerificationCode","code",stringBuffer.toString());
                jedisClient.hset("phoneNumber","number",phoneNumber);
                jedisClient.hset("password","password",password);
                jedisClient.hset("SurvivalTime","time",Long.toString(System.currentTimeMillis()));
                String str="{'msg':'验证码发送成功'}";
                jsonObject = JSONObject.parseObject(str);
                return jsonObject;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 登录
     * @param phoneNumber
     * @param password
     * @return
     */
    @Override
    public Boolean gotoLogin(String phoneNumber, String password) {
        List<UserInfoDO> list = phoneMapperDao.selectAll();
        for (UserInfoDO userInfoDO : list) {
            if (userInfoDO.getPhone().equals(phoneNumber)&&userInfoDO.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
