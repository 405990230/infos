package com.bmw.boss.common.service;


import java.io.IOException;
import java.io.InputStream;


public interface IRequestHandlerService {
	InputStream openRequest(String requestURLString, int Millisecond) throws IOException;
	InputStream openRequest2(String requestURLString, int Millisecond, String key, String value) throws IOException;
}
