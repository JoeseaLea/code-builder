package com.joesea.codebuilder.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2020/1/31</p>
 * <p>@description : </p>
 */
public class StringUtils extends org.springframework.util.StringUtils {
    /**
     * 字符串首字母转大写
     * @param str 传入字符串
     * @return 转换结果字符串
     */
    public static String first2UpperCase (String str) {
        if (null != str) {
            if ("".equals(str)) {
                return str;
            } else if (1 == str.length()) {
                return str.substring(0, 1).toUpperCase();
            } else {
                return str.substring(0, 1).toUpperCase() + str.substring(1);
            }
        }
        return null;
    }

    /**
     * 字符串首字母转小写
     * @param str 传入字符串
     * @return 转换结果字符串
     */
    public static String first2LowerCase (String str) {
        if (null != str) {
            if ("".equals(str)) {
                return str;
            } else if (1 == str.length()) {
                return str.substring(0, 1).toLowerCase();
            } else {
                return str.substring(0, 1).toLowerCase() + str.substring(1);
            }
        }
        return null;
    }

    /**
     * 下划线连接字符串转驼峰式字符串
     * @param str 下划线字符串
     * @return 驼峰式字符串
     */
    public static String underline2CamelCase (String str) {
        if (null != str) {
            if ("".equals(str)) {
                return str;
            }

            String[] strArray = str.split("_");

            int length = strArray.length;
            if (length > 0) {
                StringBuilder sb = new StringBuilder(strArray[0]);

                for (int i = 1; i < length; i++) {
                    sb.append(first2UpperCase(strArray[i]));
                }

                return sb.toString();
            } else {
                return "";
            }
        }

        return null;
    }

    /**
     *
     * @param sourceStr
     * @param patternStr
     * @return
     */
    public static String replaceFirstStr(String sourceStr, String patternStr) {
        Pattern pattern = Pattern.compile("^" + patternStr);
        Matcher matcher = pattern.matcher(sourceStr);
        if (matcher.find()) {
            return sourceStr.replaceFirst(patternStr, "");
        }
        return sourceStr;
    }
}
