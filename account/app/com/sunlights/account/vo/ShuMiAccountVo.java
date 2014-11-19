package com.sunlights.account.vo;

import java.io.Serializable;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ShuMiAccountVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class ShuMiAccountVo implements Serializable{
    private String shumi_tokenKey;
    private String shumi_tokenSecret;
    private String shumi_userName;
    private String shumi_realName;
    private String shumi_idNumber;
    private String shumi_bankName;
    private String shumi_bankCardNo;
    private String shumi_bankSerial;
    private String shumi_phoneNum;
    private String shumi_email;

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
}
