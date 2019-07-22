package com.demeter.common.mybatis;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.demeter.common.data.DruidSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
*@Description   对Manager模块中mybatis的配置
*@Author 刘海亮
*@DateTime 2019/7/21 19:32
*/
@Configuration
public class MybatisManager {
    /**
     *@Description 设置SqlSessionFactoryBean
     *@Author 刘海亮
     *@DateTime 2019/7/20 20:25
     */
    @Bean
    public SqlSessionFactoryBean getSqlSessionFactoryBean() throws IOException {
        DruidSource druidSource = new DruidSource();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidSource.getDataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage("com/demeter/manager/pojo,com/demeter/common/pojo");
        PathMatchingResourcePatternResolver pathMatchResolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(pathMatchResolver.getResources("mapper/*.xml"));
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("/mybatis/mybatis-config.xml"));
        return sqlSessionFactoryBean;
    }

    /**
     *@Description 设置Mapper扫描
     *@Author 刘海亮
     *@DateTime 2019/7/21 18:45
     */
    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com/demeter/manager/dao");
        return mapperScannerConfigurer;
    }



}
