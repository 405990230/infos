package com.bmw.boss.infos.mybatis.dao;

import com.bmw.boss.infos.entity.CarRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRequestMapper {
    int deleteByPrimaryKey(Long id);

    int deleteAll();

    int insert(CarRequest record);

    int insertSelective(CarRequest record);

    CarRequest selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CarRequest record);

    int updateByPrimaryKey(CarRequest record);

    int batchInsertCarRequestList(List<CarRequest> list);
}