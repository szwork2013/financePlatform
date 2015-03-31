package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunlights.common.utils.CommonUtil;
import models.MessageRule;

import java.text.ParseException;

/**
 * Created by Administrator on 2014/12/14.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageRuleVo {
    private Long id ;
    private String name;
    private String code;


    private Long messageTxnId;
    private String description;
    private String title;
    private String content;
    private String contentSms;
    private String contentPush;

    private String contentext;
    private String smsind;
    private String msgcenterind;
    private String pushind;
    private Long messagePushConfigId;
    private String configremarks;
    private Long groupid;
    private String groupname;
    private String status;
    private String createtime;
    private String updatetime;
    private String stayDayInd;

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
        this.contentext=message.getContentExt();
        this.smsind=message.getSmsInd();
        this.msgcenterind=message.getMsgCenterInd();
        this.pushind=message.getPushInd();
        this.messagePushConfigId=message.getMessagePushConfigId();
        this.groupid=message.getGroupId();
        this.status = message.getStatus();
        this.stayDayInd = message.getStayDayInd();
        this.createtime = CommonUtil.dateToString(message.getCreateTime(), "yyyy-MM-dd");
        this.updatetime = CommonUtil.dateToString(message.getUpdateTime(), "yyyy-MM-dd");
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
    messRule.setContentExt(this.contentext);
    messRule.setSmsInd(this.smsind);
    messRule.setMsgCenterInd(this.msgcenterind);
    messRule.setPushInd(this.pushind);
    messRule.setMessagePushConfigId(this.messagePushConfigId);
    messRule.setGroupId(this.groupid);
    messRule.setStatus(this.status);
    messRule.setStayDayInd(this.stayDayInd);
    try {
        messRule.setCreateTime(CommonUtil.stringToDate(this.createtime, "yyyy-MM-dd"));
        messRule.setUpdateTime(CommonUtil.stringToDate(this.updatetime, "yyyy-MM-dd"));
    } catch (ParseException e) {
        e.printStackTrace();
    }

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

    public String getContentext() {
        return contentext;
    }

    public void setContentext(String contentext) {
        this.contentext = contentext;
    }

    public String getSmsind() {
        return smsind;
    }

    public void setSmsind(String smsind) {
        this.smsind = smsind;
    }

    public String getMsgcenterind() {
        return msgcenterind;
    }

    public void setMsgcenterind(String msgcenterind) {
        this.msgcenterind = msgcenterind;
    }

    public String getPushind() {
        return pushind;
    }

    public void setPushind(String pushind) {
        this.pushind = pushind;
    }

    public Long getMessagePushConfigId() {
        return messagePushConfigId;
    }

    public void setMessagePushConfigId(Long messagePushConfigId) {
        this.messagePushConfigId = messagePushConfigId;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public Long getMessageTxnId() {
        return messageTxnId;
    }

    public void setMessageTxnId(Long messageTxnId) {
        this.messageTxnId = messageTxnId;
    }

    public String getConfigremarks() {
        return configremarks;
    }

    public void setConfigremarks(String configremarks) {
        this.configremarks = configremarks;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
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
