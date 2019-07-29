package com.demeter.portal.controller;

import com.alibaba.fastjson.JSON;
import com.demeter.portal.service.IMenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
/**
*@Description
*@Author 辜锦龙
*@DateTime 2019/7/26
*/

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService iMenuService;

    /**
     * 获取菜单
     * 路径：http://localhost:8080/menu/listMenu
     * @param card
     * @param token
     * @return
     */
    @RequestMapping(value = "/listMenu",method = RequestMethod.POST)
    @ResponseBody
    public JSON findMenuByCard(String card, String token){
        //去service处理
        JSON json = iMenuService.findMenuByCard(card, token);
        return json;
    }


    /**
     * 删除菜单
     * 路径：http://localhost:8080/menu/del
     * @param menuId
     * @param token
     * @return
     */

    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public JSON delMenuByMenuId(Long menuId,String token){
        JSON json = iMenuService.delMenuByMenuId(menuId, token);
        return json;
    }

    /**
     * 修改菜单
     * 路径：http://localhost:8080/menu/update
     * @param id id
     * @param icon 图片
     * @param prize 价格
     * @param name 名字
     * @param about 描述
     * @param menuId 套餐id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JSON updateById(@Param("id") Long id
            ,@Param("icon") MultipartFile icon
            ,@Param("prize") Float prize
            ,@Param("name") String name
            ,@Param("about") String about
            ,@Param("menuId") Long menuId) throws IOException {
        JSON json = iMenuService.updateById(id, icon, prize, name, about, menuId);
        return json;
    }
}
