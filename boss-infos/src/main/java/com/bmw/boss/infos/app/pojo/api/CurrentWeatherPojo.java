package com.bmw.boss.infos.app.pojo.api;

import com.bmw.boss.infos.app.pojo.json.ResponseTemperaturePojo;
import com.bmw.boss.infos.app.pojo.json.ResponseWindPojo;

import java.io.Serializable;

public class CurrentWeatherPojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String time;
	private String weather;
	private String eweather;
	private String temperature;
	private String windDirection;
	private String windPower;
	private String humidity;
	private String currentTime;
	private String sunRiseTime;
	private String sunSetTime;
	private ResponseTemperaturePojo realTimeTemperature;
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getWeather() {
		return weather;
	}
	
	public void setWeather(String weather) {
		this.weather = weather;
	}
	
	public String getEweather() {
		return eweather;
	}

	public void setEweather(String eweather) {
		this.eweather = eweather;
	}

	public String getTemperature() {
		return temperature;
	}
	
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	public String getWindDirection() {
		return ResponseWindPojo.wrapWindDirection(windDirection);
	}
	
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	
	public String getWindPower() {
		return windPower;
	}
	
	public void setWindPower(String windPower) {
		this.windPower = windPower;
	}
	
	public String getHumidity() {
		return humidity;
	}
	
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	
	public String getSunRiseTime() {
		return sunRiseTime;
	}
	
	public void setSunRiseTime(String sunRiseTime) {
		this.sunRiseTime = sunRiseTime;
	}
	
	public String getSunSetTime() {
		return sunSetTime;
	}
	
	public void setSunSetTime(String sunSetTime) {
		this.sunSetTime = sunSetTime;
	}
	
	public String getCurrentTime() {
		return currentTime;
	}
	
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public ResponseTemperaturePojo getRealTimeTemperature() {
		return realTimeTemperature;
	}

	public void setRealTimeTemperature(ResponseTemperaturePojo realTimeTemperature) {
		this.realTimeTemperature = realTimeTemperature;
	}
        
}
