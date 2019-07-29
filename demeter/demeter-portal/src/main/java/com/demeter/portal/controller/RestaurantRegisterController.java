package com.demeter.portal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.pojo.RestaurantRegisterDTO;
import com.demeter.portal.pojo.StatusVO;
import com.demeter.portal.service.IRestaurantRegisterService;
import com.demeter.portal.util.FileSave;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
*@Description 餐馆注册
*@Author 陈龙鑫
*@DateTime 2019/7/26 2019/7/26
*/
@RequestMapping("/card")
@Controller
public class RestaurantRegisterController {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private IRestaurantRegisterService restaurantRegisterService;

    @RequestMapping(value = "/uploadPage", method = RequestMethod.GET)
    public String uploadPage() {
        return "upload";
    }
    @RequestMapping(value = "/updatePage", method = RequestMethod.GET)
    public String updatePage(Long id) {
        return "restaurantInfo";
    }

    /**
     * 餐馆注册
     * 路径：/card/upload
     * @param icon 餐馆图片
     * @param registerId 营业执照注册编号
     * @param about 简介
     * @param name 餐馆名字
     * @param province 省份
     * @param city 城市
     * @param address 详细地址
     * @param token token
     * @return JSON格式的提示信息
     * @throws IOException
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public JSON register(@Param("icon") MultipartFile icon,
                         @Param("registerId") String registerId,
                         @Param("about") String about,
                         @Param("name") String name,
                         @Param("province") String province,
                         @Param("city") String city,
                         @Param("address") String address,
                         @Param("token") String token) throws IOException {


        List list = new ArrayList();
        list.add(registerId);
        list.add(about);
        list.add(icon);
        list.add(name);
        list.add(province);
        list.add(city);
        list.add(address);
        list.add(token);
        for (int i = 0; i < list.size(); i++) {
            if (null == list.get(i))
            {
                StatusVO statusVO = new StatusVO();
                statusVO.setStatus(400);
                statusVO.setMsg("信息输入有误");
                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(statusVO));
                return jsonObject;
            }

        }
        Integer id = Integer.valueOf(jedisClient.hget(token, "id"));
        FileSave fileSave = new FileSave();
        String iconName = fileSave.saveFile(icon);
        RestaurantRegisterDTO restaurantRegisterDTO = new RestaurantRegisterDTO();
        restaurantRegisterDTO.setRegisterId(Integer.parseInt(registerId));
        restaurantRegisterDTO.setAbout(about);
        restaurantRegisterDTO.setAddress(address);
        restaurantRegisterDTO.setCity(city);
        restaurantRegisterDTO.setDel(0);
        restaurantRegisterDTO.setIcon(iconName);
        restaurantRegisterDTO.setName(name);
        restaurantRegisterDTO.setProvince(province);
        restaurantRegisterDTO.setUserId(id.longValue());
        Integer result = restaurantRegisterService.register(restaurantRegisterDTO);
        if (result <= 0)
        {
            StatusVO statusVO = new StatusVO();
            statusVO.setStatus(400);
            statusVO.setMsg("错误");
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(statusVO));
            return jsonObject;
        }else
        {
            StatusVO statusVO = new StatusVO();
            statusVO.setStatus(200);
            statusVO.setMsg("成功");
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(statusVO));
            return jsonObject;
        }

    }

    /**
     * 餐馆信息回显
     * 路径：/card/show
     * @param id 餐馆id
     * @return JSON格式的餐馆信息
     *      `id`,餐馆id
     * 		`register_id`,注册编号
     * 		`about`,简介
     * 		`icon`,头像
     * 		`name`,餐馆名
     * 		`province`,省份
     * 		`city`,城市
     * 		`address`,详细地址
     * 		`user_id`,用户id
     * 		`del` 状态
     */
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public JSON findById(@Param("id") Long id)
    {
        RestaurantInfoDO restaurantInfoDO = restaurantRegisterService.findById(id);
        System.out.println(restaurantInfoDO);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(restaurantInfoDO));
        return jsonObject;
    }

    /**
     * 餐馆信息修改
     * 路径：/card/update
     * @param icon 头像
     * @param registerId 注册编号
     * @param about 简介
     * @param name 餐馆名
     * @param province 省份
     * @param city 城市
     * @param address 详细地址
     * @param del 状态
     * @param userId 用户id
     * @param id 餐馆id（用于查找数据）
     * @return 返回一个JSON信息提示
     * @throws IOException
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JSON update(@Param("icon") MultipartFile icon,
                       @Param("registerId") String registerId,
                       @Param("about") String about,
                       @Param("name") String name,
                       @Param("province") String province,
                       @Param("city") String city,
                       @Param("address") String address,
                       @Param("del") int del,
                       @Param("userId") Long userId,
                       @Param("id") Long id) throws IOException {
        String iconName=null;

        List list = new ArrayList();
        list.add(registerId);
        list.add(about);
        list.add(name);
        list.add(province);
        list.add(city);
        list.add(address);

        for (int i = 0; i < list.size(); i++) {
            if (null == list.get(i))
            {
                StatusVO statusVO = new StatusVO();
                statusVO.setStatus(400);
                statusVO.setMsg("信息输入有误");
                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(statusVO));
                return jsonObject;
            }

        }
        if (icon.getSize()>0)
        {
            FileSave fileSave = new FileSave();
            iconName = fileSave.saveFile(icon);
        }
        RestaurantRegisterDTO restaurantRegisterDTO = new RestaurantRegisterDTO();
        restaurantRegisterDTO.setRegisterId(Integer.parseInt(registerId));
        restaurantRegisterDTO.setAbout(about);
        restaurantRegisterDTO.setAddress(address);
        restaurantRegisterDTO.setCity(city);
        restaurantRegisterDTO.setDel(del);
        restaurantRegisterDTO.setIcon(iconName);
        restaurantRegisterDTO.setName(name);
        restaurantRegisterDTO.setProvince(province);
        restaurantRegisterDTO.setUserId(userId);
        restaurantRegisterDTO.setId(id);
        System.out.println(restaurantRegisterDTO);
        Integer result = restaurantRegisterService.update(restaurantRegisterDTO);
        if (result <= 0)
        {
            StatusVO statusVO = new StatusVO();
            statusVO.setStatus(400);
            statusVO.setMsg("错误");
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(statusVO));
            return jsonObject;
        }else
        {
            StatusVO statusVO = new StatusVO();
            statusVO.setStatus(200);
            statusVO.setMsg("成功");
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(statusVO));
            return jsonObject;
        }

    }
}
