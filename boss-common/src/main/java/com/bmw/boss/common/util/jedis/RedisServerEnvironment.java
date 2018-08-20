package com.bmw.boss.common.util.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by qxr4383 on 2018/5/16.
 */
public class RedisServerEnvironment {
    private static Properties redisMap = null;
    private static Logger logger = LoggerFactory.getLogger(RedisServerEnvironment.class);
    static {
        Properties properties = new Properties();
        InputStream input = null;

        ClassLoader classLoader = RedisServerEnvironment.class.getClassLoader();
        InputStream mapInput = null;
        try {
            mapInput = new FileInputStream(classLoader.getResource("properties/redis.properties").getFile());
            redisMap = new Properties();
            redisMap.load(mapInput);
            mapInput.close();
        } catch (IOException e) {
            logger.error("No redis Map file!", e);
        }
    }
    public static Properties getWeatherMap() {
        return redisMap;
    }

    //static method for accessing context properties
    public static String getPropertyString(String name) {
        return ((String)redisMap.get(name)).trim();
    }
    //static method for accessing context properties
    public static Integer getPropertyInteger(String name) {
        return Integer.valueOf(((String)redisMap.get(name)).trim());
    }

}
