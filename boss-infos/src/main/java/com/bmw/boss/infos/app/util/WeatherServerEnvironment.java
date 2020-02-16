package com.bmw.boss.infos.app.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by qxr4383 on 2018/5/16.
 */
public class WeatherServerEnvironment {
    private static Properties weatherMap = null;
    private static Logger logger = LoggerFactory.getLogger(WeatherServerEnvironment.class);
    static {
        Properties properties = new Properties();
        InputStream input = null;

        ClassLoader classLoader = WeatherServerEnvironment.class.getClassLoader();
        InputStream mapInput = null;
        try {
            mapInput = new FileInputStream(classLoader.getResource("properties/weather_map.properties").getFile());
            weatherMap = new Properties();
            weatherMap.load(mapInput);
            mapInput.close();
        } catch (IOException e) {
            logger.error("No Weather Map file!", e);
        }
    }
    public static Properties getWeatherMap() {
        return weatherMap;
    }

}
