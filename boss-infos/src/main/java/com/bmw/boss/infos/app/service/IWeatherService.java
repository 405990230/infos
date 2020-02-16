package com.bmw.boss.infos.app.service;

import com.bmw.boss.infos.app.exception.BusinessException;
import com.bmw.boss.infos.app.pojo.json.ResponseDataItemPojo;

import java.util.List;


public interface IWeatherService {
	/**
	 * 处理天气对象及缓存天气信息
	 * @param lat
	 * @param lon
	 * @param language
	 * @param cacheKey
	 * @return
	 * @throws BusinessException
	 */
	List<ResponseDataItemPojo> getCurrentWeather
    (String lat, String lon, String language, String cacheKey) throws BusinessException;
}
