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
public class PushMessageVo implements Serializable {
    private Long pushTxnId;
    private String title;
    private String content;//消息中心内容
    private String contentSms;//短信内容
    private String contentPush;//推送内容
    private String contentExt;
    private String sendNo;
    private String platform; //推送配置中的推送平台
    private String customerPlatform; //该客户此条信息的推送平台
    private Long groupId;
    private String customerId;
    private String personalInd;//个人Y、群发N 信息来源标志
    private int sendNum = 0;//次数
    private int badge = 1;//ios 专用  未读记录数量
    private List<String> registrationIdList = Lists.newArrayList();

    private Long messageRuleId;
    private String pushTimed;//Y定时 N即时
    private String pushInd;//推送标志Y是N否
    private String smsInd;//短信标志
    private String msgCenterInd;//消息中心展示标志

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

    public List<String> getRegistrationIdList() {
        return registrationIdList;
    }

    public void setRegistrationIdList(List<String> registrationIdList) {
        this.registrationIdList = registrationIdList;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
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

    public String getCustomerPlatform() {
        return customerPlatform;
    }

    public void setCustomerPlatform(String customerPlatform) {
        this.customerPlatform = customerPlatform;
    }

}
