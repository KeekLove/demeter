package com.demeter.common.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
*@Description 事务管理配置
*@Author 刘海亮
*@DateTime 2019/7/21 21:45
*/
@Configuration
public class Transation {
    @Bean
    public DataSourceTransactionManager transactionManager() {
        DruidSource druidSource = new DruidSource();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(druidSource.getDataSource());
        return transactionManager;
    }
}
