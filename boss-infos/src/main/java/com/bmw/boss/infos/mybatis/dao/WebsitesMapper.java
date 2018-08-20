package com.bmw.boss.infos.mybatis.dao;

import com.bmw.boss.infos.entity.Websites;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebsitesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Websites record);

    int insertSelective(Websites record);

    Websites selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Websites record);

    int updateByPrimaryKey(Websites record);

    List<Websites> queryList();
}