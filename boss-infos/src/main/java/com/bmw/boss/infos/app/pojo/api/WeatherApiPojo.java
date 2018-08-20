package com.bmw.boss.infos.app.pojo.api;

import java.util.List;

public class WeatherApiPojo {
  private String code;
  private List<DataApiPojo> data;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

public List<DataApiPojo> getData() {
    return data;
  }

  public void setData(List<DataApiPojo> data) {
    this.data = data;
  }
}
