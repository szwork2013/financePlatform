package models;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: CustomerMsgPushTxn.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "c_customer_msg_push_txn")
public class CustomerMsgPushTxn extends IdEntity {
    @Column(name = "message_rule_id")
    private Long messageRuleId;
    private String title;
    private String content;
    @Column(name = "content_ext")
    private String contentExt;
    @Column(name = "push_status", length = 50)
    private String pushStatus;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PUSH_TIME")
    private Date pushTime;
    @Column(name = "send_no")
    private String sendNo;
    @Column(name = "return_msg_id")
    private String returnMsgId;
    @Column(name = "customer_id", length = 30)
    private String customerId;
    @Column(name = "send_num")
    private int sendNum;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public Long getMessageRuleId() {
        return messageRuleId;
    }

    public void setMessageRuleId(Long messageRuleId) {
        this.messageRuleId = messageRuleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentExt() {
        return contentExt;
    }

    public void setContentExt(String contentExt) {
        this.contentExt = contentExt;
    }

    public String getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(String pushStatus) {
        this.pushStatus = pushStatus;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public String getReturnMsgId() {
        return returnMsgId;
    }

    public void setReturnMsgId(String returnMsgId) {
        this.returnMsgId = returnMsgId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public int getSendNum() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }
}
