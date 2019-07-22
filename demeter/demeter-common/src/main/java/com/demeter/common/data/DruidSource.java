package com.demeter.common.data;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ResourceBundle;

/**
*@Description 数据库源database的druid配置
*@Author 刘海亮
*@DateTime 2019/7/20 16:51
*/
@Configuration
public class DruidSource {

    ResourceBundle resource = ResourceBundle.getBundle("resources/jdbc");
    DruidDataSource dataSource = new DruidDataSource();
    /**
    *@Description 返回一个配置好得druid数据源实例
    *@Author 刘海亮
    *@DateTime 2019/7/20 17:10
    */
    @Bean
    public DataSource getDataSource(){
        // 设置基本连接属性
        dataSource.setDriverClassName(resource.getString("jdbc.driverClass"));
        dataSource.setUrl(resource.getString("jdbc.connectionURL"));
        dataSource.setUsername(resource.getString("jdbc.username"));
        dataSource.setPassword(resource.getString("jdbc.password"));
        // 配置初始化大小、最小、最大
        dataSource.setInitialSize(Integer.valueOf(resource.getString("jdbc.pool.init")));
        dataSource.setMinIdle(Integer.valueOf(resource.getString("jdbc.pool.minIdle")));
        dataSource.setMaxActive(Integer.valueOf(resource.getString("jdbc.pool.maxActive")));
        // 配置获取连接等待超时的时间
        dataSource.setMaxWait(Long.valueOf(resource.getString("jdbc.pool.maxWait")));
        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(Long.valueOf(resource.getString("jdbc.pool.timeBetweenEvictionRunsMillis")));
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(Long.valueOf(resource.getString("jdbc.pool.minEvictableIdleTimeMillis")));
        dataSource.setValidationQuery(resource.getString("jdbc.testSql"));
        return dataSource;
    }
}
