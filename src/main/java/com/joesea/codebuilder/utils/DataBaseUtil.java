package com.joesea.codebuilder.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2020/1/17</p>
 * <p>@description : </p>
 */
public class DataBaseUtil {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseUtil.class);

    /**
     * 获取数据库连接
     * @return Connection 对象
     */
    public static Connection getConnection(String driverClass, String url, String user, String password){
        Connection conn = null;
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            closeConnection(conn);
            logger.error("数据库连接异常", e);
        }
        return conn;
    }
    /**
     * 关闭数据库连接
     * @param conn Connection 对象
     */
    public static void closeConnection(Connection conn){
        if(null != conn){
            try {
                conn.close(); //关闭数据库连接
            } catch (Exception e) {
                logger.error("关闭数据库连接异常", e);
            }
        }
    }
}
