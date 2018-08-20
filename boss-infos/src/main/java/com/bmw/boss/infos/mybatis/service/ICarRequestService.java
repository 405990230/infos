package com.bmw.boss.infos.mybatis.service;

import com.bmw.boss.infos.entity.CarRequest;

import java.util.List;

/**
 * Created by qxr4383 on 2018/3/19.
 */
public interface ICarRequestService {
    int insertSelective(CarRequest record);

    /**
     * 批量新增
     * @param listData
     * @return
     */
    int batchInsertCarRequestList(List<CarRequest> listData);

    /**
     * 删除所以
     * @return
     */
    int deleteAll();
}
