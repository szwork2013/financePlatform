package com.sunlights.common;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2014/9/10.
 */
public final class AppConst {

  /**
   * 验证码在单位时间内最大次数
   */
  public static final String VERIFYCODE_MAX = "VERIFYCODE_MAX";
  /**
   * 验证码最大次数的单位时间(min)
   */
  public static final String VERIFYCODE_TIMES = "VERIFYCODE_TIMES";
  /**
   * 验证码时效时间(min)
   */
  public static final String VERIFYCODE_EXPIRY = "VERIFYCODE_EXPIRY";
  /**
   * 密码允许错误最大次数
   */
  public static final String PWD_MAX = "PWD_MAX";
  /**
   * 登录失败没到最大次数，隔XXX时间后失败次数清0的时间(min)
   */
  public static final String LOGIN_PERIOD = "LOGIN_PERIOD";
  /**
   * 用户解锁时间(min)
   */
  public static final String USERUNLOCK_PERIOD = "USERUNLOCK_PERIOD";
  /**
   * 超出密码错误次数，首次暂时锁定的时间(min)
   */
  public static final String RELIEVE_SUSLOCK_PERIOD = "RELIEVE_SUSLOCK_PERIOD";
  /**
   * 缓存存在时间(min)
   */
  public static final String CACHE_EXPIRY = "CACHE_EXPIRY";
  /**
   * cookie存在时间(min)
   */
  public static final String COOKIE_EXPIRY = "COOKIE_EXPIRY";
  /**
   * 后台CustomerSession有效时间(min)，一般与COOKIE_EXPIRY存在时间一致
   */
  public static final String SESSION_EXPIRY = "SESSION_EXPIRY";
  /**
   * 实名认证-N真实调用/非N 测试模式
   */
  public static final String CERTIFY_TEST = "CERTIFY_TEST";
  /**
   * 实名认证-URL地址
   */
  public static final String CERTIFY_URL = "CERTIFY_URL";
  /**
   * 实名认证-帐号用户名
   */
  public static final String CERTIFY_USERNAME = "CERTIFY_USERNAME";
  /**
   * 实名认证-帐号密码
   */
  public static final String CERTIFY_PASSWORD = "CERTIFY_PASSWORD";
  /**
   * 短信接口-URL地址
   */
  public static final String SMS_URL = "SMS_URL";
  /**
   * 短信接口-第二办公室门牌号码
   */
  public static final String SMS_ACCOUNT = "SMS_ACCOUNT";
  /**
   * 短信接口-授权码
   */
  public static final String SMS_WARRANTYCODE = "SMS_WARRANTYCODE";
  /**
   * 短信接口-密码
   */
  public static final String SMS_PASSWORD = "SMS_PASSWORD";
  /**
   * 短信接口-通道编号
   */
  public static final String SMS_CHANNEL = "SMS_CHANNEL";
  /**
   * 短信接口-N真实调用/非N 测试模式
   */
  public static final String SMS_TEST = "SMS_TEST";

  private AppConst() {
  }

  //---- core ---
  public static String VERIFY_CODE_REGISTER = "REGISTER";
  public static String VERIFY_CODE_RESETPWD = "RESETPWD";
  public static String VERIFY_CODE_RESET_ACCOUNT = "RESETACCOUNT";

  public static Set<String> VALID_VERIFY_CODES = new HashSet<>();

  static {
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
