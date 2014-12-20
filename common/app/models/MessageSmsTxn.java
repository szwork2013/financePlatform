package models;

import com.sunlights.common.AppConst;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: MessageSmsTxn.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "c_message_sms_txn")
public class MessageSmsTxn extends IdEntity {
    @Column(name = "message_rule_id")
    private Long messageRuleId;
    @Column(length = 30, name = "customer_id")
    private String customerId;
    @Column(length = 40)
    private String smsId;
    @Column(length = 11)
    private String mobile;
    private String title;
    private String content;
    @Column(length = 40, name = "rec_status")
    private String recStatus;//回执状态
    @Column(length = 200, name = "return_msg")
    private String returnMsg;
    @Column(length = 1,name = "success_ind")
    private String successInd = AppConst.STATUS_INVALID;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;//创建事件
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;//修改时间


    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getMessageRuleId() {
        return messageRuleId;
    }

    public void setMessageRuleId(Long messageRuleId) {
        this.messageRuleId = messageRuleId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getSuccessInd() {
        return successInd;
    }

    public void setSuccessInd(String successInd) {
        this.successInd = successInd;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
