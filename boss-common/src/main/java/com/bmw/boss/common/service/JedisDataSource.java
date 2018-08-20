package com.bmw.boss.common.service;

import redis.clients.jedis.Jedis;

/**
 * Created by qxr4383 on 2018/6/8.
 */
public interface JedisDataSource {
    Jedis getRedisClient();
    void returnResource(Jedis jedis);
    void returnResource(Jedis jedis, boolean broken);
}
