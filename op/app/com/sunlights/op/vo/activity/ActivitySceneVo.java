package com.sunlights.op.vo.activity;

import com.sunlights.common.service.CommonService;
import models.ActivityScene;

import java.util.Date;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: ActivitySceneVo.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class ActivitySceneVo {
    private Long id;
    private String scene;
    private String title;
    private String status;
    private String activityType;
    private String activityTypeDesc;
    private String prdCode;
    private String prdType;
    private String prdTypeDesc;
    private Date createTime;
    private Date updateTime;

    public ActivitySceneVo() {
        super();
    }

    public ActivitySceneVo(ActivityScene activityScene) {
        inActivityScene(activityScene);
    }

    public void inActivityScene(ActivityScene activityScene) {
        this.id = activityScene.getId();
        this.setScene(activityScene.getScene());
        this.setTitle(activityScene.getTitle());
        this.setStatus(activityScene.getStatus());
        this.setActivityType(activityScene.getActivityType());
        this.setActivityTypeDesc(ActivityType.getDescByType(this.getActivityType()));
        this.setPrdCode(activityScene.getPrdCode());
        this.setPrdType(activityScene.getPrdType());
        this.setPrdTypeDesc(new CommonService().findValueByCatPointKey(this.prdType));
        this.setCreateTime(activityScene.getCreateTime());
        this.setUpdateTime(activityScene.getUpdateTime());
    }

    public ActivityScene convertToActivityScene() {
        ActivityScene activityScene = new ActivityScene();
        activityScene.setId(this.getId());
        activityScene.setScene(this.getScene());
        activityScene.setTitle(this.getTitle());
        activityScene.setStatus(this.getStatus());
        activityScene.setActivityType(this.getActivityType());
        activityScene.setPrdCode(this.getPrdCode());
        activityScene.setPrdType(this.getPrdType());
        activityScene.setCreateTime(this.getCreateTime());
        activityScene.setUpdateTime(this.getUpdateTime());
        return activityScene;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getPrdCode() {
        return prdCode;
    }

    public void setPrdCode(String prdCode) {
        this.prdCode = prdCode;
    }

    public String getPrdType() {
        return prdType;
    }

    public void setPrdType(String prdType) {
        this.prdType = prdType;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrdTypeDesc() {
        return prdTypeDesc;
    }

    public void setPrdTypeDesc(String prdTypeDesc) {
        this.prdTypeDesc = prdTypeDesc;
    }

    public String getActivityTypeDesc() {
        return activityTypeDesc;
    }

    public void setActivityTypeDesc(String activityTypeDesc) {
        this.activityTypeDesc = activityTypeDesc;
    }
}
