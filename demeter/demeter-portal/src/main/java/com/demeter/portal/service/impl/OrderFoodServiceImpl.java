package com.demeter.portal.service.impl;

import com.demeter.portal.dao.ManMapper;
import com.demeter.portal.service.IOrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFoodServiceImpl implements IOrderFoodService {
    @Autowired
    private ManMapper manMapper;

    @Override
    public List View(String pageNum,String pageSize,Integer id) {
//        manMapper.findOrderFoodDo(pageNum,pageSize,"fd");
        manMapper.selectById(id);
        return null;
    }
}
