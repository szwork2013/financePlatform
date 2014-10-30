package com.sunlights.core.models;

import com.sunlights.common.dal.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <p>Project: tradingsystem</p>
 * <p>Title: SmsMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "sms_message")
public class SmsMessage extends IdEntity{
    @Column(length = 40)
    private String smsId;
    @Column(length = 11)
    private String mobile;
    @Column(length = 200)
    private String content;
    @Column(length = 40,name = "rec_status")
    private String recStatus;//回执状态
    @Column(length = 200,name = "return_msg")
    private String returnMsg;
    @Column(name = "created_datetime")
    private Timestamp createdDatetime;//创建事件
    @Column(name = "updated_datetime")
    private Timestamp updatedDatetime;//修改时间

    public SmsMessage() {
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Timestamp getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
}
