package com.sunlights.common;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: DictConst.java</p>
 * <p>Title: CodeConst.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public final class DictConst {
    private DictConst() {
        super();
    }

    public static String doc = ".";


    //===========================================core============================

    public static String PRODUCT = "PRODUCT";
    public static String PRODUCT_FUND = "FUND";
    public static String PRODUCT_RECOMMEND = "RECOMMEND";
    public static final String CUSTOMER_STATUS_NARMAL = "T";
    public static final String CUSTOMER_STATUS_LOCKED = "U";

    public static final String FP_RECOMMEND_FLAG = "FP.RECOMMEND.FLAG";// 推荐标志
    public static final String FP_RECOMMEND_FLAG_1 = "FP.RECOMMEND.FLAG.1";// 首页

    public static final String FP_RECOMMEND_TYPE = "FP.RECOMMEND.TYPE";// 推荐标志
    public static final String FP_RECOMMEND_TYPE_1 = "FP.RECOMMEND.TYPE.1";// 首页

    public static final String FP_PRODUCT_MANAGE_STATUS = "FP.PRODUCT.MANAGE.STATUS";// 产品管理状态
    public static final String FP_PRODUCT_MANAGE_STATUS_1 = "FP.PRODUCT.MANAGE.STATUS.1"; //上架

    public static final String FP_PRODUCT_TYPE = "FP.PRODUCT.TYPE"; //产品类型
    public static final String FP_PRODUCT_TYPE_1 = "FP.PRODUCT.TYPE.1"; //基金
    public static final String FP_PRODUCT_TYPE_1_1 = "FP.PRODUCT.TYPE.1.1"; //货币基金

    //=======================================customer==========================

    public static String CUSTOMER_CHANNEL = "FP.CUSTOMER.CHANNEL";//注册渠道
    public static String CUSTOMER_CHANNEL_1 = CUSTOMER_CHANNEL + doc + "1";//手机注册

    public static String CERTIFICATE_TYPE = "FP.CERTIFICATE.TYPE";// 证件
    public static String CERTIFICATE_TYPE_1 = CERTIFICATE_TYPE + doc + "1";//居民身份证


    public static String CUSTOMER_TYPE = "FP.CUSTOMER.TYPE";//客户类型
    public static String CUSTOMER_TYPE_1 = CUSTOMER_TYPE + doc + "1";//商家
    public static String CUSTOMER_TYPE_2 = CUSTOMER_TYPE + doc + "2";//个人

    public static String CUSTOMER_STATUS = "FP.CUSTOMER.STATUS";//客户状态
    public static String CUSTOMER_STATUS_1 = CUSTOMER_STATUS + doc + "1";//未激活
    public static String CUSTOMER_STATUS_2 = CUSTOMER_STATUS + doc + "2";//正常
    public static String CUSTOMER_STATUS_3 = CUSTOMER_STATUS + doc + "3";//已锁定

    public static String CUSTOMER_PROPERTY = "FP.CUSTOMER.PROPERTY";//用户属性
    public static String CUSTOMER_PROPERTY_1 = CUSTOMER_PROPERTY + doc + "1";//买家
    public static String CUSTOMER_PROPERTY_2 = CUSTOMER_PROPERTY + doc + "2";//卖家

    //============================================account======================
    public static String ACCOUNT_STATUS = "FP.ACCOUNT.STATUS";//账户状态
    public static String ACCOUNT_STATUS_1 = ACCOUNT_STATUS + doc + "1";//正常
    public static String ACCOUNT_STATUS_2 = ACCOUNT_STATUS + doc + "2";//冻结

    public static String SUBACCOUNT_STATUS = "FP.SUBACCOUNT.STATUS";//子账户状态
    public static String SUBACCOUNT_STATUS_1 = SUBACCOUNT_STATUS + doc + "1";//正常
    public static String SUBACCOUNT_STATUS_2 = SUBACCOUNT_STATUS + doc + "2";//冻结

    public static String HOLDCAPITAL_TYPE = "FP.HOLDCAPITAL.TYPE";//收益增长类型
    public static String HOLDCAPITAL_TYPE_1 = HOLDCAPITAL_TYPE + doc + "1";//申购增长
    public static String HOLDCAPITAL_TYPE_2 = HOLDCAPITAL_TYPE + doc + "2";//昨日收益增长


//================================================trade===========================

    public static String TRADE_TYPE = "FP.TRADE.TYPE";//交易类型
    public static String TRADE_TYPE_1 = TRADE_TYPE + doc + "1";//申购
    public static String TRADE_TYPE_2 = TRADE_TYPE + doc + "2";//赎回
    public static String TRADE_TYPE_3 = TRADE_TYPE + doc + "3";//分红

    public static String TRADE_STATUS = "FP.TRADE.STATUS";//交易状态
    public static String TRADE_STATUS_1 = TRADE_STATUS + doc + "1";//在途
    public static String TRADE_STATUS_2 = TRADE_STATUS + doc + "2";//成功
    public static String TRADE_STATUS_3 = TRADE_STATUS + doc + "3";//失败

    public static String PAYMENT_STATUS = "FP.PAYMENT.STATUS";//付款状态
    public static String PAYMENT_STATUS_1 = PAYMENT_STATUS + doc + "1";//不需付款
    public static String PAYMENT_STATUS_2 = PAYMENT_STATUS + doc + "2";//未付款
    public static String PAYMENT_STATUS_3 = PAYMENT_STATUS + doc + "3";//付款成功
    public static String PAYMENT_STATUS_4 = PAYMENT_STATUS + doc + "4";//付款失败
    public static String PAYMENT_STATUS_5 = PAYMENT_STATUS + doc + "5";//付款中


    public static String VERIFY_STATUS = "FP.VERIFY.STATUS";//确认状态
    public static String VERIFY_STATUS_1 = VERIFY_STATUS + doc + "1";//待确认
    public static String VERIFY_STATUS_2 = VERIFY_STATUS + doc + "2";//未确认
    public static String VERIFY_STATUS_3 = VERIFY_STATUS + doc + "3";//部分确认
    public static String VERIFY_STATUS_4 = VERIFY_STATUS + doc + "4";//确认完成
    public static String VERIFY_STATUS_5 = VERIFY_STATUS + doc + "5";//确认失败


    public static String PUSH_STATUS = "FP.PUSH.STATUS";//推送状态
    public static String PUSH_STATUS_1 = PUSH_STATUS + doc + "1";//不需推送
    public static String PUSH_STATUS_2 = PUSH_STATUS + doc + "2";//待推送
    public static String PUSH_STATUS_3 = PUSH_STATUS + doc + "3";//正在推送
    public static String PUSH_STATUS_4 = PUSH_STATUS + doc + "4";//推送成功
    public static String PUSH_STATUS_5 = PUSH_STATUS + doc + "5";//推送失败


    public static String PUSH_TYPE = "FP.PUSH.TYPE";//推送类型
    public static String PUSH_TYPE_1 = PUSH_TYPE + doc + "1";//系统公告
    public static String PUSH_TYPE_2 = PUSH_TYPE + doc + "2";//活动公告
    public static String PUSH_TYPE_3 = PUSH_TYPE + doc + "3";//交易公告
    public static String PUSH_TYPE_4 = PUSH_TYPE + doc + "4";//登录公告

    public static String PUSH_PLATFORM = "FP.PUSH.PLATFORM";//推送平台
    public static String PUSH_PLATFORM_ALL = PUSH_PLATFORM + doc + "1";//推送平台
    public static String PUSH_PLATFORM_ANDROID = PUSH_PLATFORM + doc + "2";//推送平台
    public static String PUSH_PLATFORM_IOS = PUSH_PLATFORM + doc + "3";//推送平台


}
