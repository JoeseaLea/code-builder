package com.joesea.codebuilder.service;

import java.util.List;
import java.util.Map;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2020/1/30</p>
 * <p>@description : </p>
 */
public interface CodeBuilderService {
    /**
     * 获取指定数据库下的所有数据库信息
     * @param driverClass
     * @param url
     * @param userName
     * @param password
     * @return 所有数据库信息
     */
    List<Map<String, String>> getDatabasesInfo(String driverClass, String url, String userName, String password);
    /**
     * 获取指定数据库下的所有表信息
     * @param driverClass
     * @param url
     * @param userName
     * @param password
     * @param databaseName
     * @return 所有表信息
     */
    List<Map<String, String>> getTablesInfo(String driverClass, String url, String userName, String password, String databaseName);

    /**
     * 获取表结构信息
     * @param driverClass
     * @param url
     * @param userName
     * @param password
     * @param databaseName
     * @param tableName
     * @return 表结构信息
     */
    List<Map<String, String>> getTableStruct(String driverClass, String url, String userName, String password, String databaseName, String tableName);

    /**
     * 获取表主键信息
     * @param driverClass
     * @param url
     * @param userName
     * @param password
     * @param databaseName
     * @param tableName
     * @return
     */
    List<Map<String, String>> getPrimaryKeys(String driverClass, String url, String userName, String password, String databaseName, String tableName);
}
