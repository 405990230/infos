package com.bmw.boss.common.util.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;

/**
 * Created by qxr4383 on 2018/4/4.
 */
public class RedisUtilByCode {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtilByCode.class);

    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            final String host =  RedisServerEnvironment.getPropertyString("redis.host");
            final Integer port = RedisServerEnvironment.getPropertyInteger("redis.port");
            final String auth = RedisServerEnvironment.getPropertyString("redis.pass");
            final Integer timeout = RedisServerEnvironment.getPropertyInteger("redis.timeout");
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(RedisServerEnvironment.getPropertyInteger("redis.maxTotal"));
            config.setMaxIdle( RedisServerEnvironment.getPropertyInteger("redis.maxIdle"));
            config.setMaxWaitMillis( RedisServerEnvironment.getPropertyInteger("redis.maxWaitMillis"));
            config.setTestOnBorrow(true);
            jedisPool = new JedisPool(config, host, port, timeout, auth, RedisServerEnvironment.getPropertyInteger("redis.dbIndex"));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("RedisUtilByCode 初始化Redis连接池异常 : " + e);
        }
    }

    /**
     * Redis setNX 保存字符串 增加锁数据
     * <p>
     * 返回0 -> key已存在,无法覆盖值
     * 返回1 -> 插入成功
     * seconds   缓存时间
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public static Long setNXData(String key, String value, Integer seconds) {
        Long ret = 0l;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            ret = jedis.setnx(key, value);
            if (ret == 1 && seconds != null) {
                jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("RedisUtilByCode setNXData() : " + e);
        } finally {
            jedis.close();
        }
        return ret;
    }

    /**
     * Redis保存字符串
     *
     * @param key
     * @param value
     * @param seconds
     */
    public static void setData(String key, String value, Integer seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            if (seconds != null) {
                jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("RedisUtilByCode setData() : " + e);
        } finally {
            jedis.close();
        }
    }

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    public static String getData(String key) {
        Jedis jedis = null;
        String ret = "";
        try {
            jedis = jedisPool.getResource();
            ret = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("RedisUtilByCode getData() : " + e);
        } finally {
            jedis.close();
        }
        return ret;
    }

    /**
     * 删除数据
     *
     * @param key
     */
    public static Long delData(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("RedisUtilByCode delData() : " + e);
        } finally {
            jedis.close();
        }
        return 0L;
    }

    /**
     * add list
     *
     * @param key
     * @param value
     * @param seconds
     */
    public static void addList(String key, String value, Integer seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.rpush(key, value);
            if (seconds != null) {
                jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("RedisUtilByCode addList() : " + e);
        } finally {
            jedis.close();
        }
    }

    /**
     * 获取list的长度
     *
     * @param key
     * @return
     */
    public static Long listLength(String key) {
        Jedis jedis = null;
        Long llen = 0l;
        try {
            jedis = jedisPool.getResource();
            llen = jedis.llen(key);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("RedisUtilByCode addList() : " + e);
        } finally {
            jedis.close();
        }
        return llen;
    }




    // add -> Set
    public static void addSet(String key, String value, Integer seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.sadd(key, value);
            if (seconds != null) {
                jedis.expire(key, seconds);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("RedisUtilByCode addSet() : " + e);
        } finally {
            jedis.close();
        }
    }

    // get -> Set
//    public static Set<String> getSet(String key) {
//        Jedis jedis = null;
//        Set<String> set = Sets.newConcurrentHashSet();
//        try {
//            jedis = jedisPool.getResource();
//            set = jedis.smembers(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error("RedisUtilByCode getSet() : " + e);
//        } finally {
//            jedis.close();
//        }
//        return set;
//    }

    // remove -> Set
    public static Long removeSet(String key, String members) {
        Jedis jedis = null;
        Long l = 0L;
        try {
            jedis = jedisPool.getResource();
            l = jedis.srem(key, members);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("RedisUtilByCode getSet() : " + e);
        } finally {
            jedis.close();
        }
        return l;
    }


    // ----------------------------- GEO -----------------------------

    /**
     * add单个坐标
     *
     * @param key
     * @param longitude 经度
     * @param latitude  纬度
     * @param member    唯一标识
     */
    public static Long geoadd(String key, double longitude, double latitude, String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geoadd(key, longitude, latitude, member);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return 0L;
    }

    /**
     * GEO 查询
     *
     * @param key
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    半径
     * @param unit      单位
     * @param sort      ASC|DESC：默认null结果是未排序的，传入ASC为从近到远排序，传入DESC为从远到近排序
     * @param count     传入COUNT参数，可以返回指定数量的结果 null 不限制
     * @param withdist  传入WITHDIST参数，则返回结果会带上匹配位置与给定地理位置的距离
     *                  传入WITHHASH参数，则返回结果会带上匹配位置的hash值
     *                  传入WITHCOORD参数，则返回结果会带上匹配位置的经纬度
     * @return
     */
    public static List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit,
                                                    String sort, Integer count, boolean withdist) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            GeoRadiusParam param = GeoRadiusParam.geoRadiusParam();
            param.withCoord();
            if (count != null) {
                param.count(count);
            }
            if (withdist) {
                param.withDist();
            }
//            if (Constants.ASC.equals(sort)) {
//                param.sortAscending();
//            } else if (Constants.DESC.equals(sort)) {
//                param.sortDescending();
//            }
            return jedis.georadius(key, longitude, latitude, radius, unit, param);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 查询
     *
     * @param key
     * @param members
     * @return
     */
    public static List<GeoCoordinate> geopos(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.geopos(key, members);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return null;
    }

    /**
     * 删除一个或者多个坐标
     * <p>
     * zrem(key, member...member) ：删除名称为key的zset中的元素member
     */
    public static Long zrem(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrem(key, members);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return 0L;
    }
}
