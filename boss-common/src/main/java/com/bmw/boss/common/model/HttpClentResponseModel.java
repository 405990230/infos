package com.bmw.boss.common.model;

/**
 * Description: 所有第三方接口返回的实体类
 * Copyright: Copyright (c) 2017
 * Company: 上海四键信息技术有限公司
 *
 * @author: wuw
 * @created: 2017/1/4
 */
public class HttpClentResponseModel {

    private int statusCode;
    private String content;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
