package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sunlights.common.utils.CommonUtil;
import models.MessagePushConfig;

import java.text.ParseException;

/**
 * Created by Administrator on 2014/12/14.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessagePushConfigVo {
    private Long id ;
    private String remarks;
    private String platform;
    private String platformdes;
    private String pushtype;
    private String pushtypedes;
    private String planbegintime;
    private String planendtime;
    private String pushtimed;
    private String status;
    private String createtime;
    private String updatetime;

    public MessagePushConfigVo() {
        super();
    }

    public MessagePushConfigVo(MessagePushConfig message) {
        inMessageRuleConfig(message);
    }


    public void inMessageRuleConfig(MessagePushConfig message) {
        this.id = message.getId();
        this.remarks=message.getRemarks();
        this.platform=message.getPlatform();
        this.pushtype=message.getPushType();
        this.pushtimed=message.getPushTimed();
        this.status = message.getStatus();
        this.planbegintime = CommonUtil.dateToString(message.getPlanBeginTime(), "yyyy-MM-dd");
        this.planendtime = CommonUtil.dateToString(message.getPlanEndTime(), "yyyy-MM-dd");
        this.createtime = CommonUtil.dateToString(message.getCreateTime(), "yyyy-MM-dd");
        this.updatetime = CommonUtil.dateToString(message.getUpdateTime(), "yyyy-MM-dd");
    }

    public MessagePushConfig convertToMessageRuleConfig() {
        MessagePushConfig messRule = new MessagePushConfig();
        messRule.setId(this.id);
        messRule.setRemarks(this.remarks);
        messRule.setPlatform(this.platform);
        messRule.setPushType(this.pushtype);
        messRule.setPushTimed(this.pushtimed);
        messRule.setStatus(this.status);
        try {
            messRule.setPlanBeginTime(CommonUtil.stringToDate(this.planbegintime, "yyyy-MM-dd"));
            messRule.setPlanEndTime(CommonUtil.stringToDate(this.planendtime, "yyyy-MM-dd"));
            messRule.setCreateTime(CommonUtil.stringToDate(this.createtime, "yyyy-MM-dd"));
            messRule.setUpdateTime(CommonUtil.stringToDate(this.updatetime, "yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

    public String getPushtype() {
        return pushtype;
    }

    public void setPushtype(String pushtype) {
        this.pushtype = pushtype;
    }

    public String getPlanbegintime() {
        return planbegintime;
    }

    public void setPlanbegintime(String planbegintime) {
        this.planbegintime = planbegintime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanendtime() {
        return planendtime;
    }

    public void setPlanendtime(String planendtime) {
        this.planendtime = planendtime;
    }

    public String getPushtimed() {
        return pushtimed;
    }

    public void setPushtimed(String pushtimed) {
        this.pushtimed = pushtimed;
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
    public String getPlatformdes() {
        return platformdes;
    }

    public void setPlatformdes(String platformdes) {
        this.platformdes = platformdes;
    }

    public String getPushtypedes() {
        return pushtypedes;
    }

    public void setPushtypedes(String pushtypedes) {
        this.pushtypedes = pushtypedes;
    }
}
