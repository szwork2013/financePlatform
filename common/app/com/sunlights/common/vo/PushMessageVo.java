package com.sunlights.common.vo;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: PushMessageVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class PushMessageVo implements Serializable{
    private Long pushTxnId;
    private String title;
    private String content;
    private String contentExt;
    private String sendNo;
    private String platform; //推送平台
    private Long groupId;
    private String customerId;
    private String personalInd;//个人、群发 信息来源标志
    private int sendNum = 0;//次数
    private List<String> aliasList = Lists.newArrayList();

    private Long messageRuleId;
    private String pushTimed;//Y定时 N即时
    private String pushInd;
    private String smsInd;
    private String msgCenterInd;

    public Long getPushTxnId() {
        return pushTxnId;
    }

    public void setPushTxnId(Long pushTxnId) {
        this.pushTxnId = pushTxnId;
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

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<String> getAliasList() {
        return aliasList;
    }

    public void setAliasList(List<String> aliasList) {
        this.aliasList = aliasList;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPersonalInd() {
        return personalInd;
    }

    public void setPersonalInd(String personalInd) {
        this.personalInd = personalInd;
    }

    public int getSendNum() {
        return sendNum;
    }

    public void setSendNum(int sendNum) {
        this.sendNum = sendNum;
    }

    public String getPushTimed() {
        return pushTimed;
    }

    public void setPushTimed(String pushTimed) {
        this.pushTimed = pushTimed;
    }

    public String getPushInd() {
        return pushInd;
    }

    public void setPushInd(String pushInd) {
        this.pushInd = pushInd;
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

    public Long getMessageRuleId() {
        return messageRuleId;
    }

    public void setMessageRuleId(Long messageRuleId) {
        this.messageRuleId = messageRuleId;
    }

    public void setMsgCenterInd(String msgCenterInd) {
        this.msgCenterInd = msgCenterInd;
    }
}
