package com.joesea.codebuilder.common;

import com.joesea.codebuilder.base.BaseTest;
import com.joesea.codebuilder.service.CodeBuilderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Map;

class CodeBuilderCommonTest extends BaseTest {

    @Autowired
    private CodeBuilderService codeBuilderService;

    @Test
    void generateAllCode() {
        String driverClass = "com.mysql.cj.jdbc.Driver", url = "jdbc:mysql://127.0.0.1:3306?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8",
                userName = "root", password = "root", databaseName = "manage_system", tableName = "t_sys_user";

        List<Map<String, String>> tablesInfo = codeBuilderService.getTablesInfo(driverClass, url, userName, password, databaseName);

        for (Map<String, String> table : tablesInfo) {
            List<Map<String, String>> tableColumns = codeBuilderService.getTableStruct(driverClass, url, userName, password, databaseName, table.get("TABLE_NAME"));
            List<Map<String, String>> primaryKeys = codeBuilderService.getPrimaryKeys(driverClass, url, userName, password, databaseName, table.get("TABLE_NAME"));

            CodeBuilderCommon codeBuilder = new CodeBuilderCommon(table, tableColumns, primaryKeys);

            codeBuilder.generateAllCode(new File("/Users/joesealea/IdeaProjects/code-builder/src/main/resources/templates/ftl"));
        }

//        String str = "00330p333111p33222p333";
//        int index = str.lastIndexOf("33");
//        System.out.println(str.substring(0, index));
    }
}