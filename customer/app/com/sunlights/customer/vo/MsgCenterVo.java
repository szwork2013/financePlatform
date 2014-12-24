package com.sunlights.customer.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: MsgCenterVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class MsgCenterVo implements Serializable{
    private Long msgId;
    private Long messageRuleId;
    private String title;
    private String summary;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    private String sendType;
    private String readInd;


    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getReadInd() {
        return readInd;
    }

    public void setReadInd(String readInd) {
        this.readInd = readInd;
    }
}
