package com.bmw.boss.infos.app.pojo.json;

import com.bmw.boss.infos.app.pojo.api.CurrentWeatherPojo;

import java.io.Serializable;
import java.util.List;


public class ResponseDataItemPojo implements Serializable{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String oid;
  private String timestamp;
  private ResponsePeriodPojo morning;
  private ResponsePeriodPojo evening;
  private ResponsePeriodPojo night;
  private String indexDescription;
  private List<ResponseIndexPojo> indices;
  private CurrentWeatherPojo currentWeather;
  private String week;
  public String getOid() {
    return oid;
  }

  public void setOid(String oid) {
    this.oid = oid;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public ResponsePeriodPojo getMorning() {
    return morning;
  }

  public void setMorning(ResponsePeriodPojo morning) {
    this.morning = morning;
  }

  public ResponsePeriodPojo getEvening() {
    return evening;
  }

  public void setEvening(ResponsePeriodPojo evening) {
    this.evening = evening;
  }

  public ResponsePeriodPojo getNight() {
    return night;
  }

  public void setNight(ResponsePeriodPojo night) {
    this.night = night;
  }

  public String getIndexDescription() {
    return indexDescription;
  }

  public void setIndexDescription(String indexDescription) {
    this.indexDescription = indexDescription;
  }

  public List<ResponseIndexPojo> getIndices() {
    return indices;
  }

  public void setIndices(List<ResponseIndexPojo> indices) {
    this.indices = indices;
  }

  public CurrentWeatherPojo getCurrentWeather() {
	  return currentWeather;
  }

  public void setCurrentWeather(CurrentWeatherPojo currentWeather) {
	  this.currentWeather = currentWeather;
  }

	public String getWeek() {
		return week;
	}
	
	public void setWeek(String week) {
		this.week = week;
	}
  
}
