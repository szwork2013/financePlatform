package com.sunlights.customer.vo;

import java.io.Serializable;

/**
 * <p>Project: fsp</p>
 * <p>Title: CustomerInfoVo.java</p>
 * <p>Description: 后台数据传递Vo</p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class CustomerInfoVo implements Serializable {
    private CustomerVo customerVo;
    private String customerId;

    public CustomerInfoVo(){

    }

    public CustomerInfoVo(CustomerVo customerVo, String customerId) {
        this.customerVo = customerVo;
        this.customerId = customerId;
    }
    public CustomerInfoVo(String mobile, String userName, String nickName, String email, String idCardNo, String gestureOpened, String certify, String tradePwdFlag, String bankCardCount, String customerId) {
        CustomerVo vo = new CustomerVo(mobile,userName, nickName,email,idCardNo,gestureOpened,certify,tradePwdFlag,bankCardCount);
        this.customerVo = vo;
        this.customerId = customerId;
    }

    public CustomerVo getCustomerVo() {
        return customerVo;
    }

    public void setCustomerVo(CustomerVo customerVo) {
        this.customerVo = customerVo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserName() {
        return customerVo.getUserName();
    }

    public String getGestureOpened() {
        return customerVo.getGestureOpened();
    }

    public void setBankCardCount(String bankCardCount) {
        customerVo.setBankCardCount(bankCardCount);
    }

    public String getTradePwdFlag() {
        return customerVo.getTradePwdFlag();
    }

    public void setCertify(String certify) {
        customerVo.setCertify(certify);
    }

    public void setNickName(String nickName) {
        customerVo.setNickName(nickName);
    }

    public void setIdCardNo(String idCardNo) {
        customerVo.setIdCardNo(idCardNo);
    }

    public String getEmail() {
        return customerVo.getEmail();
    }

    public void setGestureOpened(String gestureOpened) {
        customerVo.setGestureOpened(gestureOpened);
    }

    public String getIdCardNo() {
        return customerVo.getIdCardNo();
    }

    public void setMobilePhoneNo(String mobilePhoneNo) {
        customerVo.setMobilePhoneNo(mobilePhoneNo);
    }

    public String getNickName() {
        return customerVo.getNickName();
    }

    public String getMobileDisplayNo() {
        return customerVo.getMobileDisplayNo();
    }

    public String getBankCardCount() {
        return customerVo.getBankCardCount();
    }

    public void setUserName(String userName) {
        customerVo.setUserName(userName);
    }

    public void setTradePwdFlag(String tradePwdFlag) {
        customerVo.setTradePwdFlag(tradePwdFlag);
    }

    public void setMobileDisplayNo(String mobileDisplayNo) {
        customerVo.setMobileDisplayNo(mobileDisplayNo);
    }

    public String getMobilePhoneNo() {
        return customerVo.getMobilePhoneNo();
    }

    public void setEmail(String email) {
        customerVo.setEmail(email);
    }

    public String getCertify() {
        return customerVo.getCertify();
    }
}
