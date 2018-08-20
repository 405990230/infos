package com.bmw.boss.infos.jdbc.jdbc;

import com.bmw.boss.infos.entity.CarRequest;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by qxr4383 on 2018/3/16.
 */
public interface IJDBCUtil {
    int insert(CarRequest car) throws SQLException;

    int insertList(List<CarRequest> list) throws SQLException;

    int delete() throws SQLException;
}
