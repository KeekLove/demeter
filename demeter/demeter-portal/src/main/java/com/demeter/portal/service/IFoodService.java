package com.demeter.portal.service;

import com.alibaba.fastjson.JSON;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface IFoodService {
    /**
     * 查询餐馆信息、菜单信息等
     * @param pageSize
     * @param pageNum
     * @param token
     * @param card
     * @param menuId
     * @return
     */
    JSON findFood(int pageSize, int pageNum, String token, String card, long menuId);

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
    JSON insertMenu(MultipartFile icon, String token, Float prize, String name, String about, Long menuId, Long restaurantId) throws IOException;
}
