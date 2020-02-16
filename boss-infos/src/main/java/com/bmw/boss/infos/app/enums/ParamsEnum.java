package com.bmw.boss.infos.app.enums;

/**
 * Created by qxr4383 on 2018/5/7.
 */
public enum ParamsEnum {
    SEARCH("SEARCH"),
    HSIDX("HSIDX"),
    SECTORS("SECTORS"),
    SECTORSDETAIL("SECTORSDETAIL"),
    MYFAV("MYFAV");

    private String value;

    ParamsEnum(String value){
        this.value = value;
    }

    public String getParamsEnum() {
        return value;
    }
}
