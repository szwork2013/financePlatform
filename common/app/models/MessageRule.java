package models;

import javax.persistence.*;
import java.util.Date;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: MessageRule.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "c_message_rule")
public class MessageRule extends IdEntity {

    private String name;
    private String code;
    private String description;
    private String title;
    private String content;
    @Column(name = "content_ext")
    private String contentExt;
    @Column(name = "sms_ind", length = 1)
    private String smsInd;
    @Column(name = "msg_center_ind", length = 1)
    private String msgCenterInd;
    @Column(name = "push_ind", length = 1)
    private String pushInd;
    @Column(name = "message_push_config_id", length = 1)
    private Long messagePushConfigId;
    @Column(length = 1)
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getSmsInd() {
        return smsInd;
    }

    public void setSmsInd(String smsInd) {
        this.smsInd = smsInd;
    }

    public String getMsgCenterInd() {
        return msgCenterInd;
    }

    public void setMsgCenterInd(String msgCenterInd) {
        this.msgCenterInd = msgCenterInd;
    }

    public String getPushInd() {
        return pushInd;
    }

    public void setPushInd(String pushInd) {
        this.pushInd = pushInd;
    }

    public Long getMessagePushConfigId() {
        return messagePushConfigId;
    }

    public void setMessagePushConfigId(Long messagePushConfigId) {
        this.messagePushConfigId = messagePushConfigId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
