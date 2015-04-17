package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.MessagePushConfig;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2014/12/14.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessagePushConfigVo implements Serializable{
    private Long id ;
    private String remarks;
    private String platform;
    private String platformDes;
    private String pushType;
    private String pushTypeDes;
    private Date planBeginTime;
    private Date planEndTime;
    private String pushTimedInd;
    private String status;

    public MessagePushConfigVo() {
        super();
    }

    public MessagePushConfigVo(MessagePushConfig message) {
        inMessageRuleConfig(message);
    }


    public void inMessageRuleConfig(MessagePushConfig message) {
        this.id = message.getId();
        this.remarks = message.getRemarks();
        this.platform = message.getPlatform();
        this.pushType = message.getPushType();
        this.pushTimedInd = message.getPushTimed();
        this.status = message.getStatus();
        this.planBeginTime = message.getPlanBeginTime();
        this.planEndTime = message.getPlanEndTime();
    }

    public MessagePushConfig convertToMessageRuleConfig() {
        MessagePushConfig messRule = new MessagePushConfig();
        messRule.setId(this.id);
        messRule.setRemarks(this.remarks);
        messRule.setPlatform(this.platform);
        messRule.setPushType(this.pushType);
        messRule.setPushTimed(this.pushTimedInd);
        messRule.setStatus(this.status);
        messRule.setPlanBeginTime(this.planBeginTime);
        messRule.setPlanEndTime(this.planEndTime);

        return messRule;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public Date getPlanBeginTime() {
        return planBeginTime;
    }

    public void setPlanBeginTime(Date planBeginTime) {
        this.planBeginTime = planBeginTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getPushTimedInd() {
        return pushTimedInd;
    }

    public void setPushTimedInd(String pushTimedInd) {
        this.pushTimedInd = pushTimedInd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlatformDes() {
        return platformDes;
    }

    public void setPlatformDes(String platformDes) {
        this.platformDes = platformDes;
    }

    public String getPushTypeDes() {
        return pushTypeDes;
    }

    public void setPushTypeDes(String pushTypeDes) {
        this.pushTypeDes = pushTypeDes;
    }
}
