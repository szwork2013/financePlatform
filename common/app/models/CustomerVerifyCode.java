package models;

import com.sunlights.common.AppConst;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2014/9/4.
 */
@Entity
@Table(name = "C_CUSTOMER_VERIFY_CODE")
public class CustomerVerifyCode extends IdEntity {
    @Column(length = 40,name = "VERIFY_CODE")
    private String verifyCode; // 验证码
    @Column(length = 11,name = "MOBILE")
    private String mobile;//电话
    @Column(length = 40,name = "DEVICE_NO")
    private String deviceNo;//设备号
    @Column(length = 20,name = "VERIFY_TYPE")
    private String verifyType;//验证码类型
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "STATUS", length = 1)
    private String status = AppConst.STATUS_VALID;

    public CustomerVerifyCode() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
    }
}
