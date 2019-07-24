package com.demeter.restaurant.controller;

import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.restaurant.pojo.FoodAndMenuDto;
import com.demeter.restaurant.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private IRestaurantService iRestaurantService;

    @RequestMapping("/")
    public String indexView(Integer seatNum,String token,String card,Model model) {
            String state=jedisClient.hget("restaurant",card);
            if (!"open".equals(state)){
                return  "redirect:/view/NotOpening";
            }
            RestaurantInfoDO restaurantInfo = iRestaurantService.getResRestaurantInfo(card);
            // 绑定餐馆信息数据
            model.addAttribute("about",restaurantInfo.getAbout());
            model.addAttribute("name",restaurantInfo.getName());
            model.addAttribute("seatNum",seatNum);

            // 绑定菜单信息数据
            List<FoodAndMenuDto> listFoodAndMenu=iRestaurantService.getListFood(restaurantInfo.getId());
            System.out.println(listFoodAndMenu);
            return "index";
    }

    @RequestMapping("/notes")
    public String notesView() {
        return "order_notes";
    }

    @RequestMapping("/payment")
    public String paymentView() {
        return "payment";
    }

    @RequestMapping("/paymentResults")
    public String payment_paymentResultsView() {
        return "payment_results";
    }

    @RequestMapping("/successful")
    public String successfulView() {
        return "successful_trade";
    }
}
