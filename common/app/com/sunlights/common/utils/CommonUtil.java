package com.sunlights.common.utils;

import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.mvc.Http;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	private static String BLANK = "--";

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

	public static final String DATE_FORMAT_ICU = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_SHORT);

	public static synchronized String dateToString(Date date, String... format) {
		if (date == null) {
			return "";
		}

		if (format != null && format.length > 0) {
			return new SimpleDateFormat(format[0]).format(date);
		} else {
			return DATE_FORMAT.format(date);
		}
	}

	public static synchronized Date stringToDate(String dateString, String... format) throws ParseException {
		if (StringUtils.isEmpty(dateString)) {
			return new Date();
		}

		if (format != null && format.length > 0) {
			return new SimpleDateFormat(format[0]).parse(dateString);
		} else {
			return DATE_FORMAT.parse(dateString);
		}
	}

	public static String format(String value) {
		return StringUtils.isEmpty(value) ? BLANK : value;
	}

	public static Integer format(Integer value) {
		return (value == null) ? 0 : value;
	}
    
    
    public static String getCurrentVersion(Http.Request request){
        String userAgent = request.getHeader(AppConst.HEADER_USER_AGENT);
        //Mozilla/5.0 (iPhone; CPU iPhone OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D167\jindoujialicai\1.2

        Logger.info(">>userAgent:" + userAgent);

        String name = "jindoujialicai";
        int index = userAgent.indexOf(name);
        if (index <= 0){
            return "0";
        }
        String version = userAgent.substring(index + name.length() + 1, userAgent.length());
        Logger.info(">>当前版本号：" + version);

        return version;
    }

    public static String getCurrentPlatform(Http.Request request){
        String userAgent = request.getHeader(AppConst.HEADER_USER_AGENT);
        //Mozilla/5.0 (iPhone; CPU iPhone OS 7_1 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D167\jindoujialicai\1.2

        Logger.info(">>userAgent:" + userAgent);

        String name = "iPhone";
        int index = userAgent.indexOf(name);
        if (index > 0){
            Logger.info(">>当前platform：ios");
            return "ios";
        }

        Logger.info(">>当前platform：android");
        return "android";
    }


}
