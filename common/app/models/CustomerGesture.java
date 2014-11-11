package models;

import com.sunlights.common.AppConst;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2014/9/18.
 */
@Entity
@Table(name = "C_CUSTOMER_GESTURE")
public class CustomerGesture extends IdEntity {
    @Column(length = 30,name = "CUSTOMER_ID")
    private String customerId;
    @Column(length = 40,name = "DEVICE_NO")
    private String deviceNo;//设备号
    @Column(length = 40,name = "GESTURE_PASSWORD")
    private String gesturePassword;//手势密码
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "STATUS", length = 1)
    private String status = AppConst.STATUS_VALID;

    public CustomerGesture() {
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

    public String getGesturePassword() {
        return gesturePassword;
    }

    public void setGesturePassword(String gesturePassword) {
        this.gesturePassword = gesturePassword;
    }
}
