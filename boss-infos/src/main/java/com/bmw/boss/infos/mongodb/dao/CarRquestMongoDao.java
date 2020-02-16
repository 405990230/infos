package com.bmw.boss.infos.mongodb.dao;

import com.bmw.boss.infos.app.exception.BusinessException;
import com.bmw.boss.infos.entity.data.CarRequestData;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qxr4383 on 2018/3/26.
 */
@Repository
public class CarRquestMongoDao extends MongoGenDao<CarRequestData> {

    /**
     * 根据条件查询集合
     *
     * @author http://www.chisalsoft.com
     */
    public List<CarRequestData> queryList(Query query)
    {
        return this.mongoTemplate.find(query, CarRequestData.class);
    }

    /**
     * 分页查询 对应mongodb操作中的 db.book.find().skip(10).limit(10);
     *
     */
    public List<CarRequestData> queryPage(CarRequestData CarRequestData, Integer start, Integer size)
    {
        Query query = new Query();
        // 此处可以增加分页查询条件Criteria.然后query.addCriteria(criteria);
        return this.getPage(query, (start - 1) * size, size);
    }

    /**
     * 查询满足分页的记录总数
     */
    public Long queryPageCount(CarRequestData CarRequestData)
    {
        Query query = new Query();
        // 此处可以增加分页查询条件Criteria.然后query.addCriteria(criteria);
        return this.getPageCount(query);
    }

    /**
     * 更新操作
     *
     * @author http://www.chisalsoft.com
     */
    public void updateFirst(CarRequestData CarRequestData) throws Exception
    {
        Update update = new Update();
        if (null == CarRequestData.getVin() || "".equals(CarRequestData.getVin().trim()))
        {
            // 如果主键为空,则不进行修改
            throw new Exception("Update data Id is Null");
        }
        if (CarRequestData.getVin() != null)
        {
            update.set("vin", CarRequestData.getVin());
        }
        this.updateFirst(Query.query(Criteria.where("vin").is(CarRequestData.getVin())), update);
    }

    /**
     * 更新库中所有数据
     *
     * @author http://www.chisalsoft.com
     */
    public void updateMulti(CarRequestData CarRequestData) throws Exception
    {
        Update update = new Update();
        if (null == CarRequestData.getVin() || "".equals(CarRequestData.getVin().trim()))
        {
            // 如果主键为空,则不进行修改
            throw new BusinessException("Update data Id is Null");
        }
        if (CarRequestData.getVin() != null)
        {
            update.set("vin", CarRequestData.getVin());
        }
        this.updateMulti(Query.query(Criteria.where("vin").is(CarRequestData.getVin())), update);
    }

    /**
     * 实现钩子方法,返回反射的类型
     *
     */
    @Override
    protected Class<CarRequestData> getEntityClass()
    {
        return CarRequestData.class;
    }

}
