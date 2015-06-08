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

    public static String DOT = ".";


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
    public static String CUSTOMER_CHANNEL_1 = CUSTOMER_CHANNEL + DOT + "1";//手机注册

    public static String CERTIFICATE_TYPE = "FP.CERTIFICATE.TYPE";// 证件
    public static String CERTIFICATE_TYPE_1 = CERTIFICATE_TYPE + DOT + "1";//居民身份证


    public static String CUSTOMER_TYPE = "FP.CUSTOMER.TYPE";//客户类型
    public static String CUSTOMER_TYPE_1 = CUSTOMER_TYPE + DOT + "1";//商家
    public static String CUSTOMER_TYPE_2 = CUSTOMER_TYPE + DOT + "2";//个人

    public static String CUSTOMER_STATUS = "FP.CUSTOMER.STATUS";//客户状态
    public static String CUSTOMER_STATUS_1 = CUSTOMER_STATUS + DOT + "1";//未激活
    public static String CUSTOMER_STATUS_2 = CUSTOMER_STATUS + DOT + "2";//正常
    public static String CUSTOMER_STATUS_3 = CUSTOMER_STATUS + DOT + "3";//已锁定

    public static String CUSTOMER_PROPERTY = "FP.CUSTOMER.PROPERTY";//用户属性
    public static String CUSTOMER_PROPERTY_1 = CUSTOMER_PROPERTY + DOT + "1";//买家
    public static String CUSTOMER_PROPERTY_2 = CUSTOMER_PROPERTY + DOT + "2";//卖家

    //============================================account======================
    public static String ACCOUNT_STATUS = "FP.ACCOUNT.STATUS";//账户状态
    public static String ACCOUNT_STATUS_1 = ACCOUNT_STATUS + DOT + "1";//正常
    public static String ACCOUNT_STATUS_2 = ACCOUNT_STATUS + DOT + "2";//冻结

    public static String SUBACCOUNT_STATUS = "FP.SUBACCOUNT.STATUS";//子账户状态
    public static String SUBACCOUNT_STATUS_1 = SUBACCOUNT_STATUS + DOT + "1";//正常
    public static String SUBACCOUNT_STATUS_2 = SUBACCOUNT_STATUS + DOT + "2";//冻结

    public static String HOLDCAPITAL_TYPE = "FP.HOLDCAPITAL.TYPE";//收益增长类型
    public static String HOLDCAPITAL_TYPE_1 = HOLDCAPITAL_TYPE + DOT + "1";//申购增长
    public static String HOLDCAPITAL_TYPE_2 = HOLDCAPITAL_TYPE + DOT + "2";//昨日收益增长


//================================================trade===========================

    public static String TRADE_TYPE = "FP.TRADE.TYPE";//交易类型
    public static String TRADE_TYPE_1 = TRADE_TYPE + DOT + "1";//申购
    public static String TRADE_TYPE_2 = TRADE_TYPE + DOT + "2";//赎回
    public static String TRADE_TYPE_3 = TRADE_TYPE + DOT + "3";//分红

    public static String TRADE_STATUS = "FP.TRADE.STATUS";//交易状态
    public static String TRADE_STATUS_1 = TRADE_STATUS + DOT + "1";//在途
    public static String TRADE_STATUS_2 = TRADE_STATUS + DOT + "2";//成功
    public static String TRADE_STATUS_3 = TRADE_STATUS + DOT + "3";//失败

    public static String PAYMENT_STATUS = "FP.PAYMENT.STATUS";//付款状态
    public static String PAYMENT_STATUS_1 = PAYMENT_STATUS + DOT + "1";//不需付款
    public static String PAYMENT_STATUS_2 = PAYMENT_STATUS + DOT + "2";//未付款
    public static String PAYMENT_STATUS_3 = PAYMENT_STATUS + DOT + "3";//付款成功
    public static String PAYMENT_STATUS_4 = PAYMENT_STATUS + DOT + "4";//付款失败
    public static String PAYMENT_STATUS_5 = PAYMENT_STATUS + DOT + "5";//付款中


    public static String VERIFY_STATUS = "FP.VERIFY.STATUS";//确认状态
    public static String VERIFY_STATUS_1 = VERIFY_STATUS + DOT + "1";//待确认
    public static String VERIFY_STATUS_2 = VERIFY_STATUS + DOT + "2";//未确认
    public static String VERIFY_STATUS_3 = VERIFY_STATUS + DOT + "3";//部分确认
    public static String VERIFY_STATUS_4 = VERIFY_STATUS + DOT + "4";//确认完成
    public static String VERIFY_STATUS_5 = VERIFY_STATUS + DOT + "5";//确认失败


    public static String PUSH_STATUS = "FP.PUSH.STATUS";//推送状态
    public static String PUSH_STATUS_1 = PUSH_STATUS + DOT + "1";//不需推送
    public static String PUSH_STATUS_2 = PUSH_STATUS + DOT + "2";//待推送
    public static String PUSH_STATUS_3 = PUSH_STATUS + DOT + "3";//正在推送
    public static String PUSH_STATUS_4 = PUSH_STATUS + DOT + "4";//推送成功
    public static String PUSH_STATUS_5 = PUSH_STATUS + DOT + "5";//推送失败


    public static String PUSH_TYPE = "FP.PUSH.TYPE";//推送类型
    public static String PUSH_TYPE_1 = PUSH_TYPE + DOT + "1";//系统公告
    public static String PUSH_TYPE_2 = PUSH_TYPE + DOT + "2";//活动公告
    public static String PUSH_TYPE_3 = PUSH_TYPE + DOT + "3";//交易公告
    public static String PUSH_TYPE_4 = PUSH_TYPE + DOT + "4";//登录提示公告
    public static String PUSH_TYPE_6 = PUSH_TYPE + DOT + "6";//失败的公告
    public static String PUSH_TYPE_5 = PUSH_TYPE + DOT + "5";//由后台操作人员手动发出的指定公告


    public static String PUSH_PLATFORM = "FP.PUSH.PLATFORM";//推送平台
    public static String PUSH_PLATFORM_ALL = PUSH_PLATFORM + DOT + "1";//推送平台
    public static String PUSH_PLATFORM_ANDROID = PUSH_PLATFORM + DOT + "2";//推送平台
    public static String PUSH_PLATFORM_IOS = PUSH_PLATFORM + DOT + "3";//推送平台


    public static String SEND_TYPE = "FP.SEND.TYPE";//发送类型
    public static String SEND_TYPE_SMS = "FP.SEND.TYPE.1";//短信发送
    public static String SEND_TYPE_PUSH_GROUP = "FP.SEND.TYPE.2";//群发推送
    public static String SEND_TYPE_PUSH_CUSTOMER = "FP.SEND.TYPE.3";//个人推送

    //活动-客户条件
    public static String ACTIVITY_NEW_REGISTER_TRADE = "FP.ACTIVITY.CONDITION.1";//新注册且首次申购
    public static String ACTIVITY_NEW_TRADE = "FP.ACTIVITY.CONDITION.2";//首次申购
    public static String ACTIVITY_TRADE = "FP.ACTIVITY.CONDITION.3";//活动时间内的首次申购

    //社交类型
    public static String SOCIAL_TYPE = "FP.SOCIAL.TYPE";
    public static String SOCIAL_TYPE_WECHAT = SOCIAL_TYPE + DOT + "1";//微信

}
