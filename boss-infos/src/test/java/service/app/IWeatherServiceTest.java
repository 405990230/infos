package service.app;

import base.BaseTest;
import com.bmw.boss.infos.app.pojo.json.ResponseDataItemPojo;
import com.bmw.boss.infos.app.service.IWeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qxr4383 on 2018/5/9.
 */
public class IWeatherServiceTest extends BaseTest {
    private static Logger logger = LoggerFactory.getLogger(IWeatherServiceTest.class);
    @Autowired
    private IWeatherService weatherService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGetCategoriesData(){
        try {
            List<ResponseDataItemPojo> list = weatherService.getCurrentWeather("41.893001","123.463199","zh","cache");
            logger.info(objectMapper.writeValueAsString(list));
        }catch ( Exception e){
            logger.info("{}",e);
        }
    }

}
