package com.bmw.boss.infos.app.exception;

/**
 * Created by qxr4383 on 2018/3/7.
 */
public class BusinessException extends Exception{
    private static final long serialVersionUID = 1L;
    private String errorCode;

    public BusinessException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

