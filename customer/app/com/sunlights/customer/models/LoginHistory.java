package com.sunlights.customer.models;

import com.sunlights.common.AppConst;
import com.sunlights.common.models.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2014/9/5.
 */
@Entity
@Table(name = "login_history")
public class LoginHistory extends IdEntity{
    @Column(length = 30,name = "CUSTOMER_ID")
    private String customerId;
    @Column(length = 40,name = "DEVICE_NO")
    private String deviceNo;//设备号
    @Column(length = 1,name = "SUCCESS_IND")
    private String successInd = AppConst.VERIFY_CODE_STATUS_INVALID;
    @Column(length = 1,name = "PWD_IND")
    private String pwdInd = AppConst.VERIFY_CODE_STATUS_INVALID;
    @Column(length = 1,name = "GESTURE_IND")
    private String gestureInd = AppConst.VERIFY_CODE_STATUS_INVALID;
    @Column(length = 1,name = "SOCIAL_IND")
    private String socialInd = AppConst.VERIFY_CODE_STATUS_INVALID;
    @Column(name = "LOGIN_DATETIME")
    private Timestamp loginDatetime;
    @Column(name = "LOGOUT_DATETIME")
    private Timestamp logoutDatetime;
    @Column(length = 3,name = "LOG_NUM")
    private long logNum = 0;
    @Column(name = "UPDATED_DATETIME")
    private Timestamp updatedDatetime;
    @Column(name = "CREATED_DATETIME")
    private Timestamp createdDatetime;
    public LoginHistory() {
    }

    public Timestamp getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getSuccessInd() {
        return successInd;
    }

    public void setSuccessInd(String successInd) {
        this.successInd = successInd;
    }

    public String getPwdInd() {
        return pwdInd;
    }

    public void setPwdInd(String pwdInd) {
        this.pwdInd = pwdInd;
    }

    public String getGestureInd() {
        return gestureInd;
    }

    public void setGestureInd(String gestureInd) {
        this.gestureInd = gestureInd;
    }

    public String getSocialInd() {
        return socialInd;
    }

    public void setSocialInd(String socialInd) {
        this.socialInd = socialInd;
    }

    public Timestamp getLoginDatetime() {
        return loginDatetime;
    }

    public void setLoginDatetime(Timestamp loginDatetime) {
        this.loginDatetime = loginDatetime;
    }

    public Timestamp getLogoutDatetime() {
        return logoutDatetime;
    }

    public void setLogoutDatetime(Timestamp logoutDatetime) {
        this.logoutDatetime = logoutDatetime;
    }

    public long getLogNum() {
        return logNum;
    }

    public void setLogNum(long logNum) {
        this.logNum = logNum;
    }
}
