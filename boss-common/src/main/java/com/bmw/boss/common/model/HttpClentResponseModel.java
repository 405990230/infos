package com.bmw.boss.common.model;

import lombok.Data;

/**
 * Description: 所有第三方接口返回的实体类
 * Copyright: Copyright (c) 2017
 * Company: 上海四键信息技术有限公司
 *
 * @author: wuw
 * @created: 2017/1/4
 */
@Data
public class HttpClentResponseModel {

    private int statusCode;
    private String content;

}
