package service.app;

import base.BaseTest;
import com.bmw.boss.infos.app.pojo.json.ResponseCategoriesDataPojo;
import com.bmw.boss.infos.app.pojo.json.ResponseNewsListDataPojo;
import com.bmw.boss.infos.app.service.INewsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qxr4383 on 2018/5/9.
 */
public class INewsServiceTest extends BaseTest {
    private static Logger logger = LoggerFactory.getLogger(INewsServiceTest.class);
    @Autowired
    private INewsService newsService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testGetCategoriesData(){
        try {
            List<ResponseCategoriesDataPojo> list = newsService.getNewsCategories();
            logger.info(objectMapper.writeValueAsString(list));
        }catch ( Exception e){
            logger.info("{}",e);
        }
    }

    @Test
    public void testGetNewsList(){
        try {
            List<ResponseNewsListDataPojo> list = newsService.getNewsList("topNewsOverview","zh");
            logger.info(objectMapper.writeValueAsString(list));
        }catch ( Exception e){
            logger.info("{}",e);
        }
    }

    @Test
    public void getNewsListByOid(){
        try {
            List<ResponseNewsListDataPojo> list = newsService.getNewsListByOid("topNewsOverview","zh");
            logger.info(objectMapper.writeValueAsString(list));
        }catch ( Exception e){
            logger.info("{}",e);
        }
    }

    @Test
    public void getNewsListByOid2(){
        try {
            List<ResponseNewsListDataPojo> list = newsService.getNewsListByOid2("topNewsOverview","zh");
            logger.info(objectMapper.writeValueAsString(list));
        }catch ( Exception e){
            logger.info("{}",e);
        }
    }
}
