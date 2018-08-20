package com.bmw.boss.infos.app.controller;

import com.bmw.boss.infos.app.ehcache.MapKey;
import com.bmw.boss.infos.app.exception.BusinessException;
import com.bmw.boss.infos.app.pojo.json.ResponseDataItemPojo;
import com.bmw.boss.infos.app.service.IWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController implements Serializable {

  private static final long serialVersionUID = 2720583148330698344L;

  private static Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

  @Autowired
  private IWeatherService weatherService;


  @RequestMapping("/getInfos")
  protected ModelAndView getWeatherInfo(HttpServletRequest request,
                               @RequestParam(value="lat", required=false) String lat,
                               @RequestParam(value="lon", required=false) String lon)
      throws ServletException, IOException {
    String cityName = URLDecoder.decode(request.getParameter("cityName"), "UTF-8");
    ModelAndView modelAndView = new ModelAndView("weather/getWeather");
    modelAndView.addObject("cityName",cityName);
    try {
        String cacheKey = MapKey.getKeyString(lat, lon, "cn");
        //get Current Weather
        List<ResponseDataItemPojo> dataList = weatherService.getCurrentWeather(lat, lon,"cn",cacheKey);
        modelAndView.addObject("dataList",dataList);
      } catch (BusinessException e) {
        switch (e.getErrorCode()) {
          case "202":
          case "203":
          case "210":
          case "211":
          case "301":
          case "302":
          case "303":
          case "304":
            LOGGER.error("Invalid Lat, Lon. Error Code: " + e.getErrorCode());
            break;
          default:
            LOGGER.error("Invalid Request. Error Code: " + e.getErrorCode());
            break;
        }
      } catch (Exception e){
          LOGGER.error("Exception Error", e);
      }
    return modelAndView;
  }
  
  @RequestMapping("/getMap")
  protected ModelAndView doGetMap(){
	  ModelAndView modelAndView = new ModelAndView("weather/queryMap");
	  return modelAndView;
  }
}
