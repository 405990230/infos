package com.bmw.boss.infos.app.pojo.json;

import java.io.Serializable;

public class ResponsePeriodPojo implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String forecast;
  private ResponseTemperaturePojo temperature;
  private ResponseWindPojo wind;
  private String eforecast;

  public String getForecast() {
    return forecast;
  }

  public void setForecast(String forecast) {
    this.forecast = forecast;
  }

  public ResponseTemperaturePojo getTemperature() {
    return temperature;
  }

  public void setTemperature(ResponseTemperaturePojo temperature) {
    this.temperature = temperature;
  }

  public ResponseWindPojo getWind() {
    return wind;
  }

  public void setWind(ResponseWindPojo wind) {
    this.wind = wind;
  }

public String getEforecast() {
	return eforecast;
}

public void setEforecast(String eforecast) {
	this.eforecast = eforecast;
} 
  
}
