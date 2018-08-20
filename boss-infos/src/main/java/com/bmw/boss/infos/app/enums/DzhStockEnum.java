package com.bmw.boss.infos.app.enums;

public enum DzhStockEnum {
    NO_SORT(0),
    RISE_SORT(1),
    LATEST_SORT(4),
    REDUCE(0),
    GROW(1),
    ZERO(0),
    SECTOR_GROW_NUMBER(5),
    SECTOR_REDUCE_NUMBER(5),
    SECTORDETAIL_NUMBER(10),
    HSIDX_REQUEST_NUMBER(10);


    private int value;

    private DzhStockEnum(int value) {
        this.value = value;
    }

    public int getDzhStockEnum() {
        return this.value;
    }
}