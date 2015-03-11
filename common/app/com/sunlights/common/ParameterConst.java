package com.sunlights.common;

/**
 * Created by Administrator on 2014/9/15.
 */
public final class ParameterConst {
    /**
     * 验证码在单位时间内最大次数
     */
    public static String VERIFYCODE_MAX = "VERIFYCODE_MAX";

    /**
     * 验证码最大次数的单位时间(min)
     */
    public static String VERIFYCODE_TIMES = "VERIFYCODE_TIMES";

    /**
     * 验证码时效时间(min)
     */
    public static String VERIFYCODE_EXPIRY = "VERIFYCODE_EXPIRY";

    /**
     * 密码允许错误最大次数
     */
    public static String PWD_MAX = "PWD_MAX";

    /**
     * 登录失败没到最大次数，隔XXX时间后失败次数清0的时间(min)
     */
    public static String LOGIN_PERIOD = "LOGIN_PERIOD";

    /**
     * 用户解锁时间(min)
     */
    public static String USERUNLOCK_PERIOD = "USERUNLOCK_PERIOD";

    /**
     * 超出密码错误次数，首次暂时锁定的时间(min)
     */
    public static String RELIEVE_SUSLOCK_PERIOD = "RELIEVE_SUSLOCK_PERIOD";

    /**
     * 缓存存在时间(min)
     */
    public static String CACHE_EXPIRY = "CACHE_EXPIRY";
    /**
     * cookie存在时间(min)
     */
    public static String COOKIE_EXPIRY = "COOKIE_EXPIRY";
    /**
     * 后台CustomerSession有效时间(min)，一般与COOKIE_EXPIRY存在时间一致
     */
    public static String SESSION_EXPIRY = "SESSION_EXPIRY";

    /**
     * 实名认证-N真实调用/非N 测试模式
     */
    public static String CERTIFY_TEST = "CERTIFY_TEST";
    /**
     * 实名认证-URL地址
     */
    public static String CERTIFY_URL = "CERTIFY_URL";
    /**
     * 实名认证-帐号用户名
     */
    public static String CERTIFY_USERNAME = "CERTIFY_USERNAME";
    /**
     * 实名认证-帐号密码
     */
    public static String CERTIFY_PASSWORD = "CERTIFY_PASSWORD";


    /**
     * 短信接口-URL地址
     */
    public static String SMS_URL = "SMS_URL";
    /**
     * 短信接口-第二办公室门牌号码
     */
    public static String SMS_ACCOUNT = "SMS_ACCOUNT";
    /**
     * 短信接口-授权码
     */
    public static String SMS_WARRANTYCODE = "SMS_WARRANTYCODE";
    /**
     * 短信接口-密码
     */
    public static String SMS_PASSWORD = "SMS_PASSWORD";
    /**
     * 短信接口-通道编号
     */
    public static String SMS_CHANNEL = "SMS_CHANNEL";
    /**
     * 短信接口-N真实调用/非N 测试模式
     */
    public static String SMS_TEST = "SMS_TEST";


    /**
     * "极光推送app_key"
     */
    public static String APP_KEY = "APP_KEY";
    /**
     * "极光推送secret_key"
     */
    public static String SECRET_KEY = "SECRET_KEY";


    /**
     * 移动号段
     */
    public static String CMCC = "CMCC";
    /**
     * 电信号段
     */
    public static String CTCC = "CTCC";
    /**
     * 联通号段
     */
    public static String CUCC = "CUCC";


    /**
     * 金豆兑换话费可兑换的份额列表
     */
    public static String EXCHANGE_BEAN = "EXCHANGE_BEAN";
}
