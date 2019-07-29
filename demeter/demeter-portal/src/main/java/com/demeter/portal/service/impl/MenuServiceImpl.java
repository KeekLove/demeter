package com.demeter.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demeter.common.util.FileSave;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.portal.dao.FoodMapperDao;
import com.demeter.portal.pojo.SelectMenuDTO;
import com.demeter.portal.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private FoodMapperDao foodMapperDao;
    @Autowired
    private JedisClient jedisClient;


    /**
     * 获取目录菜单
     * @param card
     * @param token
     * @return
     */
    @Override
    public JSON findMenuByCard(String card, String token) {
        //获取用户id
        Integer id = Integer.valueOf(jedisClient.hget(token, "id"));
        //执行SQL
        List<SelectMenuDTO> menu = foodMapperDao.findMenu(card);
        //转化json
        JSONArray jsonObject =  (JSONArray)JSON.toJSON(menu);
        return jsonObject;
    }

    /**
     * 删除菜单
     * @param menuId
     * @param token
     * @return
     */
    @Override
    public JSON delMenuByMenuId(Long menuId, String token) {
        HashMap<Object, Object> map = new HashMap<>();
        //获取用户id
        Integer id = Integer.valueOf(jedisClient.hget(token, "id"));
        //用户传入id 删除菜单
        Integer integer = foodMapperDao.delMenu(menuId);
        if (integer==0) {
            map.put("status", "400");
            map.put("msg", "删除失败");
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
            return jsonObject;
        }
        map.put("status","200");
        map.put("msg","删除成功");
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
        return jsonObject;
    }

    /**
     * 修改菜单
     * @param id
     * @param icon
     * @param prize
     * @param name
     * @param about
     * @param menuId
     * @return
     */
    @Override
    public JSON updateById(Long id, MultipartFile icon, Float prize, String name, String about, Long menuId) throws IOException {
        FileSave save = new FileSave();
        //图片转换格式
        String iconName = save.saveFile(icon);
        HashMap<Object, Object> map = new HashMap<>();
        Integer time = foodMapperDao.updateById(id,iconName,prize,name,about,menuId);
        if (time!=0) {
            map.put("status", "200");
            map.put("msg", "修改成功");
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
            return jsonObject;
        }
        map.put("status", "400");
        map.put("msg", "修改异常");
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
        return jsonObject;
    }
}
