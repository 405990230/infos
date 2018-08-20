package com.bmw.boss.infos.app.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bmw.boss.infos.app.enums.NewsImageSizeEnum;
import com.bmw.boss.infos.app.exception.BusinessException;
import com.bmw.boss.infos.app.pojo.api.NewsItemPojo;
import com.bmw.boss.infos.app.pojo.api.NewsListAPIPojo;
import com.bmw.boss.infos.app.pojo.api.PictureItemPojo;
import com.bmw.boss.infos.app.pojo.json.*;
import com.bmw.boss.infos.app.service.INewsPathHandlerService;
import com.bmw.boss.infos.app.service.INewsService;
import com.bmw.boss.infos.app.util.I18nCategoriesForNews;
import com.bmw.boss.common.service.jedis.RedisClientTemplate;
import com.bmw.boss.common.util.jedis.RedisUtilByCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("newsService")
public class NewsServiceImp implements INewsService {

	private static Logger logger = LoggerFactory.getLogger(NewsServiceImp.class);
	@Value("#{configProperties['redis.CachingTime']}")
	private static Integer CachingTime;


	@Autowired
	private INewsPathHandlerService newsPathHandlerService;

	@Autowired
	private I18nCategoriesForNews i18nCategoriesForNews;

	@Autowired
	RedisClientTemplate redisClientTemplate;
	public List<ResponseCategoriesDataPojo> getNewsCategories() throws Exception{
		return i18nCategoriesForNews.getChannelList();
	}

	/**
	 * 使用redis，Spring管理jedis连接
	 * @param channelId
	 * @param i18n
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ResponseNewsListDataPojo> getNewsListByOid(String channelId, String i18n) throws Exception{
		List<ResponseNewsListDataPojo> rtnList = null;
		String categoryOid = i18nCategoriesForNews.getChannelId(i18n, channelId);
		Object object= JSONArray.parseArray(redisClientTemplate.get(categoryOid), ResponseNewsListDataPojo.class);
		rtnList = (List<ResponseNewsListDataPojo>)object;
		if (rtnList == null) {
			rtnList = getNewsList(channelId,i18n);
			redisClientTemplate.set(categoryOid, JSON.toJSONString(rtnList));
		} else {
			logger.info("get redis key :  " + categoryOid + " success");
		}
		return rtnList;
	}

	/**
	 * 使用redis，java代码实现jedis连接
	 * @param channelId
	 * @param i18n
	 * @return
	 * @throws Exception
	 */
	public List<ResponseNewsListDataPojo> getNewsListByOid2(String channelId, String i18n) throws Exception{
		List<ResponseNewsListDataPojo> rtnList = null;
		String categoryOid = i18nCategoriesForNews.getChannelId(i18n, channelId);
		Object object= JSONArray.parseArray(RedisUtilByCode.getData(categoryOid), ResponseNewsListDataPojo.class);
		rtnList = (List<ResponseNewsListDataPojo>)object;
		if (rtnList == null) {
			rtnList = getNewsList(channelId,i18n);
			RedisUtilByCode.setData(categoryOid, JSON.toJSONString(rtnList), CachingTime);
		} else {
			logger.info("get redis key :  " + categoryOid + " success");
		}
		return rtnList;
	}


	public List<ResponseNewsListDataPojo> getNewsList(String channelId, String i18n)
			throws IOException, BusinessException {
		String categoryOid = i18nCategoriesForNews.getChannelId(i18n, channelId);
		if (categoryOid == null) {
			IOException e = new IOException("bmw Cannot match categories [" + channelId + "]");
			throw e;
		}
		NewsListAPIPojo apiPojo = newsPathHandlerService.getNewsList(categoryOid);
		List<ResponseNewsListDataPojo> rtnList = new ArrayList<>();
		if ("0".equals(apiPojo.getStatus())) {
			ResponseNewsListDataPojo dataPojo = parseNewsListResponse(apiPojo);
			dataPojo.setOid(channelId);
			rtnList.add(dataPojo);
		} else {
			BusinessException e = new BusinessException(apiPojo.getStatus());
			throw e;
		}
		return rtnList;
	}


	/**
	 * Data conversion
	 * @param apiPojo
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ResponseNewsListDataPojo parseNewsListResponse(NewsListAPIPojo apiPojo) throws UnsupportedEncodingException {
		ResponseNewsListDataPojo dataPojo = new ResponseNewsListDataPojo();
		List<ResponseNewsItemPojo> itemsPojo  = new ArrayList<>();
		for (NewsItemPojo apiItemPojo : apiPojo.getItem()) {
			ResponseNewsItemPojo itemPojo = new ResponseNewsItemPojo();
			itemPojo.setTitle(apiItemPojo.getTitle());
			List<String> paragrah = new ArrayList<>();
			paragrah.add(apiItemPojo.getContent() + "\n");
			itemPojo.setParagraphs(paragrah);

			SimpleDateFormat srcDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat tarDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat tarTimeFormatter = new SimpleDateFormat("HH:mm:ss");
			String srcDateStr = apiItemPojo.getPubDate();
			String tarDateStr = "2000-01-01T00:00:00+0800";
			try {
				Date date = srcDateFormatter.parse(srcDateStr);
				tarDateStr = tarDateFormatter.format(date) + "T" + tarTimeFormatter.format(date) + "+0800";
			} catch (ParseException e) {
				logger.warn("bmw Parse date: " + srcDateStr + "error", e);
			}

			itemPojo.setTimestamp(tarDateStr);

			PictureItemPojo pictureItemPojo = apiItemPojo.getPictures().get(0);
			List<ResponseImagesListPojo> imageListPojo = new ArrayList<>();

			//String thumbImageUrlParam = URLEncoder.encode(apiItemPojo.getSmallpicurl(),"UTF-8");
			//String fullImageUrlParam = URLEncoder.encode(pictureItemPojo.getUrl(),"UTF-8");

			String thumbImageUrlParam = apiItemPojo.getSmallpicurl();
			String fullImageUrlParam = pictureItemPojo.getUrl();
			boolean isThumbEmpty = thumbImageUrlParam == null || thumbImageUrlParam.length() < 5;
			boolean isFullEmpty = fullImageUrlParam == null || fullImageUrlParam.length() < 5;
			if (isThumbEmpty && isFullEmpty) {
				itemPojo.setImages(null);
			} else {
				if (isThumbEmpty) {
					thumbImageUrlParam = fullImageUrlParam;
				} else if (isFullEmpty) {
					fullImageUrlParam = thumbImageUrlParam;
				}
				ResponseImagesItemPojo thumbImageItem = new ResponseImagesItemPojo();
				ResponseImagesItemPojo fullImageItem = new ResponseImagesItemPojo();
				//thumbImageItem.setLink(ServerEnvironment.getImageServelt() + "?maxHeight=135&maxWidth=135&url=" + thumbImageUrlParam);
				thumbImageItem.setLink(thumbImageUrlParam);
				thumbImageItem.setX(NewsImageSizeEnum.THUMB_IMAGE_X.getNewsImageSizeEnum());
				thumbImageItem.setY(NewsImageSizeEnum.THUMB_IMAGE_Y.getNewsImageSizeEnum());
				//fullImageItem.setLink(ServerEnvironment.getImageServelt() + "?maxHeight=225&maxWidth=324&url=" + fullImageUrlParam);
				fullImageItem.setLink(fullImageUrlParam);
				fullImageItem.setX(NewsImageSizeEnum.FULL_IMAGE_X.getNewsImageSizeEnum());
				fullImageItem.setY(NewsImageSizeEnum.FULL_IMAGE_Y.getNewsImageSizeEnum());
				ResponseImagesListPojo imagesList = new ResponseImagesListPojo();
				imagesList.setFull(fullImageItem);
				imagesList.setThumb(thumbImageItem);
				imageListPojo.add(imagesList);

				itemPojo.setImages(imageListPojo);
			}

			itemsPojo.add(itemPojo);
		}
		dataPojo.setItems(itemsPojo);
		return dataPojo;
	}
}
