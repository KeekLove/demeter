package com.demeter.portal.controller;

import com.alibaba.fastjson.JSON;
import com.demeter.common.pojo.UserInfoDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.dao.PhoneMapperDao;
import com.demeter.portal.service.IPhoneService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
/**
*@Description
*@Author 辜锦龙
*@DateTime 2019/7/26
*/

@Controller
@RequestMapping("/phone")
public class PhoneController extends HttpServlet {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private PhoneMapperDao phoneMapperDao;

    @Autowired
    private IPhoneService iPhoneService;

    /**
     * 注册
     * 路径：http://localhost:8080//phone/Verification
     * @param phoneNumber 手机号码
     * @param code 验证码
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/Verification", method = RequestMethod.GET)
    @ResponseBody
    public JSON checkPhoneVerification(@Param("phoneNumber") String phoneNumber,
                                       @Param("code") String code,
                                       @Param("password")String password){

        JSON json = iPhoneService.checkPhoneVerification(phoneNumber,code,password);
        System.out.println("uuu"+json.toString());
        return json;
    }

    /**
     * 发送验证码
     * 路径：http://localhost:8080//phone/code
     * @param phoneNumber 手机号码
     * @param password 密码
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    @ResponseBody
    public JSON sellPhoneVerification(@Param("phoneNumber") String phoneNumber,@Param("password") String password) throws IOException {
        JSON json = iPhoneService.sellPhoneVerification(phoneNumber,password);
        return json;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JSON gotoLogin(@Param("phoneNumber") String phoneNumber, @Param("password") String password, Model model){
        Boolean flag = iPhoneService.gotoLogin(phoneNumber, password);
        if (flag){
            UserInfoDO userInfo = phoneMapperDao.selectUserByid(phoneNumber);
            jedisClient.hset(phoneNumber,"username",userInfo.getPhone());
            jedisClient.hset(phoneNumber,"id",userInfo.getId().toString());
            String msg="{\"phone\":"+phoneNumber+"}";
            return JSON.parseObject(msg);//用户登录成功后需要返回的页面

        }
        String msg="{\"state\":400}";
        return JSON.parseObject(msg);//shibai
    }

    /**
     * 这是去登录页面的跳转方法
     * @return
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)

    public String gotoLoginMode(){

        return "/login";
    }
}
