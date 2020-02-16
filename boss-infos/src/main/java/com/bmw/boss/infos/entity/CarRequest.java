package com.bmw.boss.infos.entity;

import lombok.Data;

import java.util.Date;

@Data
public class CarRequest {
    private Long id;

    private String vin;

    private String appName;

    private String pu;

    private String system;

    private String page;

    private Date requestDate;

    private Date updateDate;

    private Date creatDate;

}