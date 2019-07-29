package com.demeter.portal.service;

import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IMenuService {
    /**
     * 获取目录菜单
     * @param card
     * @param token
     * @return
     */
    JSON findMenuByCard(String card, String token);

    /**
     *  删除菜单
     * @param menuId
     * @param token
     * @return
     */
    JSON delMenuByMenuId(Long menuId, String token);

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
    JSON updateById(Long id, MultipartFile icon, Float prize, String name, String about, Long menuId) throws IOException;
}
