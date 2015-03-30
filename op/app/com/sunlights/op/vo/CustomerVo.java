package com.sunlights.op.vo;


import models.Customer;

/**
 * Created by yuan on 9/24/14.
 */
public class CustomerVo {
    private Long id;//id
    private String nickName;//用户名
    private String userName;//用户名真实姓名
    private String passWord;//密码
    private String mobilePhoneNo;//手机号
    private String email;//邮箱
    private String idCardNo;//身份证号
    private boolean locked;//锁定标识


    public CustomerVo() {
        super();
    }

    public CustomerVo(Customer customer) {
        inCustomer(customer);
    }

    public void inCustomer(Customer customer) {
        this.id = customer.getId();
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
}
