package com.bmw.boss.infos.mybatis.service;

import com.bmw.boss.infos.entity.Websites;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * app的请求服务service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * qxr4383    1.0  2018年03月16日 Created
 *
 * </pre>
 * @since 1.
 */
public interface IWebsitesService {

    /**
     * 根据ID查询列表
     *
     * @param id
     * @return
     */
    Websites selectByPrimaryKey(Integer id) throws SQLException;

    /**
     * 查询列表
     * @return
     * @throws SQLException
     */
    List<Websites> querList() throws SQLException;
}
