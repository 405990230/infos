package com.bmw.boss.infos.jdbc.jdbc.impl;

import com.bmw.boss.infos.jdbc.jdbc.IBONDataSource;
import com.bmw.boss.infos.jdbc.jdbc.IJDBCUtil;
import com.bmw.boss.infos.entity.CarRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by qxr4383 on 2018/3/16.
 */

@Repository("jDBCUtil")
public class JDBCUtil implements IJDBCUtil {

    private static Logger logger = LoggerFactory.getLogger(JDBCUtil.class);

    @Autowired
    IBONDataSource bONDataSource;

    public int insert(CarRequest car) throws SQLException {
        String preparedSQLString =
                "INSERT INTO car_request (vin, app_name, pu, system , page) VALUES(?, ?, ?, ?,?)";

        Connection connection = bONDataSource.getConnection();
        connection.setAutoCommit(false); //设置不会自动提交
        PreparedStatement statement = connection.prepareStatement(preparedSQLString);
        statement.setString(1, car.getVin());
        statement.setString(2, car.getAppName());
        statement.setString(3, car.getPu());
        statement.setString(4, car.getSystem());
        statement.setString(5, car.getPage());

        logger.info("I'm going to execute [" + statement.toString() + "]");
        int result = statement.executeUpdate();

        if (statement != null) {
            statement.close();
        }

        connection.commit(); //提交事务
        if (connection != null) {
            connection.close();
        }

        return result;
    }


    public int insertList(List<CarRequest> list) throws SQLException {
        String preparedSQLString =
                "INSERT INTO car_request (vin, app_name, pu, system , page) VALUES(?, ?, ?, ?,?)";

        Connection connection = bONDataSource.getConnection();
        connection.setAutoCommit(false); //设置不会自动提交
        PreparedStatement statement = connection.prepareStatement(preparedSQLString);

        for(CarRequest car : list){
            //statement.setInt(1, car.getId());
            statement.setString(1, car.getVin());
            statement.setString(2, car.getAppName());
            statement.setString(3, car.getPu());
            statement.setString(4, car.getSystem());
            statement.setString(5, car.getPage());
            statement.addBatch();
        }

        int [] counts = statement.executeBatch();
        logger.info("I'm going to execute [" + statement.toString() + "]");
        int result = statement.executeUpdate();

        if (statement != null) {
            statement.close();
        }

        connection.commit(); //提交事务
        if (connection != null) {
            connection.close();
        }

        return result;
    }

    @Override
    public int delete() throws SQLException {
        String preparedSQLString = "DELETE FROM car_request";

        Connection connection = bONDataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(preparedSQLString);
        int result = statement.executeUpdate();
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
        return result;
    }
}
