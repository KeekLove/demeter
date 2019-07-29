package com.demeter.portal.service.impl;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.demeter.common.util.FileSave;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.dao.FoodMapperDao;
import com.demeter.portal.pojo.FindMenuByIdDTO;
import com.demeter.portal.pojo.UploadMenuDTO;
import com.demeter.portal.service.IFoodService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


/**
*@Description 食品的service
*@Author 辜锦龙
*@DateTime 2019/7/24 2
*/

@Service
public class FoodServiceImpl implements IFoodService {

    @Autowired
    private FoodMapperDao foodMapperDao;
    @Autowired
    private JedisClient jedisClient;
    /**
     * 查询餐馆信息、菜单信息等
     * @param pageSize
     * @param pageNum
     * @param token
     * @param card
     * @param menuId
     * @return
     */
    @Override
    public JSON findFood(int pageSize, int pageNum, String token, String card, long menuId) {
        //获取用户id
        Integer id = Integer.valueOf(jedisClient.hget(token, "id"));
        //进行分页
        PageHelper.startPage(pageSize,pageNum);
        List<FindMenuByIdDTO> list = foodMapperDao.findFood(card, menuId,id);
        //打包
        PageInfo<FindMenuByIdDTO> info = new PageInfo<>(list);
        //转换json格式
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(info));
        //返回json
        return jsonObject;
    }

    /**
     * 上传菜品信息
     * @param icon
     * @param token
     * @param prize
     * @param name
     * @param about
     * @param menuId
     * @param restaurantId
     * @return
     * @throws IOException
     */
    @Override
    public JSON insertMenu(MultipartFile icon, String token, Float prize, String name, String about, Long menuId, Long restaurantId) throws IOException {
        HashMap<Object, Object> map = new HashMap<>();
        if (
                null==icon||
                null==prize||
                null==name||
                null==about||
                null==menuId||
                null==restaurantId
        ){
            map.put("status","444");
            map.put("msg","信息输入有误");
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
            return jsonObject;
        }
        FileSave save = new FileSave();
        //图片转换格式
        String iconName = save.saveFile(icon);
        //获取用户id
        Integer id = Integer.valueOf(jedisClient.hget(token, "id"));
        UploadMenuDTO dto = new UploadMenuDTO();
        dto.setAbout(about);
        dto.setIcon(iconName);
        dto.setMenuId(menuId);
        dto.setName(name);
        dto.setPrize(prize);
        dto.setRestaurantId(restaurantId);
        //执行SQL
        Integer time = foodMapperDao.insertMenu(dto);
        if (time==0) {
            map.put("status", "400");
            map.put("msg", "添加失败");
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
            return jsonObject;
        }
        map.put("status","200");
        map.put("msg","添加成功");
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
        return jsonObject;
    }
}
