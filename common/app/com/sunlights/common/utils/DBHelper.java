package com.sunlights.common.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;

/**
 * <p>Project: fsp</p>
 * <p>Title: Constants.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class DBHelper {

    // Define batch objects record size.
    public static final int DEFAULT_BATCH_SIZE = 1000;

    // Define application event audit executor name.
    public static final String AUDIT_EVENT_EXECUTOR = "auditEventExecutor";

    // Define HQL SELECT query.
    public static final String SQLMAP_KEYWORDS_FORUPDATE = "FOR UPDATE";
    public static final String HQL_SELECT_COUNT_FROM = "SELECT COUNT(obj) as totalCount FROM ";
    public static final String HQL_SELECT_FROM = "SELECT obj FROM ";
    public static final String HQL_ALIAS_OBJECT = " obj ";

    // Define HQL Key Word.
    public static final String HQL_KEYWORD_OR = " or ";
    public static final String HQL_KEYWORD_AND = " and ";
    public static final String HQL_KEYWORD_AND_VALUE = " = ? and ";
    public static final String HQL_KEYWORD_WHERE = " where ";
    public static final String HQL_KEYWORD_LIKE = " like ";

    // Define HQL Placeholder sign.
    public static final String HQL_PLACEHOLDER_EQUALITY_COLON = " = :";
    public static final String HQL_PLACEHOLDER_EQUALITY = " = ";
    public static final String HQL_PLACEHOLDER_APOSTROPHE = "'";

    // Define HQL Operator sign.
    public static final String HQL_OPERATOR_LE = " <= ";
    public static final String HQL_OPERATOR_LT = " < ";
    public static final String HQL_OPERATOR_GE = " >= ";
    public static final String HQL_OPERATOR_GT = " > ";
    public static final String HQL_OPERATOR_NE = " != ";
    public static final String HQL_OPERATOR_PERCENT = "%";
    public static final String HQL_OPERATOR_BRACKET_BEFORE = "(";
    public static final String HQL_OPERATOR_BRACKET_AFTER = ")";

    // Define Prefix placeholder 'filter_'.
    public static final String PREFIX_PLACEHOLDER_FILTER = "filter_";

    public static Timestamp getCurrentTime() {
        long time = Calendar.getInstance(Locale.CANADA).getTime().getTime();
        return new Timestamp(time);
    }

    private static final long ONE_MINUTE = 60000;

    public static Timestamp afterMinutes(Timestamp date, long min) {
        long fTime = date.getTime() + min * ONE_MINUTE;
        return new Timestamp(fTime);
    }

    public static Timestamp beforeMinutes(Timestamp date, long min) {
        long fTime = date.getTime() - min * ONE_MINUTE;
        return new Timestamp(fTime);
    }

}
