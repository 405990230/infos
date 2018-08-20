package com.bmw.boss.infos.app.service.impl;

import com.android.dazhihui.DzhAgent;
import com.android.dazhihui.domain.SectorInfos;
import com.android.dazhihui.domain.StockInfos;
import com.bmw.boss.infos.app.service.IDzhApi;
import org.springframework.stereotype.Service;

@Service("dzhApi")
public class DzhApi implements IDzhApi {
  @Override
  public StockInfos getStockInfo(String code) {
    return DzhAgent.getSockInfo(code);
  }

  @Override
  public StockInfos getHuShenIndexList(int sortId, int turn, int beginId,
                                       int number) {
    return DzhAgent.getHuShenIndexList(sortId, turn, beginId, number);
  }

  @Override
  public SectorInfos getSectorList(int sortId, int turn, int beginId, int number) {
    return DzhAgent.getSectorList(sortId, turn, beginId, number);
  }

  @Override
  public StockInfos getSectorDetail(int cfgId, int sortId, int turn,
                                    int beginId, int number) {
    return DzhAgent.getSectorDetail(cfgId, sortId, turn, beginId, number);
  }

  @Override
  public StockInfos getMineStockList(String[] codes) {
    return DzhAgent.getMineStockList(codes);
  }

}
