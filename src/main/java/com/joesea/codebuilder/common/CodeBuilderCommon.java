package com.joesea.codebuilder.common;

import com.joesea.codebuilder.utils.DateFormatUtil;
import com.joesea.codebuilder.utils.StringUtils;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2020/1/30</p>
 * <p>@description : </p>
 *
 */
public class CodeBuilderCommon {
    private Map<String, Object> map = new HashMap<>();
    private Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    private final String templatesPath = "/Users/joesealea/IdeaProjects/code-builder/src/main/resources/templates/ftl";
    private final String javaSourceBasePath = "/Users/joesealea/Downloads/java";

    public CodeBuilderCommon(Map<String, String> tablesInfo, List<Map<String, String>> tableColumns, List<Map<String, String>> primaryKeys) {

        List<String> importFullJavaType = new ArrayList<>();

        map.putAll(tablesInfo);

        String entityName = StringUtils.replaceFirstStr(tablesInfo.get("TABLE_NAME").toLowerCase(), "t_sys_");
        entityName = StringUtils.underline2CamelCase(StringUtils.first2UpperCase(entityName));
        map.put("ENTITY_CLASS_NAME", entityName);
        map.put("ENTITY_NAME", StringUtils.first2LowerCase(entityName));

        for (Map<String, String> column : tableColumns) {
            column.put("JAVA_TYPE", typeName2JavaType(column.get("TYPE_NAME")));
            column.put("ENTITY_PROPERTY_NAME", StringUtils.underline2CamelCase(column.get("COLUMN_NAME").toLowerCase()));
            column.put("FIRST_UP_ENTITY_PROPERTY_NAME", StringUtils.first2UpperCase(StringUtils.underline2CamelCase(column.get("COLUMN_NAME"))));
            if (column.get("JAVA_TYPE").equals("Date")) {
                if (!importFullJavaType.contains("java.util.Date")) {
                    importFullJavaType.add("java.util.Date");
                }
            }
        }
        map.put("TABLE_COLUMNS", tableColumns);


        for (Map<String, String> column : primaryKeys) {

            for (Map<String, String> tableColumn : tableColumns) {
                if (column.get("COLUMN_NAME").equals(tableColumn.get("COLUMN_NAME"))) {
                    column.putAll(tableColumn);
                    break;
                }
            }
        }
        map.put("PRIMARY_KEYS", primaryKeys);

        map.put("IMPORT_FULL_JAVA_TYPE", importFullJavaType);

        /**
         * 基本数据
         */
        map.put("BASE_PACKAGE", "com.system.core");
        map.put("AUTHOR", "Joesea Lea");
        map.put("CREATE_DATE", DateFormatUtil.format(Calendar.getInstance().getTime(), "yyyy/MM/dd"));
        map.put("ENTITY_DESC", "");
        try {
            FileTemplateLoader ftl = new FileTemplateLoader(new File(templatesPath));
            cfg.setTemplateLoader(ftl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateAllCode(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File f : files) {
                generateAllCode(f);
            }
        } else {
            String templateFilePath = file.getAbsolutePath().replace(templatesPath, "");

            String[] temp = templateFilePath.split("\\/");
            String javaFilePath = javaSourceBasePath + templateFilePath.substring(0, templateFilePath.lastIndexOf(temp[temp.length - 1])) +  map.get("ENTITY_CLASS_NAME").toString() + temp[temp.length - 1];
            int index = javaFilePath.lastIndexOf("Template.ftl");
            if (index > 0) {
                javaFilePath = javaFilePath.substring(0, index) + ".java";
//                String javaFilePath = javaSourceBasePath  + templateFilePath.substring(0, index) + ".java";
                generateCode(templateFilePath, javaFilePath);
            } else {
                index = javaFilePath.lastIndexOf("Templatemapper.ftl");
                if (index > 0) {
                    javaFilePath = javaFilePath.substring(0, index) + ".xml";
                    generateCode(templateFilePath, javaFilePath);
                }
            }
        }
    }

    public void generateCode(String templateName, String saveFilePath) {
        try {
            Template template = cfg.getTemplate(templateName);

            File file = new File(saveFilePath);
            if (!file.exists()) {
                String parentFilePath = file.getParentFile().getAbsolutePath();
                File parentFile = new File(parentFilePath);
                if (!parentFile.exists()) {
                    parentFile.mkdir();
                }
                file.createNewFile();
            }

            template.process(map, new PrintWriter(file));
//            template.process(map, new PrintWriter(System.out));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Map<String, Object> getEntityDataModel() {
        Map<String, Object> model = new HashMap<>();

        return model;
    }

    private String typeName2JavaType(String typeName) {

        /**
         * 整型
         */
        List<String> intType = new ArrayList<>();
        intType.add("TINYINT");
        intType.add("SMALLINT");
        intType.add("INT");
        intType.add("MEDIUMINT");
        intType.add("INTEGER");
        if (intType.contains(typeName)) {
            return "int";
        }

        /**
         * 长整型
         */
        List<String> longType = new ArrayList<>();
        longType.add("BIGINT");
        if (longType.contains(typeName)) {
            return "long";
        }

        /**
         * 浮点型
         */
        List<String> doubleType = new ArrayList<>();
        doubleType.add("FLOAT");
        doubleType.add("DOUBLE");
        doubleType.add("DECIMAL");
        doubleType.add("DOUBLE PRECISION");
        doubleType.add("DEC");
        if (doubleType.contains(typeName)) {
            return "double";
        }

        /**
         * 布尔类型
         */
        List<String> booleanType = new ArrayList<>();
        booleanType.add("BOOLEAN");
        if (booleanType.contains(typeName)) {
            return "boolean";
        }

        /**
         * 字符串类型
         */
        List<String> stringType = new ArrayList<>();
        stringType.add("CHAR");
        stringType.add("CHAR BYTE");
        stringType.add("VARBINARY");
        stringType.add("VARCHAR");
        stringType.add("TINYTEXT");
        stringType.add("TEXT");
        stringType.add("MEDIUMTEXT");
        stringType.add("LONGTEXT");
        stringType.add("TINYBLOB");
        stringType.add("BLOB");
        stringType.add("MEDIUMBLOB");
        stringType.add("BLOB DATA TYPE");
        stringType.add("LONGBLOB");
        stringType.add("BINARY");
        stringType.add("BIT");
        if (stringType.contains(typeName)) {
            return "String";
        }

        /**
         * 日期型
         */
        List<String> dateType = new ArrayList<>();
        dateType.add("TIME");
        dateType.add("DATE");
        dateType.add("DATETIME");
        dateType.add("TIMESTAMP");
        dateType.add("YEAR DATA TYPE");
        if (dateType.contains(typeName)) {
            return "Date";
        }

        throw new IllegalArgumentException(typeName + "数据类型未定义");
    }
}
