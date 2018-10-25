package com.bmw.boss.infos.app.pojo.api;

import com.bmw.boss.infos.app.pojo.json.ResponseTemperaturePojo;
import com.bmw.boss.infos.app.pojo.json.ResponseWindPojo;
import lombok.Data;

import java.io.Serializable;

@Data
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
        
}
