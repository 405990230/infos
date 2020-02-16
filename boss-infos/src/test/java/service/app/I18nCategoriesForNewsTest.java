package service.app;

import base.BaseTest;
import com.bmw.boss.infos.app.pojo.json.I18nCategoriesItemPojo;
import com.bmw.boss.infos.app.pojo.json.I18nCategoriesPojo;
import com.bmw.boss.infos.app.pojo.json.ResponseCategoriesDataPojo;
import com.bmw.boss.infos.app.util.I18nCategoriesForNews;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: tongshan.han
 * @Date: 5/9/2018 15:09
 * @Description: I18nCategoriesForNews测试
 */
public class I18nCategoriesForNewsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(I18nCategoriesForNewsTest.class);

    //@Autowired
    //private ObjectMapper objectMapper;

    ObjectMapper objectMapper =new ObjectMapper();
    @Autowired
    private I18nCategoriesForNews i18nCategoriesForNews;

    @Test
    public void fileUtils() throws Exception {
        Map<String, Map<String, String>> i18nCategoriesMap;
        Map<String, List<String>> i18nCategoriesList;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        i18nCategoriesMap = new HashMap<String, Map<String, String>>();
        i18nCategoriesList = new HashMap<String, List<String>>();

        File catDir = new File(I18nCategoriesForNews.class.getClassLoader().getResource("cat").getPath());
        if (catDir.isDirectory()) {
            for (File i18nFile : catDir.listFiles()) {
                String fileName = i18nFile.getName();
                logger.info("Cat file name: " + fileName);
                int idxOfExt = fileName.indexOf("json");
                if (idxOfExt != -1) {
                    fileName = fileName.substring(0, idxOfExt - 1);
                    I18nCategoriesPojo i18nCategoriesPojo = new I18nCategoriesPojo();
                    i18nCategoriesPojo = objectMapper.readValue(i18nFile, I18nCategoriesPojo.class);
                    Map<String, String> oidMap = new HashMap<String, String>();
                    List<String> oidList = new ArrayList<String>();
                    for (I18nCategoriesItemPojo itemPojo : i18nCategoriesPojo.getItems()) {
                        logger.info(fileName + "," + itemPojo.getChannel() + "," + itemPojo.getOid());
                        oidMap.put(itemPojo.getChannel(), itemPojo.getOid());
                        oidList.add(itemPojo.getChannel());
                    }
                    i18nCategoriesMap.put(fileName, oidMap);
                    i18nCategoriesList.put(fileName, oidList);
                }
            }
        } else {
            logger.warn("Cannot find [cat] directory in resources.");
        }
    }


    @Test
    public void testGet() throws Exception {
        List<ResponseCategoriesDataPojo> list = i18nCategoriesForNews.getChannelList();
        if (list==null || list.isEmpty()){
            logger.warn("======================>获取不到数据。。。。");
        }
        logger.info("=========================>返回值为:"+objectMapper.writeValueAsString(list));
    }

}

