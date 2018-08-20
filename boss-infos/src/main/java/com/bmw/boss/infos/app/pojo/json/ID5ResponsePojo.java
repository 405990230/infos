package com.bmw.boss.infos.app.pojo.json;


import java.util.List;

public class ID5ResponsePojo extends ResponseReqPojo {
  private ResponseReqPojo req = null;
  private List<StocksPojo> stock = null;
  private List<SectorPojo> sectors = null;

  public ResponseReqPojo getReq() {
    return req;
  }

  public void setReq(ResponseReqPojo reqPojo) {
    this.req = reqPojo;
  }

  public List<StocksPojo> getStock() {
    return stock;
  }

  public void setStock(List<StocksPojo> stock) {
    this.stock = stock;
  }

  public List<SectorPojo> getSectors() {
    return sectors;
  }

  public void setSectors(List<SectorPojo> sectors) {
    this.sectors = sectors;
  }

}
