package com.sunlights.common;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2014/9/10.
 */
public final class AppConst {

    private AppConst(){}

    //---- core ---
    public static String VERIFY_CODE_REGISTER = "REGISTER";
    public static String VERIFY_CODE_RESETPWD = "RESETPWD";
    public static String VERIFY_CODE_RESET_ACCOUNT = "RESETACCOUNT";

    public static Set<String> VALID_VERIFY_CODES = new HashSet<>();
    static{
        VALID_VERIFY_CODES.add(VERIFY_CODE_REGISTER);
        VALID_VERIFY_CODES.add(VERIFY_CODE_RESETPWD);
        VALID_VERIFY_CODES.add(VERIFY_CODE_RESET_ACCOUNT);
    }

    // ----- customer ---
    public static String VERIFY_CODE_STATUS_VALID = "Y";
    public static String VERIFY_CODE_STATUS_INVALID = "N";

    /**
     * 手机
     */
    public static String REGISTER_CHANNEL_MOBILE = "M";

    /**
     * 客户类型：个人
     */
    public static String CUSTOMER_TYPE_PERSON = "C";
    /**
     * 客户类型：商户
     */
    public static String CUSTOMER_TYPE_MERCHANT = "B";

    /**
     * 客户状态：正常
     */
    public static String CUSTOMER_STATUS_NORMAL = "T";
    /**
     * 客户状态：已锁定
     */
    public static String CUSTOMER_STATUS_LOCK = "U";

    /**
     * 证件类型：身份证
     */
    public static String ID_CARD = "I";

    public static String CUSTOMER_BUYER = "1";

    public static String CUSTOMER_SELLER = "2";

    public static String INVALID_CERTIFY = "0";
    public static String VALID_CERTIFY = "1";

    // --- all ---

    public static String DELETE_FLAG = "defunctInd";

    public static long ONE_MINUTE = 60 * 1000;
    public static String TOKEN = "token";
}
