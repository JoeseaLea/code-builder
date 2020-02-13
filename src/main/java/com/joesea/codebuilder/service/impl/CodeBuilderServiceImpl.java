package com.joesea.codebuilder.service.impl;

import com.joesea.codebuilder.service.CodeBuilderService;
import com.joesea.codebuilder.utils.DataBaseUtil;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2020/1/30</p>
 * <p>@description : </p>
 */
@Service
public class CodeBuilderServiceImpl implements CodeBuilderService {

    @Override
    public List<Map<String, String>> getDatabasesInfo(String driverClass, String url, String userName, String password) {
        List<Map<String, String>> databaseInfo = new ArrayList<>();

        Connection conn = DataBaseUtil.getConnection(driverClass, url, userName, password);

        List<String> databaseProperties = new ArrayList<>();

        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getCatalogs();

            ResultSetMetaData metaData = rs.getMetaData();  //获取列集
            int columnCount = metaData.getColumnCount(); //获取列的数量
            for (int i = 1; i <= columnCount; i++) { //循环列
                String columnName = metaData.getColumnName(i); //通过序号获取列名,起始值为1
                databaseProperties.add(columnName);
            }

            resultSetCommon(rs, databaseProperties, databaseInfo);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConnection(conn);
        }

        return databaseInfo;
    }

    @Override
    public List<Map<String, String>> getTablesInfo(String driverClass, String url, String userName, String password, String databaseName) {
        List<Map<String, String>> tablesInfo = new ArrayList<>();

        Connection conn = DataBaseUtil.getConnection(driverClass, url, userName, password);

        List<String> tableProperties = new ArrayList<>();

        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(databaseName, databaseName, "%", new String[] {"TABLE"});

            ResultSetMetaData metaData = rs.getMetaData();  //获取列集
            int columnCount = metaData.getColumnCount(); //获取列的数量
            for (int i = 1; i <= columnCount; i++) { //循环列
                String columnName = metaData.getColumnName(i); //通过序号获取列名,起始值为1
                tableProperties.add(columnName);
            }

            resultSetCommon(rs, tableProperties, tablesInfo);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConnection(conn);
        }

        return tablesInfo;
    }

    @Override
    public List<Map<String, String>> getTableStruct(String driverClass, String url, String userName, String password, String databaseName, String tableName) {
        List<Map<String, String>> tableColumns = new ArrayList<>();

        Connection conn = DataBaseUtil.getConnection(driverClass, url, userName, password);

        List<String> columns = new ArrayList<>();

        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getColumns(databaseName, databaseName, tableName, "%");

            ResultSetMetaData metaData = rs.getMetaData();  //获取列集
            int columnCount = metaData.getColumnCount(); //获取列的数量
            for (int i = 1; i <= columnCount; i++) { //循环列
                String columnName = metaData.getColumnName(i); //通过序号获取列名,起始值为1
                columns.add(columnName);
            }

            resultSetCommon(rs, columns, tableColumns);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConnection(conn);
        }

        return tableColumns;
    }

    @Override
    public List<Map<String, String>> getPrimaryKeys(String driverClass, String url, String userName, String password, String databaseName, String tableName) {
        List<Map<String, String>> primaryKeys = new ArrayList<>();

        Connection conn = DataBaseUtil.getConnection(driverClass, url, userName, password);

        List<String> columns = new ArrayList<>();

        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getPrimaryKeys(databaseName, databaseName, tableName);

            ResultSetMetaData metaData = rs.getMetaData();  //获取列集
            int columnCount = metaData.getColumnCount(); //获取列的数量
            for (int i = 1; i <= columnCount; i++) { //循环列
                String columnName = metaData.getColumnName(i); //通过序号获取列名,起始值为1
                columns.add(columnName);
            }

            resultSetCommon(rs, columns, primaryKeys);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConnection(conn);
        }

        return primaryKeys;
    }

    private void resultSetCommon(ResultSet rs, List<String> properties, List<Map<String, String>> result) throws SQLException {
        while (rs.next()) {
            Map<String, String> propertyInfo = new HashMap<>();
            for (String property : properties) {
                propertyInfo.put(property, rs.getString(property));
            }
            result.add(propertyInfo);
        }
    }
}
