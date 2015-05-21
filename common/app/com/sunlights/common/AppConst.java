package com.sunlights.common;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2014/9/10.
 */
public final class AppConst {

    private AppConst() {
    }

    //---- core ---
    public static final String VERIFY_CODE_REGISTER = "REGISTER";
    public static final String VERIFY_CODE_RESETPWD = "RESETPWD";
    public static final String VERIFY_CODE_RESET_ACCOUNT = "RESETACCOUNT";

    public static final Set<String> VALID_VERIFY_CODES = new HashSet<>();

    static {
        VALID_VERIFY_CODES.add(VERIFY_CODE_REGISTER);
        VALID_VERIFY_CODES.add(VERIFY_CODE_RESETPWD);
        VALID_VERIFY_CODES.add(VERIFY_CODE_RESET_ACCOUNT);
    }

    //-------account------
    public static final String SUBJECT_PURCHASE = "100001";
    public static final String SUBJECT_REDEEM = "100002";


    // ----- customer ---
    public static final String STATUS_VALID = "Y";
    public static final String STATUS_INVALID = "N";


    public static final String INVALID_CERTIFY = "0";
    public static final String VALID_CERTIFY = "1";

    // --- all ---

    public static final String STATUS_FLAG = "status";

    public static final long ONE_MINUTE = 60 * 1000;
    public static final String TOKEN = "token";

    public static final String APP_VERSION_1_2 = "1.2";

    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_MSG = "SUNLIGHTS_HEADER_MSG";
    public static final String HEADER_REGISTRATION_ID = "registrationId";
    public static final String HEADER_DEVICE = "DeviceNo";

    public static final String ROUTE_ACTION_METHOD = "ROUTE_ACTION_METHOD";

    public static final String PLATFORM_IOS = "ios";
    public static final String PLATFORM_ANDROID = "android";
    public static final String PLATFORM_PC = "pc";

    public static final String ANDROID_LATEST_VERSION = "ANDROID_LATEST_VERSION";

    public static final String CHANNEL_IOS = "0";
    public static final String CHANNEL_PC = "1";
    public static final String CHANNEL_ANDROID = "2";
    public static final String CHANNEL_H5 = "3";

    public static final String CACHE_PRODUCT_MONETARY_LIST = "product_monetary_list";//货币型产品缓存
    public static final String CACHE_PRODUCT_STF_LIST = "product_stf_list";//定期理财缓存
    public static final String CACHE_PRODUCT_INDEX = "product_index";//首页缓存

    public static final String FUND_CATEGORY_MONETARY = "MONETARY";
    public static final String FUND_CATEGORY_STF = "STF";
    public static final String FUND_GRAB_STATUS_NULL = "FUND-GRAB-STATUS-NULL";
    public static final String FUND_GRAB_STATUS_OVERDUE = "FUND-GRAB-STATUS-OVERDUE";
    public static final String FUND_GRAB_STATUS_SYNCHRONIZED = "FUND-GRAB-STATUS-SYNCHRONIZED";

}
