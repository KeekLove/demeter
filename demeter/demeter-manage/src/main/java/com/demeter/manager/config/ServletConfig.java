package com.demeter.manager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
*@Description web配置文件
*@Author 刘海亮
*@DateTime 2019/7/20 20:12
*/

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.demeter.manager"})
public class ServletConfig  implements WebMvcConfigurer {

}
