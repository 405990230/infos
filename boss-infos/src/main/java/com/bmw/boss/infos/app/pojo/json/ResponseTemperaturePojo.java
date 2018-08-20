package com.bmw.boss.infos.app.pojo.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ResponseTemperaturePojo implements Serializable{

  private static final long serialVersionUID = 1L;
  private int C;
  private int F;

  @JsonProperty("C")
  public int getC() {
    return C;
  }

  @JsonProperty("C")
  public void setC(int C) {
    this.C = C;
  }

  @JsonProperty("F")
  public int getF() {
    return F;
  }

  @JsonProperty("F")
  public void setF(int F) {
    this.F = F;
  }
}
