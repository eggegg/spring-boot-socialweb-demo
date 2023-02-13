package com.imooc.socialweb.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisOperation {

    @Autowired
    private RedisTemplate redisTemplate;

    @Async
    public void asyncRedisStringSet(String key, Object data, long expireTime, TimeUnit expireTimeUnit){
        redisTemplate.opsForValue().set(key, data,expireTime,expireTimeUnit);
    }
}

