package com.joesea.codebuilder.controller;

import com.joesea.codebuilder.common.CodeBuilderCommon;
import com.joesea.codebuilder.service.CodeBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2020/1/17</p>
 * <p>@description : </p>
 */
@RestController
public class CodeBuilderController {

    @Autowired
    private CodeBuilderService codeBuilderService;

    /**
     * 获取指定数据库下的所有表信息
     * @return 所有表信息
     */
    @RequestMapping(value = "getDatabasesInfo")
    public List<Map<String, String>> getDatabasesInfo(String driverClass, String url, String userName, String password) {
        return codeBuilderService.getDatabasesInfo(driverClass, url, userName, password);
    }

    /**
     * 获取指定数据库下的所有表信息
     * @return 所有表信息
     */
    @RequestMapping(value = "getTablesInfo")
    public List<Map<String, String>> getTablesInfo(String driverClass, String url, String userName, String password, String databaseName) {
        return codeBuilderService.getTablesInfo(driverClass, url, userName, password, databaseName);
    }

    /**
     * 获取表结构信息
     * @return 表结构信息
     */
    @RequestMapping(value = "getTableStruct")
    public List<Map<String, String>> getTableStruct(String driverClass, String url, String userName, String password, String databaseName, String tableName) {
        return codeBuilderService.getTableStruct(driverClass, url, userName, password, databaseName, tableName);
    }
    /**
     * 获取表结构信息
     * @return 表结构信息
     */
    @RequestMapping(value = "getPrimaryKeys")
    public List<Map<String, String>> getPrimaryKeys(String driverClass, String url, String userName, String password, String databaseName, String tableName) {
        return codeBuilderService.getPrimaryKeys(driverClass, url, userName, password, databaseName, tableName);
    }

    @RequestMapping(value = "test")
    public Object test() {
//        String driverClass = "com.mysql.cj.jdbc.Driver", url = "jdbc:mysql://127.0.0.1:3306?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8",
//                userName = "root", password = "root", databaseName = "manage_system", tableName = "t_sys_user";
//
//        return getPrimaryKeys(driverClass, url, userName, password, databaseName, tableName);
        String driverClass = "com.mysql.cj.jdbc.Driver", url = "jdbc:mysql://127.0.0.1:3306?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8",
                userName = "root", password = "root", databaseName = "manage_system", tableName = "t_sys_user";

        List<Map<String, String>> tablesInfo = codeBuilderService.getTablesInfo(driverClass, url, userName, password, databaseName);

        for (Map<String, String> table : tablesInfo) {
            List<Map<String, String>> tableColumns = codeBuilderService.getTableStruct(driverClass, url, userName, password, databaseName, table.get("TABLE_NAME"));
            List<Map<String, String>> primaryKeys = codeBuilderService.getPrimaryKeys(driverClass, url, userName, password, databaseName, table.get("TABLE_NAME"));

            CodeBuilderCommon codeBuilder = new CodeBuilderCommon(table, tableColumns, primaryKeys);

//            codeBuilder.generateAllCode("");
        }
        return "hello";
    }

}
