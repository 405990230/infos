package com.bmw.boss.infos.app.pojo.json;


import com.bmw.boss.infos.app.pojo.api.DzhPojo;

public class SectorPojo extends DzhPojo {
  private String vol = null;
  private String cfgId = null;

  public String getVol() {
    return vol;
  }

  public void setVol(String vol) {
    this.vol = vol;
  }

  public String getCfgId() {
    return cfgId;
  }

  public void setCfgId(String cfgId) {
    this.cfgId = cfgId;
  }
}
