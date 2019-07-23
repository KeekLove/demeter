package com.demeter.restaurant.config;

import com.demeter.common.data.Transation;
import com.demeter.common.mybatis.MybatisManager;
import com.demeter.common.mybatis.MybatisRestaurant;
import com.demeter.common.redis.JedisConfig;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
*@Description 非web配置文件
*@Author 刘海亮
*@DateTime 2019/7/20 20:17
*/
@Configuration
@Import({MybatisRestaurant.class, JedisConfig.class, Transation.class, })
@ImportResource("classpath:json.xml")
@ComponentScan(basePackages = {"com.demeter.restaurant"},
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
    }
)
public class RootConfig {

}
