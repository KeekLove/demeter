package com.demeter.portal.controller;

import com.alibaba.fastjson.JSON;
import com.demeter.common.pojo.PackageTypeDO;
import com.demeter.portal.service.IPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/pack")
@Controller
public class PackController {
    @Autowired
    private IPackService iPackService;
    @RequestMapping("/get")
    @ResponseBody
    public JSON getPack() {
        List<PackageTypeDO> list = iPackService.getListPack();
        return JSON.parseArray(JSON.toJSONString(list));
    }
}
