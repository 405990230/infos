package com.bmw.boss.infos.app.pojo.api;

import java.io.Serializable;
import java.util.List;


public class DataApiPojo implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private List<ForcastApiPojo> forecast;
  private IndicesApiPojo index;
  private CurrentWeatherPojo currentWeather;
  private WeatherAirPojo weatherAir;
  

public List<ForcastApiPojo> getForecast() {
    return forecast;
  }

  public void setForecast(List<ForcastApiPojo> forecast) {
    this.forecast = forecast;
  }

  public IndicesApiPojo getIndex() {
    return index;
  }

  public void setIndex(IndicesApiPojo index) {
    this.index = index;
  }
  public CurrentWeatherPojo getCurrentWeather() {
	return currentWeather;
  }

  public void setCurrentWeather(CurrentWeatherPojo currentWeather) {
	this.currentWeather = currentWeather;
  }

public WeatherAirPojo getWeatherAir() {
	return weatherAir;
}

public void setWeatherAir(WeatherAirPojo weatherAir) {
	this.weatherAir = weatherAir;
}
}
