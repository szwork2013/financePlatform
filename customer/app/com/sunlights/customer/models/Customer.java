package com.sunlights.customer.models;

import com.sunlights.common.dal.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <p>Project: fsp</p>
 * <p>Title: Customer.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "c_customer")
public class Customer extends IdEntity {
    @Column(length = 30, name = "customer_id")
    private String customerId;

    @Column(length = 20, name = "login_id")
    private String loginId;

    @Column(length = 20, name = "nick_name")
    private String nickName;

    @Column(length = 40, name = "login_password")
    private String loginPassWord;
    @Column(length = 1, name = "reg_channel")
    private String regChannel;// 注册渠道 M	手机渠道 W	互联网渠道
    @Column(length = 1, name = "reg_way")
    private String regWay;//注册方式 M.手机 E.邮箱
    @Column(length = 20, name = "real_name")
    private String realName;//真实姓名
    @Column(length = 1, name = "identity_typer")
    private String identityTyper;//证件类型 I	居民身份证
    @Column(length = 30, name = "identity_number")
    private String identityNumber;//证件号码
    @Column(length = 40, name = "pic_way")
    private String picWay;//用户图像存放路径
    @Column(length = 1, name = "customer_type")
    private String customerType;//客户类型B.商户   C.个人  一期默认为C类
    @Column(length = 1, name = "property")
    private String property;//用户属性1.买家  2. 卖家  一期默认为买家
    @Column(length = 50, name = "device_no")
    private String deviceNo;//注册设备号
    @Column(length = 10, name = "referral_code")
    private String referralCode;//推荐码
    @Column(length = 50, name = "email")
    private String email;//绑定邮箱
    @Column(length = 20, name = "qq")
    private String qq;//绑定QQ
    @Column(length = 30, name = "weixin")
    private String weixin;//  绑定微信号
    @Column(length = 30, name = "weibo")
    private String weibo;//绑定微博号
    @Column(length = 11, name = "mobile")
    private String mobile;// 绑定手机
    @Column(length = 1, name = "status")
    private String status;//用户状态 T.正常 U.已锁定
    @Column(name = "CREATED_DATETIME")
    private Timestamp createdDatetime;
    @Column(name = "CREATED_BY", length = 30)
    private String createdBy;
    @Column(name = "UPDATED_DATETIME")
    private Timestamp updatedDatetime;
    @Column(name = "UPDATED_BY", length = 30)
    private String updatedBy;

    public Customer() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginPassWord() {
        return loginPassWord;
    }

    public void setLoginPassWord(String loginPassWord) {
        this.loginPassWord = loginPassWord;
    }

    public String getRegChannel() {
        return regChannel;
    }

    public void setRegChannel(String regChannel) {
        this.regChannel = regChannel;
    }

    public String getRegWay() {
        return regWay;
    }

    public void setRegWay(String regWay) {
        this.regWay = regWay;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdentityTyper() {
        return identityTyper;
    }

    public void setIdentityTyper(String identityTyper) {
        this.identityTyper = identityTyper;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getPicWay() {
        return picWay;
    }

    public void setPicWay(String picWay) {
        this.picWay = picWay;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
