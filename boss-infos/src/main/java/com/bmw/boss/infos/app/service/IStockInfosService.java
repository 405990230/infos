package com.bmw.boss.infos.app.service;

import com.bmw.boss.infos.app.pojo.json.SectorPojo;
import com.bmw.boss.infos.app.pojo.json.StocksPojo;

import java.util.List;

/**
 * Created by qxr4383 on 2017/8/21.
 */
public interface IStockInfosService {

    /**
     * get stock infos for typeOfCar by Search param
     * @param param
     * @return
     */
    List<StocksPojo> getStockInfoBySearch(String param);

    /**
     * get stock hsidx infos
     * @return
     */
    List<StocksPojo> getHsidx();

    /**
     * get stock Sectors infos
     * @return
     */
    List<SectorPojo> getSectors();

    /**
     * get stock SectorsDetail infos by param
     * @param param
     * @return
     */
    List<StocksPojo> getSectorsDetail(String param);

    /**
     * get stock MyFav infos by param
     * @param param
     * @return
     */
    List<StocksPojo> getMyFav(String param);

}
