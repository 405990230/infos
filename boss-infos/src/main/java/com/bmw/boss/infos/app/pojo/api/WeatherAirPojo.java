package com.bmw.boss.infos.app.pojo.api;

public class WeatherAirPojo {
  private String PM25;
  private String aqi;
  private String publicTime;
  private String desc;
  private String detail;

	public String getPM25() {
		return PM25;
	}
	
	public void setPM25(String pM25) {
		PM25 = pM25;
	}

	public String getAqi() {
		return aqi;
	}

	public void setAqi(String aqi) {
		this.aqi = aqi;
	}

	public String getPublicTime() {
		return publicTime;
	}

	public void setPublicTime(String publicTime) {
		this.publicTime = publicTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
