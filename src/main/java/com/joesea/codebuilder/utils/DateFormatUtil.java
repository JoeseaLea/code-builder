package com.joesea.codebuilder.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2020/2/11</p>
 * <p>@description : </p>
 */
public class DateFormatUtil {
    public static String format(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        return df.format(date);
    }
}
