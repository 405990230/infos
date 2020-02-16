package com.bmw.boss.infos.mybatis.service.impl;

import com.bmw.boss.infos.entity.Websites;
import com.bmw.boss.infos.mybatis.dao.WebsitesMapper;
import com.bmw.boss.infos.mybatis.service.IWebsitesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * app的请求service服务类
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * qxr4383    1.0  2018年03月16日 Created
 *
 * </pre>
 * @since 1.
 */
@Service("websitesService")
public class WebsitesServiceImpl implements IWebsitesService {
    @Autowired
    WebsitesMapper websitesMapper;

    private Logger logger = LoggerFactory.getLogger(WebsitesServiceImpl.class);


    /**
     * 根据ID查询列表
     *
     * @param id
     * @return
     */
    public Websites selectByPrimaryKey(Integer id) throws SQLException{
        logger.info("开始查询id"+id);
        return websitesMapper.selectByPrimaryKey(id);
    }


    /**
     * 查询列表
     * @return
     * @throws SQLException
     */
    public List<Websites> querList() throws SQLException{
        return websitesMapper.queryList();
    }
}
