package com.travelbuddy.auth.service;

import java.util.concurrent.TimeUnit;

public interface UserAuthRedisService {
    void save(String key, String value);

    void save(String key, String value, long timeout, TimeUnit unit);

    String get(String key);

    void delete(String key);

    void expire(String key, long timeout, TimeUnit unit);

    Long increment(String key, int i);

    void save(String key, int i, int timeout, TimeUnit timeUnit);
}
