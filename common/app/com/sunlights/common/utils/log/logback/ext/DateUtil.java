package com.sunlights.common.utils.log.logback.ext;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2015/5/10.
 */
public class DateUtil {
    private static final SimpleDateFormat dateFormat;
    static
    {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
    }

    public static boolean isValidDate(String s) {
        try {
            dateFormat.parse(s);
            return true;
        } catch (Exception e) {
            // throw java.text.ParseException或者NullPointerException说明格式
            return false;
        }
    }

    public static void main(String[]args){
        System.err.print(isValidDate("111"));
    }
}
