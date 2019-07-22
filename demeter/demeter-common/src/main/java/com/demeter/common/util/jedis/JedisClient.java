package com.demeter.common.util.jedis;

/**
*@Description jedis的操作工具类
*@Author 刘海亮
*@DateTime 2019/7/21 21:59
*/
public interface JedisClient {
    /**
    *@Description 根据key，设置其值
    *@Author 刘海亮
    *@DateTime 2019/7/21 22:00
    */
    String set(String key, String value);
    /**
    *@Description 根据key，获取对应的值
    *@Author 刘海亮
    *@DateTime 2019/7/21 22:00
    */
    String get(String key);

    /**
    *@Description 设置hash的值，key为字段，item为字段，value为字段对应的值
    *@Author 刘海亮
    *@DateTime 2019/7/21 22:02
    */
    Long hset(String key, String item, String value);
    /**
    *@Description 通过字段和建名获取其value
    *@Author 刘海亮
    *@DateTime 2019/7/22 9:17
    */
    String hget(String key, String item);
    /**
    *@Description 根据键名和字段删除其value
    *@Author 刘海亮
    *@DateTime 2019/7/22 9:18
    */
    Long hdel(String key, String item);
    /**
    *@Description 根据键名删除整个hash
    *@Author 刘海亮
    *@DateTime 2019/7/22 9:19
    */
    Long hDelAll(String key);
    // 自增
    Long incr(String key);
    Long decr(String key);

    /**
    *@Description 设置过期时间
    *@Author 曾锦铭
    *@DateTime 7/22/2019 7/22/2019
    */
    
    Long expire(String key, int second);
    Long ttl(String key);
}
