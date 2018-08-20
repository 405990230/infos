package com.bmw.boss.infos.app.pojo.json;


import com.bmw.boss.infos.app.pojo.api.DzhPojo;

public class StocksPojo extends DzhPojo {
  private String volRatio = null;
  private String handOff = null;
  private String vol = null;
  private KChartPojo kChart = null;

  public String getVolRatio() {
    return volRatio;
  }

  public void setVolRatio(String volRatio) {
    this.volRatio = volRatio;
  }

  public String getHandOff() {
    return handOff;
  }

  public void setHandOff(String handOff) {
    this.handOff = handOff;
  }

  public String getVol() {
    return vol;
  }

  public void setVol(String vol) {
    this.vol = vol;
  }

  public KChartPojo getkChart() {
    return kChart;
  }

  public void setkChart(KChartPojo kChart) {
    this.kChart = kChart;
  }
}
