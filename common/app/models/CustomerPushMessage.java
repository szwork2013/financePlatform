package models;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: CustomerPushMsg.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "c_customer_push_message")
public class CustomerPushMessage extends IdEntity {
    @Column(name = "customer_id", length = 30)
    private String customerId;
    @Column(name = "read_ind", length = 1)
    private String readInd;
    @Column(name = "push_message_id")
    private Long pushMessageId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "read_time")
    private Date readTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getReadInd() {
        return readInd;
    }

    public void setReadInd(String readInd) {
        this.readInd = readInd;
    }

    public Long getPushMessageId() {
        return pushMessageId;
    }

    public void setPushMessageId(Long pushMessageId) {
        this.pushMessageId = pushMessageId;
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
}
