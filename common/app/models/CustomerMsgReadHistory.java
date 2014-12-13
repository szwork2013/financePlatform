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
    @Column(name = "customer_msg_push_txn_id")
    private Long customerMsgPushTxnId;
    @Column(name = "message_push_txn_id")
    private Long messagePushTxnId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "read_time")
    private Date readTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getMessagePushTxnId() {
        return messagePushTxnId;
    }

    public void setMessagePushTxnId(Long messagePushTxnId) {
        this.messagePushTxnId = messagePushTxnId;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCustomerMsgPushTxnId() {
        return customerMsgPushTxnId;
    }

    public void setCustomerMsgPushTxnId(Long customerMsgPushTxnId) {
        this.customerMsgPushTxnId = customerMsgPushTxnId;
    }
}
