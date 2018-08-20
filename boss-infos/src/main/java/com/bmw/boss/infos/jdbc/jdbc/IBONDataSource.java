package com.bmw.boss.infos.jdbc.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by qxr4383 on 2018/3/16.
 */
public interface IBONDataSource {
    Connection getConnection() throws SQLException;
}
