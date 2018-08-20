package com.bmw.boss.infos.app.service;

import com.bmw.boss.infos.app.pojo.api.WeatherApiPojo;

public interface IWeatherPathHandlerService {

    WeatherApiPojo getWeather(String lat, String lon, String i18n);

}
