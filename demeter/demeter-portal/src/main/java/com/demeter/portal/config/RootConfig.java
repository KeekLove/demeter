package com.demeter.portal.config;

import com.demeter.common.data.Transation;
import com.demeter.common.mybatis.MybatisPortal;
import com.demeter.common.redis.JedisConfig;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
*@Description 非web配置文件
*@Author 刘海亮
*@DateTime 2019/7/20 20:17
*/
@Configuration
@Import( {MybatisPortal.class, Transation.class, JedisConfig.class})
//@ImportResource("classpath:spring-context-mybatis.xml")
@ComponentScan(basePackages = {"com.demeter.portal"},
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
    }
)
public class RootConfig {

}
