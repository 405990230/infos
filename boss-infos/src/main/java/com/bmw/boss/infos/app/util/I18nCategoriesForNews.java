package com.bmw.boss.infos.app.util;

import com.bmw.boss.infos.app.pojo.json.I18nCategoriesItemPojo;
import com.bmw.boss.infos.app.pojo.json.I18nCategoriesPojo;
import com.bmw.boss.infos.app.pojo.json.ResponseCategoriesDataPojo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class I18nCategoriesForNews {

    private static Logger LOGGER = LoggerFactory.getLogger(I18nCategoriesForNews.class);

    private static Map<String, Map<String, String>> i18nCategoriesMap;
    private static Map<String, List<String>> i18nCategoriesList;
    private static Map<String, List<String>> i18nNameList;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void initI18n(){
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        i18nCategoriesMap = new HashMap<>();
        i18nCategoriesList = new HashMap<>();
        i18nNameList = new HashMap<>();
        File catDir = new File(I18nCategoriesForNews.class.getClassLoader().getResource("json/news").getPath());
        if (catDir.isDirectory()) {
            LOGGER.info("Find <cat> folder");
            for (File i18nFile : catDir.listFiles()) {
                String fileName = i18nFile.getName();
                LOGGER.info("Cat file name: " + fileName);
                int idxOfExt = fileName.indexOf("json");
                if (idxOfExt != -1) {
                    fileName = fileName.substring(0, idxOfExt - 1);

                    I18nCategoriesPojo i18nCategoriesPojo = new I18nCategoriesPojo();
                    try {
                        i18nCategoriesPojo = objectMapper.readValue(i18nFile, I18nCategoriesPojo.class);
                    } catch (JsonParseException e) {
                        LOGGER.error("Parse Json failed!", e);
                    } catch (JsonMappingException e) {
                        LOGGER.error("Json format doesn't match pojo", e);
                    } catch (IOException e) {
                        LOGGER.error("Read file failed", e);
                    }
                    Map<String, String> oidMap = new HashMap<>();
                    List<String> oidList = new ArrayList<>();
                    List<String> nameList = new ArrayList<>();
                    for (I18nCategoriesItemPojo itemPojo : i18nCategoriesPojo.getItems()) {
                        LOGGER.info(fileName + "," + itemPojo.getChannel() + "," + itemPojo.getOid()+","+itemPojo.getName());
                        oidMap.put(itemPojo.getChannel(), itemPojo.getOid());
                        oidList.add(itemPojo.getChannel());
                        nameList.add(itemPojo.getName());
                    }
                    i18nCategoriesMap.put(fileName, oidMap);
                    i18nCategoriesList.put(fileName, oidList);
                    i18nNameList.put(fileName, nameList);
                }
            }
        } else {
            LOGGER.warn("Cannot find [cat] directory in resources.");
        }
    }

    public List<ResponseCategoriesDataPojo>getChannelList() {
        List<ResponseCategoriesDataPojo> rtn = new ArrayList<ResponseCategoriesDataPojo>();
        for (String i18n : i18nCategoriesList.keySet()) {
            ResponseCategoriesDataPojo responseCategoriesDataPojo = new ResponseCategoriesDataPojo();
            responseCategoriesDataPojo.setOid(i18n);
            responseCategoriesDataPojo.setCategories(i18nCategoriesList.get(i18n));
            responseCategoriesDataPojo.setNames(i18nNameList.get(i18n));
            rtn.add(responseCategoriesDataPojo);
        }
        return rtn;
    }

    public String getChannelId(String i18n, String channel) {
        return i18nCategoriesMap.get(i18n).get(channel);
    }

}
