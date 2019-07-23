package com.demeter.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class viewController {
    @RequestMapping("/")
    public String indexView() {
        return "index";
    }
}
