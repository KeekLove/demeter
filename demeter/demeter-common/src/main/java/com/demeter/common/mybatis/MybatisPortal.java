package com.demeter.common.mybatis;

import com.demeter.common.data.DruidSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
*@Description   对Manager模块中mybatis的配置
*@Author 刘海亮
*@DateTime 2019/7/21 19:32
*/
@Configuration
public class MybatisPortal {
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
        sqlSessionFactoryBean.setTypeAliasesPackage("com/demeter/common/pojo,com/demeter/portal/pojo");
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
        mapperScannerConfigurer.setBasePackage("com/demeter/portal/dao");
        return mapperScannerConfigurer;
    }
}
