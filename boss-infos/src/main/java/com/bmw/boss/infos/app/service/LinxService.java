package com.bmw.boss.infos.app.service;

import com.bmw.boss.infos.app.pojo.api.NewsListAPIPojo;
import com.bmw.boss.infos.app.pojo.api.WeatherApiPojo;

import java.io.IOException;

public interface LinxService {

    WeatherApiPojo getWeather(String lat, String lon, String i18n);

    /**
     * URL that generates third party requests
     * @param channelId
     * @return
     * @throws IOException
     */
    NewsListAPIPojo getNewsList(String channelId) throws IOException;
}
