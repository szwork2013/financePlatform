package models;

import com.sunlights.common.AppConst;

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

    @Column(name = "content_sms")
    private String contentSms;
    @Column(name = "content_push")
    private String contentPush;

    @Column(name = "group_id")
    private Long groupId;//每次推送时，此次要推送到的群发组。空值表示推送全部人
    @Column(name = "sms_ind", length = 1)
    private String smsInd = AppConst.STATUS_INVALID;
    @Column(name = "msg_center_ind", length = 1)
    private String msgCenterInd = AppConst.STATUS_INVALID;
    @Column(name = "push_ind", length = 1)
    private String pushInd ;
    @Column(name = "message_push_config_id")
    private Long messagePushConfigId;
    @Column(length = 1)
    private String status = AppConst.STATUS_VALID;
    @Column(name = "stay_day_ind", length = 1)
    private String stayDayInd;//信息是否保存30天
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getContentSms() {
        return contentSms;
    }

    public void setContentSms(String contentSms) {
        this.contentSms = contentSms;
    }

    public String getContentPush() {
        return contentPush;
    }

    public void setContentPush(String contentPush) {
        this.contentPush = contentPush;
    }

    public String getStayDayInd() {
        return stayDayInd;
    }

    public void setStayDayInd(String stayDayInd) {
        this.stayDayInd = stayDayInd;
    }
}
