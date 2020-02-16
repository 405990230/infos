package com.bmw.boss.infos.jdbc.jdbc.impl;

import com.bmw.boss.infos.jdbc.jdbc.IBONDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by qxr4383 on 2018/3/16.
 */
@Repository("bONDataSource")
public class BONDataSource implements IBONDataSource {

    private static Logger logger = LoggerFactory.getLogger(BONDataSource.class);

    private static BONDataSource instance;

    private static Properties config = new Properties();
    private ComboPooledDataSource cpds = null;

    private BONDataSource() {
        try{
            cpds = new ComboPooledDataSource();
            //装载配置文件
            config.load(BONDataSource.class.getClassLoader()
                    .getResourceAsStream("properties/c3p0.properties"));
            //声明C3P0数据源对象
            cpds = new ComboPooledDataSource();
            //设置数据库连接驱动
            cpds.setDriverClass(
                    config.getProperty("c3p0.driverClass"));
            //设置数据连接URL
            cpds.setJdbcUrl(
                    config.getProperty("c3p0.jdbcUrl"));
            //设置数据库连接用户账号
            cpds.setUser(
                    config.getProperty("c3p0.user"));
            //设置数据库连接用户账号的密码
            cpds.setPassword(
                    config.getProperty("c3p0.password"));

            //设置数据库连接池中的初始化连接对象数量
            cpds.setInitialPoolSize(Integer.valueOf(config.getProperty("c3p0.initialPoolSize")));
            //设置数据库连接池中的最小连接对象数量
            cpds.setMinPoolSize(Integer.valueOf(config.getProperty("c3p0.minPoolSize")));
            //设置数据库连接池中的最大连接对象数量
            cpds.setMaxPoolSize(Integer.valueOf(config.getProperty("c3p0.maxPoolSize")));
            //当连接不够，每次新增连接数量
            cpds.setAcquireIncrement(Integer.valueOf(config.getProperty("c3p0.acquireIncrement")));
        } catch (Exception e){
            e.printStackTrace();
            logger.error("read c3p0.properties exception",e);
        }

    }

    public static IBONDataSource getInstance() {
        if (instance == null) {
            synchronized (BONDataSource.class) {
                if (instance == null) {
                    instance = new BONDataSource();
                    logger.info("BONDataSource been allocated");
                }
            }
        }
        return instance;
    }

    public static void destroy() {
        if (instance != null) {
            synchronized (BONDataSource.class) {
                if (instance != null) {
                    instance.cpds.close();
                    instance.cpds = null;
                    instance = null;
                    logger.info("BONDataSource been destoryed");
                }
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        logger.info("Get Connection from Connection Pool");
        return this.cpds.getConnection();
    }
}
