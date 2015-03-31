package com.sunlights.op.vo;

import models.Customer;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by yuan on 9/24/14.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerVo {
    private Long id;//id
    private String customerId;//用户编号
    private String nickName;//用户名
    private String userName;//用户名真实姓名
    private String passWord;//密码
    private String mobilePhoneNo;//手机号
    private String email;//邮箱
    private String idCardNo;//身份证号
    private boolean locked;//锁定标识

    private String balance;//余额
    private String redPacketAmount;//红包余额
    private String goldenBeanCount;//金豆剩余数

    public CustomerVo() {
        super();
    }

    public CustomerVo(Customer customer) {
        inCustomer(customer);
    }

    public void inCustomer(Customer customer) {
        this.id = customer.getId();
		this.customerId = customer.getCustomerId();
        this.nickName = customer.getNickName();
        this.userName = customer.getRealName();
        this.passWord = customer.getLoginPassWord();
        this.mobilePhoneNo = customer.getMobile();
        this.email = customer.getEmail();
        this.idCardNo = customer.getIdentityNumber();
        this.locked = "U".equals(customer.getStatus());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMobilePhoneNo() {
        return mobilePhoneNo;
    }

    public void setMobilePhoneNo(String mobilePhoneNo) {
        this.mobilePhoneNo = mobilePhoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

	public String getBalance () {
		return balance;
	}

	public void setBalance (String balance) {
		this.balance = balance;
	}

	public String getRedPacketAmount () {
		return redPacketAmount;
	}

	public void setRedPacketAmount (String redPacketAmount) {
		this.redPacketAmount = redPacketAmount;
	}

	public String getGoldenBeanCount () {
		return goldenBeanCount;
	}

	public void setGoldenBeanCount (String goldenBeanCount) {
		this.goldenBeanCount = goldenBeanCount;
	}

	public String getCustomerId () {
		return customerId;
	}

	public void setCustomerId (String customerId) {
		this.customerId = customerId;
	}
}
