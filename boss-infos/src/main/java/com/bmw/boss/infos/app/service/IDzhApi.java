package com.bmw.boss.infos.app.service;

import com.android.dazhihui.domain.SectorInfos;
import com.android.dazhihui.domain.StockInfos;

public interface IDzhApi {
  StockInfos getStockInfo(String code);

  StockInfos getHuShenIndexList(int sortId, int turn, int beginId,
                                       int number);

  SectorInfos getSectorList(int sortId, int turn, int beginId, int number);

  StockInfos getSectorDetail(int cfgId, int sortId, int turn,
                                    int beginId, int number);

  StockInfos getMineStockList(String[] codes);

}