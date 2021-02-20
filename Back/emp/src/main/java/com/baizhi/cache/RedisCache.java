/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: RedisCache
 * Author:   Administrator
 * Date:     2020/9/8 10:52
 * Description: Redis缓存
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.baizhi.cache;

import com.baizhi.utils.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Redis缓存〉
 *
 * @author Administrator
 * @create 2020/9/8
 * @since 1.0.0
 */
@Slf4j
public class RedisCache implements Cache {
    private String id;

    public RedisCache(String id) {
        log.info("当前的Id为：[{}] ",id);
        this.id = id;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    @Override
    public String getId() {

        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        log.info("放入缓存的key为：[{}] 值是：[{}]",key,value);
        RedisTemplate redisTemplate = getRedisTemplate();
        if (redisTemplate != null){
            redisTemplate.opsForHash().put(id,key.toString(),value);
        }
    }

    @Override
    public Object getObject(Object key) {
        log.info("获取缓存的key为：[{}] ",key);
        RedisTemplate redisTemplate = getRedisTemplate();
        if (redisTemplate != null){
            return  redisTemplate.opsForHash().get(id,key.toString());
        }
        return null;
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {
        log.info("清除所有缓存信息... ");
        RedisTemplate redisTemplate = getRedisTemplate();
        if (redisTemplate != null){
            redisTemplate.delete(id);
        }
    }

    @Override
    public int getSize() {

        RedisTemplate redisTemplate = getRedisTemplate();
        if (redisTemplate != null){
            return  redisTemplate.opsForHash().size(id).intValue();
        }
        return 0;
    }

    //封装获取RedisTemplate的方法
    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate)ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        return redisTemplate;
    }

}
