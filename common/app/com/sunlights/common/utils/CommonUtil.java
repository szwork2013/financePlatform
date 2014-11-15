package com.sunlights.common.utils;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: CommonUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class CommonUtil {
    private static CommonUtil commonUtil = new CommonUtil();

    public static CommonUtil getInstance() {
        return commonUtil;
    }

    public CommonUtil() {

    }


    /**
     * 参数验证
     *
     * @param params
     */
    public void validateParams(String... params) {
        for (String param : params) {
            if (StringUtils.isEmpty(param)) {
                throw errorBusinessException(MsgCode.ACCESS_FAIL, param);
            }
        }
    }

    public BusinessRuntimeException errorBusinessException(MsgCode msgCode, Object... params) {
        String detail = getDetail(msgCode, params);
        return new BusinessRuntimeException(Severity.ERROR, msgCode.getCode(), msgCode.getMessage(), detail);
    }

    private String getDetail(MsgCode msgCode, Object[] params) {
        String detail = msgCode.getDetail();
        if (params != null) {
            detail = MessageFormat.format(detail, params);
        }
        return detail;
    }

    public BusinessRuntimeException fatalBusinessException(MsgCode msgCode, Object... params) {
        String detail = getDetail(msgCode, params);
        return new BusinessRuntimeException(Severity.FATAL, msgCode.getCode(), msgCode.getMessage(), detail);
    }

    public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";

    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String DATE_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_ICU = "yyyy-MM-dd HH:mm:ss:SSS";

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_SHORT);

    public static String dateToString(Date date, String... format) {
        if (date == null) {
            return "";
        }

        if (format != null) {
            return new SimpleDateFormat(format[0]).format(date);
        } else {
            return DATE_FORMAT.format(date);
        }
    }

    public static Date stringToDate(String dateString, String... format) throws ParseException {
        if (StringUtils.isEmpty(dateString)) {
            return new Date();
        }

        if (format != null) {
            return new SimpleDateFormat(format[0]).parse(dateString);
        } else {
            return DATE_FORMAT.parse(dateString);
        }
    }

}
