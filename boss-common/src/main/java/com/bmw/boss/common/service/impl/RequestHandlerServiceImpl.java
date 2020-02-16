package com.bmw.boss.common.service.impl;


import com.bmw.boss.common.service.IRequestHandlerService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Service("requestHandlerService")
public class RequestHandlerServiceImpl implements IRequestHandlerService {
	@Override
	public InputStream openRequest(String requestURLString,int Millisecond)
			throws IOException {
		URL requestURL = new URL(requestURLString);
		URLConnection conn = requestURL.openConnection();
		conn.setReadTimeout(Millisecond);
		conn.setConnectTimeout(Millisecond);
		return conn.getInputStream();
	}

	@Override
	public InputStream openRequest2(String requestURLString,int Millisecond,
			String key,String value)throws IOException {
		URL requestURL = new URL(requestURLString);
		URLConnection conn = requestURL.openConnection();
		conn.setReadTimeout(Millisecond);
		conn.setConnectTimeout(Millisecond);
		conn.setRequestProperty(key,value);
		return conn.getInputStream();
	}
}
