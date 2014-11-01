package com.sunlights.customer.models;

import com.sunlights.common.AppConst;
import com.sunlights.common.models.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2014/9/18.
 */
@Entity
@Table(name = "c_customer_gesture")
public class CustomerGesture extends IdEntity {
    @Column(length = 30,name = "CUSTOMER_ID")
    private String customerId;
    @Column(length = 40,name = "DEVICE_NO")
    private String deviceNo;//设备号
    @Column(length = 40,name = "GESTURE_PASSWORD")
    private String gesturePassword;//手势密码
    @Column(name = "UPDATED_DATETIME")
    private Timestamp updatedDatetime;
    @Column(name = "CREATED_DATETIME")
    private Timestamp createdDatetime;
    @Column(name = "STATUS", length = 1)
    private String status = AppConst.VERIFY_CODE_STATUS_INVALID;

    public CustomerGesture() {
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
