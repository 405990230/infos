package com.bmw.boss.common.service.impl;

import com.bmw.boss.common.service.JedisDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by qxr4383 on 2018/6/8.
 */
@Repository("jedisDS")
public class JedisDataSourceImpl implements JedisDataSource {
    private static final Logger logger = LoggerFactory.getLogger(JedisDataSourceImpl.class);

    @Autowired
    private JedisPool jedisPool;

    @Override
    public Jedis getRedisClient() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis;
        } catch (Exception e) {
            logger.error("[JedisDS] getRedisClent error:" + e.getMessage());
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    @Override
    public void returnResource(Jedis jedis) {
        jedis.close();
    }

    @Override
    public void returnResource(Jedis jedis, boolean broken) {
        jedis.close();
    }
}
