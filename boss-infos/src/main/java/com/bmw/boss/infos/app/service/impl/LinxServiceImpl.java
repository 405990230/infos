package com.bmw.boss.infos.app.service.impl;

import com.bmw.boss.common.service.IRequestHandlerService;
import com.bmw.boss.infos.app.pojo.api.NewsListAPIPojo;
import com.bmw.boss.infos.app.pojo.api.WeatherApiPojo;
import com.bmw.boss.infos.app.service.LinxService;
import com.bmw.boss.infos.app.util.CMSApiHelper;
import com.bmw.boss.common.model.HttpClentResponseModel;
import com.bmw.boss.common.service.IHttpClientService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * @Auther: hants
 * @Date: 2018-05-10 18:14
 * @Description: 第三方API接口调用服务实现
 */
@Service("linxService")
public class LinxServiceImpl implements LinxService {

    private static final Logger logger = LoggerFactory.getLogger(LinxServiceImpl.class);

    @Value("#{configProperties['weather.api.gateway']}")
    private String weatherApiGateway;

    @Value("#{configProperties['weather.api.user']}")
    private String apiUser;

    @Value("#{configProperties['weather.api.key']}")
    private String apiKey;

    @Value("#{configProperties['news.api.gateway']}")
    private String newsApiGateway;

    @Autowired
    private IRequestHandlerService requestHandlerService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IHttpClientService httpClientService;

    @Override
    public WeatherApiPojo getWeather(String lat, String lon, String i18n) {
        logger.info("I'm going to request weather at [" + lat + "," + lon + "] in " + i18n);
        WeatherApiPojo weatherApiPojo = new WeatherApiPojo();
        try {
            String queryString = "cata=json&lat=" + lat + "&lng=" + lon + "&language=" + i18n + "&userid=" + apiUser;
            String key = CMSApiHelper.generateHASHKey(queryString, apiKey);
            String requestUrl = weatherApiGateway + "forecastByLocation?" + queryString + "&key=" + key;
            logger.info("I'm going to send request: " + requestUrl);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            HttpClentResponseModel responseModel = httpClientService.httpGet(requestUrl, 5000);
            if (responseModel!=null && responseModel.getStatusCode()==200){
                weatherApiPojo = objectMapper.readValue(responseModel.getContent(), WeatherApiPojo.class);
            } else {
                weatherApiPojo.setCode("-1");
            }
            //logger.info("===================>获取API接口的返回值为:"+objectMapper.writeValueAsString(weatherApiPojo));
            return weatherApiPojo;
        } catch (Exception e) {
            logger.error("获取第三方天气API接口getWeather error {} ", e);
            weatherApiPojo.setCode("-1");
        }
        return weatherApiPojo;
    }


    @Override
    public NewsListAPIPojo getNewsList(String channelId) throws IOException {
        NewsListAPIPojo rtnPojo = new NewsListAPIPojo();
        try {
            logger.info("I'm going to request Channel [" + channelId + "]");
            String requestUrl = newsApiGateway+"channelId="+channelId+"&p=1&pageSize=20&refer=100000448";
            logger.info("I'm going to send request: " + requestUrl);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            rtnPojo = objectMapper.readValue(requestHandlerService.openRequest(requestUrl,5000), NewsListAPIPojo.class);
        } catch (Exception e) {
            logger.error("Error in get key", e);
        }
        return rtnPojo;
    }

}

