package service.app;

import base.BaseTest;
import com.bmw.boss.common.model.HttpClentResponseModel;
import com.bmw.boss.common.service.IHttpClientService;
import com.bmw.boss.infos.app.pojo.json.ResponseDataItemPojo;
import com.bmw.boss.infos.app.service.IWeatherService;
import com.bmw.boss.infos.app.util.CMSApiHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import utils.MD5Util;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.List;

/**
 * Created by qxr4383 on 2018/5/9.
 */
public class IWeatherServiceTest extends BaseTest {
    private static Logger logger = LoggerFactory.getLogger(IWeatherServiceTest.class);
    @Autowired
    private IWeatherService weatherService;
    @Autowired
    private IHttpClientService httpClientService;

    @Autowired
    ObjectMapper objectMapper;

    @Value("#{configProperties['weather.api.gateway']}")
    private String apiGateway;

    @Value("#{configProperties['weather.api.user']}")
    private String apiUser;

    @Value("#{configProperties['weather.api.key']}")
    private String apiKey;

    @Test
    public void testGetCategoriesData(){
        try {
            List<ResponseDataItemPojo> list = weatherService.getCurrentWeather("41.893001","123.463199","zh","cache");
            logger.info(objectMapper.writeValueAsString(list));
        }catch ( Exception e){
            logger.info("{}",e);
        }
    }

    /**
     　　* @Description: 获取四维天气接口原始返回数据
     　　* @param ${tags}
     　　* @throws
     　　* @date 2018/8/8 15:18
     　　* @return void
     　　*/
    @Test
    public void testSiWeiResponseData() throws Exception {
        String lon=120.172512+"";
        String lat=30.25767+"";
        String i18n = "zh";
        String queryString = "cata=json&lat=" + lat + "&lng=" + lon + "&language=" + i18n + "&userid=" + apiUser;
        String key = CMSApiHelper.generateHASHKey(queryString, apiUser);
        String requestUrl = apiGateway+ "forecastByLocation?" + queryString + "&key=" + key;
        HttpClentResponseModel responseModel = httpClientService.httpGet(requestUrl, 5000);
        System.out.println("=================>"+objectMapper.writeValueAsString(responseModel));
    }


}
