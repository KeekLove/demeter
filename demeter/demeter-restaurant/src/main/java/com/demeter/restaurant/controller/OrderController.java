package com.demeter.restaurant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demeter.common.pay.Pay;
import com.demeter.common.pojo.RestaurantInfoDO;
import com.demeter.common.pojo.dto.PayDto;
import com.demeter.common.util.jedis.JedisClient;
import com.demeter.restaurant.pojo.FoodAndMenuDto;
import com.demeter.restaurant.pojo.OrderDto;
import com.demeter.restaurant.service.IFoodService;
import com.demeter.restaurant.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.util.resources.ga.LocaleNames_ga;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private IRestaurantService iRestaurantService;
    @Autowired
    private IFoodService iFoodService;

    @RequestMapping("/")
    public String indexView(Integer seatNum, @RequestParam("card") String card,Model model) {
        System.out.println(card);
            String state=jedisClient.hget("restaurant",card);

            if (!"open".equals(state)){
                return  "redirect:/view/NotOpening";
            }
            RestaurantInfoDO restaurantInfo = iRestaurantService.getResRestaurantInfo(card);
            // 绑定餐馆信息数据
            model.addAttribute("about",restaurantInfo.getAbout());
            model.addAttribute("card",card);
            model.addAttribute("name",restaurantInfo.getName());
            model.addAttribute("icon",restaurantInfo.getIcon());
            model.addAttribute("seatNum",seatNum);

            // 绑定菜单信息数据
            List<FoodAndMenuDto> listFoodAndMenu=iRestaurantService.getListFood(restaurantInfo.getId());
            model.addAttribute("foodList",listFoodAndMenu);
            return "index";
    }

    /**
    *@Description 获取账单，并存入redis
    *@Author 刘海亮
    *@DateTime 2019/7/24 16:22
    */
    @RequestMapping("/getNotes")
    @ResponseBody
    public String getNotes(String foodList,String total,String count,String seatNum,String card) {
        System.out.println(foodList);
        System.out.println(total);
        System.out.println(count);

        String uuid= "WaitOrder-"+UUID.randomUUID().toString().replaceAll("-", "");
        //存入缓存
        jedisClient.hset(uuid,"foodList",foodList);
        jedisClient.hset(uuid,"total",total);
        jedisClient.hset(uuid,"count",count);
        jedisClient.hset(uuid,"seatNum",seatNum);
        jedisClient.hset(uuid,"card",card);
        jedisClient.expire(uuid,300);
        return uuid;
    }
    /**
    *@Description 进入待支付页面
    *@Author 刘海亮
    *@DateTime 2019/7/24 20:56
    */
    @RequestMapping("/notes")
    public String notesView(String result,Model model) {
        List<OrderDto> listOrder = JSONObject.parseArray(jedisClient.hget(result,"foodList"),OrderDto.class);
        model.addAttribute("listOrder",listOrder);
        model.addAttribute("total",jedisClient.hget(result,"total"));
        model.addAttribute("count",jedisClient.hget(result,"count"));
        model.addAttribute("seatNum",jedisClient.hget(result,"seatNum"));
        model.addAttribute("card",jedisClient.hget(result,"card"));
        model.addAttribute("result",result);
        return "order_notes";
    }

    //获取备注信息
    @RequestMapping("/remark")
    @ResponseBody
    public void remark(String result,String remark) {
        jedisClient.hset(result,"remark",remark);
    }

    /**
    *@Description 选择支付方式
    *@Author 刘海亮
    *@DateTime 2019/7/24 21:43
    */
    @RequestMapping("/payment")
    public String paymentView(@RequestParam("result") String result, Model model) {
        System.out.println(result);
        model.addAttribute("total",jedisClient.hget(result,"total"));
        model.addAttribute("remark",jedisClient.hget(result,"remark"));
        model.addAttribute("result",result);
        return "payment";
    }

    /**
     *@Description 支付
     *@Author 刘海亮
     *@DateTime 2019/7/24 21:43
     */
    @RequestMapping("/pay")
    public void pay(HttpServletRequest httpRequest,
                      HttpServletResponse httpResponse,String result, Model model) throws Exception{
        Pay pay = new Pay();
        PayDto payDto = new PayDto();
        // 设置我方账单
        Long id = iFoodService.getOrderFoodMaxId()+1L;
        // 获取前端给的金额，餐馆id
        // 找到当前order_user表中最大的id主键，并且加一
        payDto.setOut_trade_no(id.toString());
        payDto.setBody("云智慧餐厅支付");
        payDto.setTotal_amount(jedisClient.hget(result,"total"));
        payDto.setSubject("云智慧餐厅支付");
        iFoodService.setOrder(id,Integer.valueOf(jedisClient.hget(result,"seatNum"))
                ,jedisClient.hget(result,"card")
                ,jedisClient.hget(result,"foodList")
                ,jedisClient.hget(result,"remark"),
                jedisClient.hget(result,"total"));
        pay.payWap(httpRequest,httpResponse,payDto,"http://25b8c82775.wicp.vip:56889/order/callback","http://25b8c82775.wicp.vip:56889/order/paymentResults");

    }

    /**
    *@Description 处理支付宝的回调函数
    *@Author 刘海亮
    *@DateTime 2019/7/25 10:06
    */
    @RequestMapping("/callback")
    public String callback(String out_trade_no,String trade_no ,String total_amount,Model model) {
        if ((trade_no==null||trade_no.equals(""))){
            return null;
        }
        iFoodService.setTrade_no(Long.valueOf(out_trade_no),trade_no);
        RestaurantInfoDO restaurantInfo = iFoodService.getRestaurantInfoByOrderId(Long.valueOf(out_trade_no));

        if (jedisClient.hget(restaurantInfo.getCard(),"waitNum")==null) {
            jedisClient.hset(restaurantInfo.getCard(),"waitNum","1");
            return "payment_results";
        }
        String card=restaurantInfo.getCard();
        //设置账单
        jedisClient.hset(card,"waitNum",
                String.valueOf(Integer.valueOf(jedisClient.hget(restaurantInfo.getCard(),"num"))+1));
        //找到
        Integer waitNum=Integer.valueOf(jedisClient.hget(card,"waitNum"));

        for (Integer i=1;i<waitNum;i++){
            if (jedisClient.hget(card,i.toString())==null){
                jedisClient.hset(card,i.toString(),out_trade_no);
                break;
            }
        }
        jedisClient.hset(card,"waitNum",
                String.valueOf(Integer.valueOf(jedisClient.hget(restaurantInfo.getCard(),"num"))+1));
        model.addAttribute("total_amount",total_amount);
        model.addAttribute("trade_no",trade_no);
        model.addAttribute("name",restaurantInfo.getName());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("time",df.format(new Date()));
        return "payment_results";
    }

    @RequestMapping("/paymentResults")
    public String payment_paymentResultsView(String out_trade_no,String trade_no ,String total_amount,Model model) {
        if ((trade_no==null||trade_no.equals(""))){
            return null;
        }
        RestaurantInfoDO restaurantInfo = iFoodService.getRestaurantInfoByOrderId(Long.valueOf(out_trade_no));
        String card=restaurantInfo.getCard();
        jedisClient.hset(card,"waitNum",
                String.valueOf(Integer.valueOf(jedisClient.hget(restaurantInfo.getCard(),"num"))+1));
        model.addAttribute("total_amount",total_amount);
        model.addAttribute("trade_no",trade_no);
        model.addAttribute("name",restaurantInfo.getName());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("time",df.format(new Date()));
        return "payment_results";
    }

    @RequestMapping("/help")
    @ResponseBody
    public String help(String card,String seatNum) {
        jedisClient.hset(card,"service",seatNum);
        return "{\"status\":200}";
    }

    @RequestMapping("/successful")
    public String successfulView() {
        return "successful_trade";
    }
}
