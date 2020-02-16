package com.bmw.boss.infos.app.service.impl;


import com.android.dazhihui.domain.SectorInfo;
import com.android.dazhihui.domain.SectorInfos;
import com.android.dazhihui.domain.StockInfo;
import com.android.dazhihui.domain.StockInfos;
import com.bmw.boss.infos.app.enums.DzhStockEnum;
import com.bmw.boss.infos.app.pojo.json.KChartPojo;
import com.bmw.boss.infos.app.pojo.json.SectorPojo;
import com.bmw.boss.infos.app.pojo.json.StocksPojo;
import com.bmw.boss.infos.app.service.IDzhApi;
import com.bmw.boss.infos.app.service.IStockInfosService;
import com.bmw.boss.infos.app.util.GreatWisdomKChartURLGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("stockInfosService")
public class StockInfosServiceImp implements IStockInfosService {

  private static Logger logger = Logger.getLogger(StockInfosServiceImp.class);

  @Autowired
  private IDzhApi dzhApi;


  public List<StocksPojo> getStockInfoBySearch(String param) {
    List<StocksPojo> rtn = new ArrayList<StocksPojo>();
    try {
      StockInfos info = dzhApi.getStockInfo(param);
      List<StockInfo> stockInfos = info.getStocks();
      StockInfo stockInfo;
      StocksPojo pojo;
      String stockCode = null;
      int size = stockInfos.size();
      for (int i = 0; i < size; i++) {
        stockInfo = null;
        stockInfo = stockInfos.get(i);
        stockCode = stockInfo.getStockCode();
        // Only keeps stock with prefix "SH", "SZ"
        if (stockCode.matches("\\bSH\\d+\\b")
            || stockCode.matches("\\bSZ\\d+\\b")) {
          pojo = setUpStockInfoPojo(stockInfo);
          rtn.add(pojo);
        }
      }

    } catch (Exception e) {
      logger.error("DzhStockEnum Search Error!", e);
    }
    return rtn;
  }

  /**
   * 获取沪深指数
   * @return
   */
  @Override
  public List<StocksPojo> getHsidx() {
    List<StocksPojo> rtn = new ArrayList<StocksPojo>();
    StocksPojo pojo;
    try {
      StockInfos info = dzhApi.getHuShenIndexList(DzhStockEnum.NO_SORT.getDzhStockEnum(),
              DzhStockEnum.REDUCE.getDzhStockEnum(), DzhStockEnum.ZERO.getDzhStockEnum(),
              DzhStockEnum.HSIDX_REQUEST_NUMBER.getDzhStockEnum());
      List<StockInfo> stockInfos = info.getStocks();
      StockInfo stockInfo = null;
      String stockCode = null;
      int size = stockInfos.size();
      for (int i = 0; i < size; i++) {
        stockInfo = stockInfos.get(i);
        stockCode = stockInfo.getStockCode();
        // Only keeps stock with prefix "SH", "SZ"
        if (stockCode.matches("\\bSH\\d+\\b")
            || stockCode.matches("\\bSZ\\d+\\b")) {
          pojo = setUpStockInfoPojo(stockInfo);
          rtn.add(pojo);
        } 
      }
    } catch (Exception e) {
      logger.error("DzhStockEnum getHsIdx Error!", e);
    }
    return rtn;
  }

  /**
   * 获取板块涨停信息
   * @return
   */
  @Override
  public List<SectorPojo> getSectors() {
    ArrayList<SectorPojo> rtn = new ArrayList<SectorPojo>();
    try {
      SectorInfos infos = dzhApi.getSectorList(DzhStockEnum.RISE_SORT.getDzhStockEnum(),
              DzhStockEnum.GROW.getDzhStockEnum(), DzhStockEnum.ZERO.getDzhStockEnum(),
              DzhStockEnum.SECTOR_GROW_NUMBER.getDzhStockEnum());
      ArrayList<SectorInfo> infoList = infos.getSectors();

      for (int i = 0; i < infoList.size(); i++) {
        SectorInfo sector = infoList.get(i);
        rtn.add(setUpSectorInfoPojo(sector));
      }
      infos = null;
      infos = dzhApi.getSectorList(DzhStockEnum.RISE_SORT.getDzhStockEnum(),
              DzhStockEnum.REDUCE.getDzhStockEnum(), DzhStockEnum.ZERO.getDzhStockEnum(),
              DzhStockEnum.SECTOR_REDUCE_NUMBER.getDzhStockEnum());
      infoList = infos.getSectors();
      for (int i = 0; i < infoList.size(); i++) {
        SectorInfo sector = infoList.get(i);
        rtn.add(setUpSectorInfoPojo(sector));
      }
    } catch (Exception e) {
      logger.error("DzhStockEnum getSectors Error!", e);
    }
    return rtn;
  }


  /**
   * 获取板块涨停详情
   * @param param
   * @return
   */
  public List<StocksPojo> getSectorsDetail(String param) {
    List<StocksPojo> rtnList = new ArrayList<StocksPojo>();
    try {
      int cfgId = Integer.parseInt(param);
      StockInfos stockInfos = dzhApi.getSectorDetail(
              cfgId, DzhStockEnum.RISE_SORT.getDzhStockEnum(), (DzhStockEnum.REDUCE.getDzhStockEnum()),
              DzhStockEnum.ZERO.getDzhStockEnum(), DzhStockEnum.SECTORDETAIL_NUMBER.getDzhStockEnum());
      StockInfo stockInfo = null;
      StocksPojo pojo = null;
      List<StockInfo> stockList = stockInfos.getStocks();
      for (int j = 0; j < stockList.size(); j++) {
        stockInfo = stockList.get(j);
        pojo = setUpStockInfoPojo(stockInfo);
        rtnList.add(pojo);
      }
    } catch (Exception e) {
      logger.error("DzhStockEnum getSectorsDetail Error!", e);
    }
    return rtnList;
  }

  public List<StocksPojo> getMyFav(String param) {
    List<StocksPojo> rtnList = new ArrayList<StocksPojo>();
    try {
      String[] codes = param.split(",");
      StockInfos info = dzhApi.getMineStockList(codes);
      List<StockInfo> stockInfos = info.getStocks();

      StocksPojo pojo = null;
      StockInfo stockInfo = null;
      int size = stockInfos.size();
      for (int i = 0; i < size; i++) {
        stockInfo = stockInfos.get(i);
        pojo = setUpStockInfoPojo(stockInfo);
        rtnList.add(pojo);
      }

    } catch (Exception e) {
      logger.error("DzhStockEnum getMyFav Error", e);
    }
    return rtnList;
  }



  private StocksPojo setUpStockInfoPojo(StockInfo stockInfo) {
    String stockCode = null;
    StocksPojo pojo = new StocksPojo();
    stockCode = stockInfo.getStockCode();
    pojo.setName(stockInfo.getStockName());
    pojo.setCode(stockCode);
    pojo.setLatePrice(stockInfo.getLatePrice());
    String priceRise = stockInfo.getPriceRise();
    if (-1 == priceRise.indexOf("-")) {
      priceRise = "+" + priceRise;
    }
    pojo.setPriceRise(priceRise);
    String priceChange = stockInfo.getPriceChange();
    if (-1 == priceChange.indexOf("-")) {
      priceChange = "+" + priceChange;
    }
    pojo.setPriceChange(priceChange);
    pojo.setLastPrice(stockInfo.getLastPrice());
    pojo.setOpenPrice(stockInfo.getOpenPrice());
    pojo.setVolRatio(parseEmptyValue(stockInfo.getVolRatio()));
    pojo.setHandOff(parseEmptyValue(stockInfo.getHandoff()));
    pojo.setVol(stockInfo.getVol());
    pojo.setkChart(getKchartUrl(stockCode));
    return pojo;
  }

  private String parseEmptyValue(String value) {
    String rtn = value == null ? "" : value;
    return rtn;
  }

  private KChartPojo getKchartUrl(String stockCode) {
    KChartPojo rtn = new KChartPojo();
    rtn.setHour(getUrlEncode(stockCode, "hour"));
    rtn.setDay(getUrlEncode(stockCode, "day"));
    rtn.setWeek(getUrlEncode(stockCode, "week"));
    rtn.setMonth(getUrlEncode(stockCode, "month"));
    return rtn;
  }

  /**
   * 获取股票走势图
   * @param stockCode
   * @param kChartType
   * @return
   */
  private String getUrlEncode(String stockCode, String kChartType) {
    //StringBuffer rtn = new StringBuffer();
    //String imgAddr = ServerEnvironment.getImageServelt() + "?url=";
    String imgAddr = GreatWisdomKChartURLGenerator
            .generateKChartURL(stockCode, kChartType);;
//    rtn.append(imgAddr);
//    String econdeString = "";
//    try {
//      econdeString = URLEncoder.encode(GreatWisdomKChartURLGenerator
//              .generateKChartURL(stockCode, kChartType), "utf-8");
//    } catch (UnsupportedEncodingException e) {
//      LOGGER.error("URLEncoding Error!", e);
//    }
//    rtn.append(econdeString);
    return imgAddr;
  }

  private SectorPojo setUpSectorInfoPojo(SectorInfo sector) {
    SectorPojo pojo = new SectorPojo();
    pojo.setName(sector.getStockName());
    pojo.setCode(sector.getStockCode());
    pojo.setLatePrice(sector.getLatePrice());
    String priceRise = sector.getPriceRise();
    if (-1 == priceRise.indexOf("-")) {
      priceRise = "+" + priceRise;
    }
    pojo.setPriceRise(priceRise);
    String priceChange = sector.getPriceChange();
    if (-1 == priceChange.indexOf("-")) {
      priceChange = "+" + priceChange;
    }
    pojo.setPriceChange(priceChange);
    pojo.setLastPrice(sector.getLastPrice());
    pojo.setOpenPrice(sector.getOpenPrice());
    pojo.setVol(parseEmptyValue(sector.getVol()));
    pojo.setCfgId(Integer.toString(sector.getCfgId()));
    return pojo;
  }

}
