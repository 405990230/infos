package com.bmw.boss.infos.mongodb.service;

/**
 * Created by qxr4383 on 2018/3/20.
 */
public interface ReadStatisticByNIOService {
    void readTxtByNIOofWeather(String filePath);

    void readTxtByNIOofWeatherMongo(String filePath);
}
