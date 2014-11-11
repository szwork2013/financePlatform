package models;

import com.sunlights.common.AppConst;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2014/9/5.
 */
@Entity
@Table(name = "C_LOGIN_HISTORY")
public class LoginHistory extends IdEntity{
    @Column(length = 30,name = "CUSTOMER_ID")
    private String customerId;
    @Column(length = 40,name = "DEVICE_NO")
    private String deviceNo;//设备号
    @Column(length = 1,name = "SUCCESS_IND")
    private String successInd = AppConst.STATUS_VALID;
    @Column(length = 1,name = "PWD_IND")
    private String pwdInd = AppConst.STATUS_INVALID;
    @Column(length = 1,name = "GESTURE_IND")
    private String gestureInd = AppConst.STATUS_INVALID;
    @Column(length = 1,name = "SOCIAL_IND")
    private String socialInd = AppConst.STATUS_INVALID;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOGIN_TIME")
    private Date loginTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOGOUT_TIME")
    private Date logoutTime;
    @Column(length = 3,name = "LOG_NUM")
    private long logNum = 0;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    public LoginHistory() {
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }

    public long getLogNum() {
        return logNum;
    }

    public void setLogNum(long logNum) {
        this.logNum = logNum;
    }
}
