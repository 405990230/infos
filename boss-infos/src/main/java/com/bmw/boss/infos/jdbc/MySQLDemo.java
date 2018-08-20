package com.bmw.boss.infos.jdbc;

import com.bmw.boss.infos.jdbc.env.ServerEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by qxr4383 on 2018/3/14.
 */
public class MySQLDemo {

    private static Logger logger = LoggerFactory.getLogger(MySQLDemo.class);
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = ServerEnvironment.getDriver();
    static final String DB_URL = ServerEnvironment.getUrl();

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = ServerEnvironment.getUsername();
    static final String PASS = ServerEnvironment.getPassword();
    public static void main(String[] args) {
        //创建数据库链接
        Connection connection = null;
        //预编译preparedStatement
        PreparedStatement preparedStatement = null;
        //结果集
        ResultSet resultSet = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            logger.info(" 实例化Statement对象...");
            String sql = "SELECT id, name, url FROM websites";

            preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            preparedStatement.close();
            connection.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(preparedStatement!=null) preparedStatement.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(connection!=null) connection.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
