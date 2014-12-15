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
    private String gestureSetted = "0";//是否曾经设置过手势 0表示从未设置过，1表示曾设置过

    @JsonIgnore
    private String customerId;


    //数米数据
    private String shumi_tokenKey;//
    private String shumi_tokenSecret;//
    private String shumi_userName;
    private String shumi_realName;//
    private String shumi_idNumber;//
    private String shumi_bankName;
    private String shumi_bankCardNo;
    private String shumi_bankSerial;
    private String shumi_phoneNum;//
    private String shumi_email;





    public CustomerVo() {

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

    public String getShumi_tokenKey() {
        return shumi_tokenKey;
    }

    public void setShumi_tokenKey(String shumi_tokenKey) {
        this.shumi_tokenKey = shumi_tokenKey;
    }

    public String getShumi_tokenSecret() {
        return shumi_tokenSecret;
    }

    public void setShumi_tokenSecret(String shumi_tokenSecret) {
        this.shumi_tokenSecret = shumi_tokenSecret;
    }

    public String getShumi_userName() {
        return shumi_userName;
    }

    public void setShumi_userName(String shumi_userName) {
        this.shumi_userName = shumi_userName;
    }

    public String getShumi_realName() {
        return shumi_realName;
    }

    public void setShumi_realName(String shumi_realName) {
        this.shumi_realName = shumi_realName;
    }

    public String getShumi_idNumber() {
        return shumi_idNumber;
    }

    public void setShumi_idNumber(String shumi_idNumber) {
        this.shumi_idNumber = shumi_idNumber;
    }

    public String getShumi_bankName() {
        return shumi_bankName;
    }

    public void setShumi_bankName(String shumi_bankName) {
        this.shumi_bankName = shumi_bankName;
    }

    public String getShumi_bankCardNo() {
        return shumi_bankCardNo;
    }

    public void setShumi_bankCardNo(String shumi_bankCardNo) {
        this.shumi_bankCardNo = shumi_bankCardNo;
    }

    public String getShumi_bankSerial() {
        return shumi_bankSerial;
    }

    public void setShumi_bankSerial(String shumi_bankSerial) {
        this.shumi_bankSerial = shumi_bankSerial;
    }

    public String getShumi_phoneNum() {
        return shumi_phoneNum;
    }

    public void setShumi_phoneNum(String shumi_phoneNum) {
        this.shumi_phoneNum = shumi_phoneNum;
    }

    public String getShumi_email() {
        return shumi_email;
    }

    public void setShumi_email(String shumi_email) {
        this.shumi_email = shumi_email;
    }

    public String getGestureSetted() {
        return gestureSetted;
    }

    public void setGestureSetted(String gestureSetted) {
        this.gestureSetted = gestureSetted;
    }
}
