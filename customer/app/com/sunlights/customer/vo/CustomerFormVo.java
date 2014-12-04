package com.sunlights.customer.vo;

import java.io.Serializable;

public class CustomerFormVo implements Serializable {
  private String mobilePhoneNo;//手机号
  private String deviceNo;//设备号
  private String passWord;
  private String nickName;
  private String userName;//真实姓名
  private String idCardNo;//身份证号
  private String verifyCode;//验证码
  private String type;//验证码类型

  private String gesturePassWord;//手势密码
  private String gestureOpened;//手势开启/关闭

  private String referralMobile;//推荐人手机号

  public CustomerFormVo() {

  }

  public String getMobilePhoneNo() {
    return mobilePhoneNo;
  }

  public void setMobilePhoneNo(String mobilePhoneNo) {
    this.mobilePhoneNo = mobilePhoneNo;
  }

  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getIdCardNo() {
    return idCardNo;
  }

  public void setIdCardNo(String idCardNo) {
    this.idCardNo = idCardNo;
  }

  public String getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(String verifyCode) {
    this.verifyCode = verifyCode;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getGesturePassWord() {
    return gesturePassWord;
  }

  public void setGesturePassWord(String gesturePassWord) {
    this.gesturePassWord = gesturePassWord;
  }

  public String getGestureOpened() {
    return gestureOpened;
  }

  public void setGestureOpened(String gestureOpened) {
    this.gestureOpened = gestureOpened;
  }

    public String getReferralMobile() {
        return referralMobile;
    }

    public void setReferralMobile(String referralMobile) {
        this.referralMobile = referralMobile;
    }
}
