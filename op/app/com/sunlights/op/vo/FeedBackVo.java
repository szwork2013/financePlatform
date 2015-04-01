package com.sunlights.op.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import models.FeedBack;

import java.util.Date;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: FeedBackVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FeedBackVo {
    private Long id;
    private String customerId;
    private String context;
    private String mobile;
    private String remark;
    private String status;
    private String deviceNo;
    private Date createTime;
    private Date updateTime;
    private String updateBy;

    public FeedBackVo() {
        super();
    }

    public FeedBackVo(FeedBack feedBack) {
        this.id = feedBack.getId();
        this.customerId = feedBack.getCustomerId();
        this.context = feedBack.getContext();
        this.mobile = feedBack.getMobile();
        this.remark = feedBack.getRemark();
        this.status = feedBack.getStatus();
        this.deviceNo = feedBack.getDeviceNo();
        this.createTime = feedBack.getCreateTime();
        this.updateTime = feedBack.getUpdateTime();
        this.updateBy = feedBack.getUpdateBy();
    }

    public FeedBack convertToFeedback() {
        FeedBack feedBack = new FeedBack();
        feedBack.setId(this.id);
        feedBack.setCustomerId(this.customerId);
        feedBack.setContext(this.context);
        feedBack.setMobile(this.mobile);
        feedBack.setRemark(this.remark);
        feedBack.setStatus(this.status);
        feedBack.setDeviceNo(this.deviceNo);
        feedBack.setCreateTime(this.createTime);
        feedBack.setUpdateTime(this.updateTime);
        feedBack.setUpdateBy(this.updateBy);
        return feedBack;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
