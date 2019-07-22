package com.demeter.manager.config;

import com.demeter.common.mybatis.MybatisManager;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
*@Description 非web配置文件
*@Author 刘海亮
*@DateTime 2019/7/20 20:17
*/
@Configuration
@Import(MybatisManager.class)
@ImportResource("classpath:json.xml")
@ComponentScan(basePackages = {"com.demeter.manager"},
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
    }
)
public class RootConfig {

}
