package com.bmw.boss.infos.app.controller;

import com.bmw.boss.infos.app.pojo.json.ResponseCategoriesDataPojo;
import com.bmw.boss.infos.app.pojo.json.ResponseNewsListDataPojo;
import com.bmw.boss.infos.app.service.INewsService;
import com.bmw.boss.common.api.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController implements Serializable {

  private static final long serialVersionUID = 2720583148330698344L;

  private static Logger LOGGER = LoggerFactory.getLogger(NewsController.class);
  private static Logger STAT_LOGGER =  LoggerFactory.getLogger("MONITORLOGTOCSV");

  @Autowired
  private INewsService newsService;


  @RequestMapping("/getCategory")
  protected JsonResult<List<ResponseCategoriesDataPojo>> getCategory()
      throws ServletException, IOException {
      JsonResult<List<ResponseCategoriesDataPojo>>  json= new JsonResult<>();
      LOGGER.info("GET Method called.");
      try {
          List<ResponseCategoriesDataPojo> dataList = newsService.getNewsCategories();
          json.setData(dataList);
          json.setCode("0");
          json.setMsg("缓存成功");
      } catch (Exception e) {
          json.setCode("1");
          json.setMsg("缓存失败");
          e.printStackTrace();
      }

      return json;
  }
  
  @RequestMapping("/getNewsList")
  @ResponseBody
  protected JsonResult<List<ResponseNewsListDataPojo>> getNewsList(@RequestParam("category") String category,
                                                                   @RequestParam("language")String language){
      JsonResult<List<ResponseNewsListDataPojo>>  json= new JsonResult<>();
      LOGGER.info("GET Method called.");
      try {
          List<ResponseNewsListDataPojo> dataList = newsService.getNewsListByOid(category,language);
          json.setData(dataList);
          json.setCode("0");
          json.setMsg("缓存成功");
      } catch (Exception e) {
          json.setCode("1");
          json.setMsg("缓存失败");
          e.printStackTrace();
      }
	  return json;
  }

}
