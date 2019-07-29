package com.demeter.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class rootController {
    @RequestMapping("/")
    public String root() {
        return "login";
    }
}
