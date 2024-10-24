package com.travelbuddy.auth.service.impl;

import com.travelbuddy.auth.service.UserAuthRedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserAuthRedisServiceImpl  implements UserAuthRedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public UserAuthRedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void save(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void expire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    @Override
    public Long increment(String key, int i) {
        return redisTemplate.opsForValue().increment(key, i);
    }

    @Override
    public void save(String key, int i, int timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, i, timeout, timeUnit);
    }
}
