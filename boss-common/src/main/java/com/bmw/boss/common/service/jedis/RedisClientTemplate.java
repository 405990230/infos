package com.bmw.boss.common.service.jedis;

import com.bmw.boss.common.service.JedisDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

/**
 * Created by qxr4383 on 2018/6/8.
 */
@Repository("redisClientTemplate")
public class RedisClientTemplate {
    private static final Logger log = LoggerFactory.getLogger(RedisClientTemplate.class);

    @Autowired
    private JedisDataSource redisDataSource;

    public void disconnect() {
        Jedis jedis = redisDataSource.getRedisClient();
        jedis.disconnect();
    }

    /**
     * 设置单个值
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value,int seconds) {
        String result = null;

        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.set(key, value);
            jedis.expire(key, seconds);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    /**
     * 获取单个值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = jedis.get(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    public Boolean exists(String key) {
        Boolean result = false;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    public String type(String key) {
        String result = null;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.type(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    /**
     * 在某段时间后失效
     *
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key, int seconds) {
        Long result = null;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.expire(key, seconds);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    /**
     * 在某个时间点失效
     *
     * @param key
     * @param unixTime
     * @return
     */
    public Long expireAt(String key, long unixTime) {
        Long result = null;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.expireAt(key, unixTime);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    public Long ttl(String key) {
        Long result = null;
        Jedis jedis = redisDataSource.getRedisClient();
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.ttl(key);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    public boolean setbit(String key, long offset, boolean value) {

        Jedis jedis = redisDataSource.getRedisClient();
        boolean result = false;
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.setbit(key, offset, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    public boolean getbit(String key, long offset) {
        Jedis jedis = redisDataSource.getRedisClient();
        boolean result = false;
        if (jedis == null) {
            return result;
        }
        boolean broken = false;

        try {
            result = jedis.getbit(key, offset);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    public long setRange(String key, long offset, String value) {
        Jedis jedis = redisDataSource.getRedisClient();
        long result = 0;
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.setrange(key, offset, value);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }

    public String getRange(String key, long startOffset, long endOffset) {
        Jedis jedis = redisDataSource.getRedisClient();
        String result = null;
        if (jedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = jedis.getrange(key, startOffset, endOffset);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(jedis, broken);
        }
        return result;
    }
}

