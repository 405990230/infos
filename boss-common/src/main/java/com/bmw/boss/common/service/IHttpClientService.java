package com.bmw.boss.common.service;

import com.bmw.boss.common.model.HttpClentResponseModel;

import java.util.Map;

public interface IHttpClientService {

    /**
     * Http Post方式，通过json格式的参数访问请求，参数放在body体中
     * @param url
     * @param jsonValue
     * @return
     */
    HttpClentResponseModel httpPostByJson(String url, String jsonValue);

    /**
     * 有post参数的http post请求，参数在from中
     * @param url
     * @param paramMap
     * @return
     */
    HttpClentResponseModel httpPost(String url, Map<String, String> paramMap);

    /**
     * 无post参数的http post请求
     * @param url
     * @return
     */
    HttpClentResponseModel httpPost(String url);

    /**
     * http get请求方式
     * @param url
     * @return
     */
    HttpClentResponseModel httpGet(String url, int Millisecond) throws Exception;

}
