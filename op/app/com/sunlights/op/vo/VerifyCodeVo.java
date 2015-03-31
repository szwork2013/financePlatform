package com.sunlights.op.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import models.CustomerVerifyCode;

import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>Project: op</p>
 * <p>Title: VerifyCodeVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VerifyCodeVo {
    private String mobile;//电话
    private String verifyCode; // 验证码
    private String verifyType;//验证码类型
    private Date createdDatetime;

    public VerifyCodeVo() {
        super();
    }

    public VerifyCodeVo(CustomerVerifyCode customerVerifyCode) {
        inCustomerVerifyCode(customerVerifyCode);
    }

    public void inCustomerVerifyCode(CustomerVerifyCode customerVerifyCode) {
        this.mobile = customerVerifyCode.getMobile();//电话
        this.verifyCode = customerVerifyCode.getVerifyCode(); // 验证码
        this.verifyType = customerVerifyCode.getVerifyType();//验证码类型
        this.createdDatetime = customerVerifyCode.getCreateTime();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
}
