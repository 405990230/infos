package com.bmw.boss.common.service.impl;

import com.bmw.boss.common.model.HttpClentResponseModel;
import com.bmw.boss.common.service.IHttpClientService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("httpClientService")
public class HttpClientServiceImpl implements IHttpClientService {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientServiceImpl.class);

    private static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");

    private static final String CONTENT_TYPE_JSON = "application/reponse";

    @Autowired
    private CloseableHttpClient closeableHttpClient;
    @Autowired
    private RequestConfig.Builder requestConfigBuilder;

    @Override
    public HttpClentResponseModel httpPostByJson(String url, String jsonValue) {
        logger.info("=====================>Http post url:"+ url+",jsonValue:"+jsonValue);
        HttpClentResponseModel HttpClentResponseModel = new HttpClentResponseModel();
        String httpEntityStr = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(RequestConfig.DEFAULT);
            httpPost.setHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON);
            StringEntity stringEntity = this.getStringEntity(jsonValue);
            httpPost.setEntity(stringEntity);
            // post请求
            HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
            // getEntity()
            HttpEntity httpEntity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            logger.info("=====================>Http status code:" + httpResponse.getStatusLine().getStatusCode());
            HttpClentResponseModel.setStatusCode(httpResponse.getStatusLine().getStatusCode());
            if (httpEntity != null) {
                // 打印响应内容
                httpEntityStr = EntityUtils.toString(httpEntity, CHARSET_UTF8);
                logger.info("=====================>API返回值为:"+ httpEntityStr);
            }
        } catch (Exception e) {
            logger.error("httpPost error", e);
        }
        if(null != httpEntityStr){
            HttpClentResponseModel.setContent(httpEntityStr);
            logger.info("=====================>API返回值为:"+ httpEntityStr);
        }
        return HttpClentResponseModel;
    }

    @Override
    public HttpClentResponseModel httpPost(String url, Map<String, String> paramMap) {
        logger.info("=====================>Http post url:"+ url);
        HttpClentResponseModel HttpClentResponseModel = new HttpClentResponseModel();
        String httpEntityStr = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(RequestConfig.DEFAULT);
            UrlEncodedFormEntity entity = this.getUrlEncodedFormEntity(paramMap);
            httpPost.setEntity(entity);
            // post请求
            HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
            // getEntity()
            HttpEntity httpEntity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            logger.info("=====================>Http status code:" + httpResponse.getStatusLine().getStatusCode());
            HttpClentResponseModel.setStatusCode(httpResponse.getStatusLine().getStatusCode());
            if (httpEntity != null) {
                // 打印响应内容
                httpEntityStr = EntityUtils.toString(httpEntity, CHARSET_UTF8);
                logger.info("=====================>API返回值为:"+ httpEntityStr);
            }
        } catch (Exception e) {
            logger.error("httpPost error", e);
        }
        if(null != httpEntityStr){
            HttpClentResponseModel.setContent(httpEntityStr);
        }
        return HttpClentResponseModel;
    }

    @Override
    public HttpClentResponseModel httpPost(String url) {
        return this.httpPost(url,null);
    }

    @Override
    public HttpClentResponseModel httpGet(String url,int Millisecond) throws Exception{
        logger.info("=====================>Http get url:"+ url);
        HttpClentResponseModel HttpClentResponseModel = new HttpClentResponseModel();
        String httpEntityStr = null;
        requestConfigBuilder.setConnectTimeout(Millisecond);
        requestConfigBuilder.setSocketTimeout(Millisecond);
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpget);
        //int statusCode = httpResponse.getStatusLine().getStatusCode();
        logger.info("=====================>Http status code:" + httpResponse.getStatusLine().getStatusCode());
        HttpClentResponseModel.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            httpEntityStr = EntityUtils.toString(entity, CHARSET_UTF8);
            logger.info("=====================>API返回值为:"+ httpEntityStr);
        }
        if(null != httpEntityStr){
            HttpClentResponseModel.setContent(httpEntityStr);
        }
        return HttpClentResponseModel;
    }

    public StringEntity getStringEntity(String jsonValue){
        StringEntity stringEntity = new StringEntity(jsonValue,CHARSET_UTF8);
        return stringEntity;
    }

    public UrlEncodedFormEntity getUrlEncodedFormEntity(Map<String, String> paramMap){
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if (null != paramMap) {
            Iterator<Map.Entry<String, String>> iter = paramMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next();
                formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                logger.info("=====================>paramName=" + entry.getKey() + ",paramValue=" + entry.getValue());
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, CHARSET_UTF8);
        return entity;
    }

}
