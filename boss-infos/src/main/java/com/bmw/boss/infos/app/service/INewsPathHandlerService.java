package com.bmw.boss.infos.app.service;

import com.bmw.boss.infos.app.pojo.api.NewsListAPIPojo;

import java.io.IOException;


public interface INewsPathHandlerService {

	/**
	 * URL that generates third party requests
	 * @param channelId
	 * @return
	 * @throws IOException
	 */
	NewsListAPIPojo getNewsList(String channelId) throws IOException;

}
