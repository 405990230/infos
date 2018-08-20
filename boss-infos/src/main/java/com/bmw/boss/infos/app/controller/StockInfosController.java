package com.bmw.boss.infos.app.controller;


import com.bmw.boss.infos.app.pojo.json.KChartPojo;
import com.bmw.boss.infos.app.pojo.json.SectorPojo;
import com.bmw.boss.infos.app.pojo.json.StocksPojo;
import com.bmw.boss.infos.app.service.IStockInfosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/dzh")
public class StockInfosController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger LOGGER = LoggerFactory.getLogger(StockInfosController.class);

  @Autowired
  private IStockInfosService stockInfosService;


  @RequestMapping("/getStock")
  public ModelAndView getStockInfos(@RequestParam(required = false) String type,KChartPojo kChart,
                                    HttpServletRequest request){
    LOGGER.info("StockInfos GET Method called.");
    LOGGER.info("type: "+ type);
    if(type!=null&&!"".equals(type)){
      type = type.toUpperCase();
    }
    String param = request.getParameter("param");
    ModelAndView modelAndView = new ModelAndView("dzhStock/stockInfo");
    List<StocksPojo> stocksPojoslist = null;
    List<SectorPojo> sectorPojosList = null;
    switch (type) {
      case "SEARCH":
        stocksPojoslist = stockInfosService.getStockInfoBySearch(param);
        modelAndView.addObject("searchValue",param);
        break;
      case "HSIDX":
        stocksPojoslist = stockInfosService.getHsidx();
        break;
      case "SECTORS":
        sectorPojosList = stockInfosService.getSectors();
        modelAndView.addObject("sectorPojosList",sectorPojosList);
        break;
      case "SECTORSDETAIL":
        stocksPojoslist = stockInfosService.getSectorsDetail(param);
        break;
      case "MYFAV":
        stocksPojoslist = stockInfosService.getMyFav(param);
        break;
      case "PHOTODETAIL":
        modelAndView.addObject("kChart",kChart);
        break;
      default:
        break;
    }
    modelAndView.addObject("stocksPojoslist",stocksPojoslist);
    return modelAndView;
  }
}
