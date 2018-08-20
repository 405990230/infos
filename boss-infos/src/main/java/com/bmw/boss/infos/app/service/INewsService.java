package com.bmw.boss.infos.app.service;

import com.bmw.boss.infos.app.exception.BusinessException;
import com.bmw.boss.infos.app.pojo.json.ResponseCategoriesDataPojo;
import com.bmw.boss.infos.app.pojo.json.ResponseNewsListDataPojo;

import java.io.IOException;
import java.util.List;

/**
 * Created by qxr4383 on 2018/1/17.
 */
public interface INewsService {

    /**
     * get news Categories and add redies cache
     * spring bean
     * @return
     */
    List<ResponseNewsListDataPojo> getNewsListByOid(String channelId, String i18n) throws Exception;

    /**
     * get news Categories and add redies cache
     * java code
     * @return
     */
    List<ResponseNewsListDataPojo> getNewsListByOid2(String channelId, String i18n) throws Exception;


    /**
     * get news Categories
     * @return
     */
    List<ResponseCategoriesDataPojo> getNewsCategories() throws Exception;

    /**
     * Get a list of news messages by channelId and languag
     * @param channelId
     * @param i18n
     * @return
     * @throws IOException
     * @throws BusinessException
     */
    List<ResponseNewsListDataPojo> getNewsList(String channelId, String i18n)
            throws IOException, BusinessException;

}
