package com.bmw.boss.infos.mybatis.service.impl;

import com.bmw.boss.infos.entity.CarRequest;
import com.bmw.boss.infos.mybatis.dao.CarRequestMapper;
import com.bmw.boss.infos.mybatis.service.ICarRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qxr4383 on 2018/3/19.
 */
@Service("carRequestService")
public class CarRequestServiceImpl implements ICarRequestService{

    @Autowired
    CarRequestMapper carRequestMapper;

    public int insertSelective(CarRequest record){
        return carRequestMapper.insertSelective(record);
    }

    public int batchInsertCarRequestList(List<CarRequest> listData){
        return carRequestMapper.batchInsertCarRequestList(listData);
    }

    public int deleteAll(){
        return carRequestMapper.deleteAll();
    }
}

