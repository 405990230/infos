package com.bmw.boss.infos.app.service.impl;


import com.bmw.boss.infos.app.pojo.api.NewsListAPIPojo;
import com.bmw.boss.infos.app.service.INewsPathHandlerService;
import com.bmw.boss.infos.app.util.CMSApiHelper;
import com.bmw.boss.common.service.IRequestHandlerService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;



@Service("newsPathHandlerService")
public class NewsPathHandlerServiceImpl implements INewsPathHandlerService {

	private static Logger logger = LoggerFactory.getLogger(NewsPathHandlerServiceImpl.class);

	@Value("#{configProperties['news.api.user']}")
	private String apiUser;
	@Value("#{configProperties['news.api.key']}")
	private String apiKey;
	@Value("#{configProperties['news.api.gateway']}")
	private String apiGateway;

	@Autowired
	private IRequestHandlerService requestHandlerService;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public NewsListAPIPojo getNewsList(String channelId) throws IOException {
		logger.info("I'm going to request Channel [" + channelId + "]");

		String queryString = "cata=json&channelId=" + channelId + "&userid=" + apiUser;
		String key = "";
		try {
			key = CMSApiHelper.generateHASHKey(queryString,apiKey);
		} catch (Exception e) {
			logger.error("Error in get key", e);
		}
		String requestUrl = apiGateway+ "newslist?" + queryString + "&key=" + key;
		logger.info("I'm going to send request: " + requestUrl);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		NewsListAPIPojo rtnPojo = null;
		rtnPojo = objectMapper.readValue(requestHandlerService.openRequest(requestUrl,5000), NewsListAPIPojo.class);
		return rtnPojo;
	}
}
