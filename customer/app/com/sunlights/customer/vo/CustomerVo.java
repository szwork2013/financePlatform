package com.sunlights.customer.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;


/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerInfoVo.java</p>
 * <p>Description: 返回前台vo</p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class CustomerVo implements Serializable {
  private String userName;//用户名
  private String nickName;
  private String mobilePhoneNo;//手机号
  private String mobileDisplayNo;//带*显示手机号
  private String email;//邮箱
  private String gestureOpened = "0";//手势开启/关闭  关闭0   开启 1
  private String certify = "0";//判断是否实名认证     false 0 true 1
  private String idCardNo;//身份证号  实名认证才返回，非实名认证返回null
  private String bankCardCount = "0";//绑定的银行卡数量
  private String tradePwdFlag = "0";//交易密码是否设置 0 未  1已

  @JsonIgnore
  private String customerId;

  public CustomerVo() {

  }

  public CustomerVo(String mobile, String userName, String nickName, String email, String idCardNo, String gestureOpened,
                    String certify, String tradePwdFlag, String bankCardCount, String customerId) {
    this.mobilePhoneNo = mobile;
    this.userName = userName;
    this.nickName = nickName;
    this.email = email;
    this.idCardNo = idCardNo;
    this.certify = certify;
    this.gestureOpened = gestureOpened;
    this.tradePwdFlag = tradePwdFlag;
    this.bankCardCount = bankCardCount;
    this.customerId = customerId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getMobilePhoneNo() {
    return mobilePhoneNo;
  }

  public void setMobilePhoneNo(String mobilePhoneNo) {
    this.mobilePhoneNo = mobilePhoneNo;
  }

  public String getMobileDisplayNo() {
    return mobileDisplayNo;
  }

  public void setMobileDisplayNo(String mobileDisplayNo) {
    this.mobileDisplayNo = mobileDisplayNo;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGestureOpened() {
    return gestureOpened;
  }

  public void setGestureOpened(String gestureOpened) {
    this.gestureOpened = gestureOpened;
  }

  public String getCertify() {
    return certify;
  }

  public void setCertify(String certify) {
    this.certify = certify;
  }

  public String getIdCardNo() {
    return idCardNo;
  }

  public void setIdCardNo(String idCardNo) {
    this.idCardNo = idCardNo;
  }

  public String getBankCardCount() {
    return bankCardCount;
  }

  public void setBankCardCount(String bankCardCount) {
    this.bankCardCount = bankCardCount;
  }

  public String getTradePwdFlag() {
    return tradePwdFlag;
  }

  public void setTradePwdFlag(String tradePwdFlag) {
    this.tradePwdFlag = tradePwdFlag;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
}
