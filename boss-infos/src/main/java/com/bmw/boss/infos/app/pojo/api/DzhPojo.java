package com.bmw.boss.infos.app.pojo.api;

public abstract class DzhPojo {
  private String name = null;
  private String code = null;
  private String latePrice = null;
  private String priceRise = null;
  private String priceChange = null;
  private String lastPrice = null;
  private String openPrice = null;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getLatePrice() {
    return latePrice;
  }

  public void setLatePrice(String latePrice) {
    this.latePrice = latePrice;
  }

  public String getPriceRise() {
    return priceRise;
  }

  public void setPriceRise(String priceRise) {
    this.priceRise = priceRise;
  }

  public String getPriceChange() {
    return priceChange;
  }

  public void setPriceChange(String priceChange) {
    this.priceChange = priceChange;
  }

  public String getLastPrice() {
    return lastPrice;
  }

  public void setLastPrice(String lastPrice) {
    this.lastPrice = lastPrice;
  }

  public String getOpenPrice() {
    return openPrice;
  }

  public void setOpenPrice(String openPrice) {
    this.openPrice = openPrice;
  }

}
