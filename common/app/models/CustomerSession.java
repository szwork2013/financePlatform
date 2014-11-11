package models;

import com.sunlights.common.AppConst;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2014/9/10.
 */
@Entity
@Table(name = "C_CUSTOMER_SESSION")
public class CustomerSession extends IdEntity {
    @Column(length = 30,name = "CUSTOMER_ID")
    private String customerId;
    @Column(length = 400)
    private String token;
    @Column(length = 40,name = "DEVICE_NO")
    private String deviceNo; // 设备号
    @Column(length = 40,name = "DEVICE_NAME")
    private String deviceName; // 设备名称
    @Column(length = 40,name = "CLIENT_ADDRESS")
    private String clientAddress; // 客户端IP地址
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Column(name = "STATUS", length = 1)
    private String status = AppConst.STATUS_VALID;

    public CustomerSession() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }
}
