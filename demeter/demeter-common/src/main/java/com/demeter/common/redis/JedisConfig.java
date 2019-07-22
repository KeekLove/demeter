package com.demeter.common.redis;

import com.demeter.common.util.jedis.JedisClient;
import com.demeter.common.util.jedis.JedisClientImpl;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
*@Description jedis的配置
*@Author 刘海亮
*@DateTime 2019/7/21 21:45
*/
@Configuration
public class JedisConfig {
    /**
    *@Description 配置jedis的连接池配置
    *@Author 刘海亮
    *@DateTime 2019/7/21 21:45
    */
    @Bean
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(30);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setNumTestsPerEvictionRun(1024);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(20000);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(10000);
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(1800000);
        jedisPoolConfig.setMaxWaitMillis(1500);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setBlockWhenExhausted(false);
        return jedisPoolConfig;
    }
    
    /**
    *@Description 配置redis连接
    *@Author 刘海亮
    *@DateTime 2019/7/21 21:50
    */
    public JedisPool getJedisPool() {
        JedisPool jedisPool = new JedisPool(
                getJedisPoolConfig()
                ,"193.112.24.39"
                ,9999
                ,2000
                ,"123456"
                ,1
        );

        return jedisPool;
    }
    /**
    *@Description 设置jedis的实现类bean
    *@Author 刘海亮
    *@DateTime 2019/7/21 21:56
    */
    @Bean
    public JedisClient getJedisClientImpl(){
        JedisClient jedisClient = new JedisClientImpl();
        return jedisClient;
    }
}
