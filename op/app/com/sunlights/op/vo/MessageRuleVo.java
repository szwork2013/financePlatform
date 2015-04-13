package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.MessageRule;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2014/12/14.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageRuleVo implements Serializable{
    private Long id ;
    private String name;
    private String code;

    private Long messageTxnId;
    private String description;
    private String title;
    private String content;
    private String contentSms;
    private String contentPush;

    private String contentExt;
    private String smsInd;
    private String msgCenterInd;
    private String pushInd;
    private Long messagePushConfigId;
    private String configRemarks;
    private Long groupId;
    private String groupName;
    private String status;
    private String stayDayInd;
    
    private Date createTime;
    private Date updateTime;

    public MessageRuleVo() {
        super();
    }

    public MessageRuleVo(MessageRule message) {
        inMessageRule(message);
    }


    public void inMessageRule(MessageRule message) {
        this.id = message.getId();
        this.name=message.getName();
        this.code=message.getCode();
        this.description=message.getDescription();
        this.title=message.getTitle();
        this.content=message.getContent();
        this.contentSms = message.getContentSms();
        this.contentPush = message.getContentPush();
        this.contentExt =message.getContentExt();
        this.smsInd =message.getSmsInd();
        this.msgCenterInd =message.getMsgCenterInd();
        this.pushInd =message.getPushInd();
        this.messagePushConfigId=message.getMessagePushConfigId();
        this.groupId =message.getGroupId();
        this.status = message.getStatus();
        this.stayDayInd = message.getStayDayInd();
        this.createTime = message.getCreateTime();
        this.updateTime = message.getUpdateTime();
    }

public MessageRule convertToMessageRule() {
    MessageRule messRule = new MessageRule();
    messRule.setId(this.id);
    messRule.setName(this.name);
    messRule.setCode(this.code);
    messRule.setDescription(this.description);
    messRule.setTitle(this.title);
    messRule.setContent(this.content);
    messRule.setContentSms(this.contentSms);
    messRule.setContentPush(this.contentPush);
    messRule.setContentExt(this.contentExt);
    messRule.setSmsInd(this.smsInd);
    messRule.setMsgCenterInd(this.msgCenterInd);
    messRule.setPushInd(this.pushInd);
    messRule.setMessagePushConfigId(this.messagePushConfigId);
    messRule.setGroupId(this.groupId);
    messRule.setStatus(this.status);
    messRule.setStayDayInd(this.stayDayInd);
    messRule.setCreateTime(this.createTime);
    messRule.setUpdateTime(this.updateTime);

    return messRule;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMessageTxnId() {
        return messageTxnId;
    }

    public void setMessageTxnId(Long messageTxnId) {
        this.messageTxnId = messageTxnId;
    }

    public String getConfigRemarks() {
        return configRemarks;
    }

    public void setConfigRemarks(String configRemarks) {
        this.configRemarks = configRemarks;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
