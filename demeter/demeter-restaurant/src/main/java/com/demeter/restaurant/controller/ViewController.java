package com.demeter.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/view")
@Controller
public class ViewController {
    @RequestMapping("/NotOpening")
    public String notOpen() {
        return  "NotOpening";
    }
}
