package com.bmw.boss.infos.mongodb.service;

import com.bmw.boss.infos.entity.data.CarRequestData;

import java.util.List;

/**
 * Created by qxr4383 on 2018/3/26.
 */
public interface ICarRequestMongoService {
    void insertList(List<CarRequestData> list);

    List<CarRequestData>  queryByCondition(String field, String value);

    List<CarRequestData> queryAll();

    Integer delete(CarRequestData data);

    Integer deleteByCondition(String itme, String condition);

}
