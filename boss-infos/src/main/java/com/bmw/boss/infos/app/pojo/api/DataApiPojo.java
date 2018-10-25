package com.bmw.boss.infos.app.pojo.api;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DataApiPojo implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private List<ForcastApiPojo> forecast;
    private IndicesApiPojo index;
    private CurrentWeatherPojo currentWeather;
    private WeatherAirPojo weatherAir;

}
