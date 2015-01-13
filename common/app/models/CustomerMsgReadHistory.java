package models;


import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: MessageReadHistory.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "c_customer_msg_read_history")
public class CustomerMsgReadHistory extends IdEntity {
    @Column(name = "customer_id", length = 30)
    private String customerId;
    @Column(name = "push_txn_id")
    private Long pushTxnId;
    @Column(name = "device_no", length = 40)
    private String deviceNo;
    @Column(name = "send_type")
    private String sendType;//"FP.SEND.TYPE.1";//短信发送   "FP.SEND.TYPE.2";//群发推送  "FP.SEND.TYPE.3";//个人推送
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "read_time")
    private Date readTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getPushTxnId() {
        return pushTxnId;
    }

    public void setPushTxnId(Long pushTxnId) {
        this.pushTxnId = pushTxnId;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
