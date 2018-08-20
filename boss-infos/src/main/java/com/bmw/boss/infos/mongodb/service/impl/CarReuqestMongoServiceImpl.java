package com.bmw.boss.infos.mongodb.service.impl;

import com.bmw.boss.infos.entity.data.CarRequestData;
import com.bmw.boss.infos.mongodb.dao.CarRquestMongoDao;
import com.bmw.boss.infos.mongodb.service.ICarRequestMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qxr4383 on 2018/3/26.
 */
@Service("carRequestMongoService")
public class CarReuqestMongoServiceImpl implements ICarRequestMongoService {
    @Autowired
    private CarRquestMongoDao carRquestMongoDao;

    public void insertList(List<CarRequestData> list){
        carRquestMongoDao.insertList(list);
    }


    public List<CarRequestData> queryByCondition(String field,String value){
        Query query = new Query();
        query.addCriteria(Criteria.where(field).is(value));
        List<CarRequestData> carRequestDataList = carRquestMongoDao.queryList(query);
        return carRequestDataList;
    }

    public List<CarRequestData> queryAll(){
        return carRquestMongoDao.queryAll();
    }


    public Integer delete(CarRequestData data){
        return carRquestMongoDao.delete(data);
    }

    public Integer deleteByCondition(String itme,String condition){
        Query query = Query.query(Criteria.where(itme).is(condition));
        return carRquestMongoDao.deleteByCondition(query);
    }
}
