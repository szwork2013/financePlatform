package com.sunlights.op.vo.activity;

import com.sunlights.common.utils.CommonUtil;
import models.Activity;
import play.Logger;

import java.text.ParseException;

/**
 * Created by tangweiqun on 2014/11/12.
 */
public class ActivityVo {

    private Long id;

    private Long ruleId;

    private String title;

    private String beginTime;

    private String endTime;

    private Long appId;

    private String activitySourceId;

    private Long clickTime;

    private String type;

    private String h5Content;



    private String status;

    private String image;

    private String url;

    private String rewardType;

    private String rewardTypeName;

    private String realReward;

    private String shouldReward;

    private String effectTime;

    private String validTime;

    private String shareText;

    private String shareUrl;

    private String shareTitle;

    private String iconUrl;

    private String clickEvent;

    private String scene;

    private String sceneName;

    private String ruleStatus;

    public ActivityVo() {

    }

    public ActivityVo(Activity activity) {
        this.id = activity.getId();
        this.title = activity.getTitle();
        this.beginTime = CommonUtil.dateToString(activity.getBeginTime(), "yyyy-MM-dd");
        this.endTime = CommonUtil.dateToString(activity.getEndTime(), "yyyy-MM-dd");
        this.appId = activity.getAppId();
        this.url = activity.getUrl();
        this.image = activity.getImage();
        this.clickTime = activity.getClickTime();

        this.status = activity.getStatus();
        this.clickEvent = activity.getClickEvent();
        this.scene = activity.getScene();

        //this.shareText = activity.getShareText();
        //this.shareUrl = activity.getShareUrl();
    }

    public Activity convert2Model(ActivityVo vo) {
        Activity activity = new Activity();
        activity.setId(vo.getId());
        activity.setTitle(vo.getTitle());
        activity.setUrl(vo.getUrl());
        activity.setImage(vo.getImage());
        activity.setAppId(vo.getAppId());
        try {
            activity.setBeginTime(CommonUtil.stringToDate(vo.getBeginTime(), "yyyy-MM-dd"));
            activity.setEndTime(CommonUtil.stringToDate(vo.getEndTime(), "yyyy-MM-dd"));
        } catch (ParseException e) {
            Logger.error("set time error", e);
        }

        activity.setClickTime(vo.getClickTime());

        activity.setStatus(vo.getStatus());
        activity.setClickEvent(vo.getClickEvent());
        activity.setScene(vo.getScene());

        //activity.setShareText(vo.getShareText());
        //activity.setShareUrl(vo.getShareUrl());

        return activity;
    }

    public String getH5Content() {
        return h5Content;
    }

    public void setH5Content(String h5Content) {
        this.h5Content = h5Content;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getRewardTypeName() {
        return rewardTypeName;
    }

    public void setRewardTypeName(String rewardTypeName) {
        this.rewardTypeName = rewardTypeName;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String trimStr(String str) {
        return str == null ? null : str.trim();
    }

    public String getClickEvent() {
        return clickEvent;
    }

    public void setClickEvent(String clickEvent) {
        this.clickEvent = clickEvent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getActivitySourceId() {
        return activitySourceId;
    }

    public void setActivitySourceId(String activitySourceId) {
        this.activitySourceId = activitySourceId;
    }

    public Long getClickTime() {
        return clickTime;
    }

    public void setClickTime(Long clickTime) {
        this.clickTime = clickTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public String getShouldReward() {
        return shouldReward;
    }

    public void setShouldReward(String shouldReward) {
        this.shouldReward = shouldReward;
    }



    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }



    public String getRealReward() {
        return realReward;
    }

    public void setRealReward(String realReward) {
        this.realReward = realReward;
    }

    public String getEffectTime() {
        return effectTime;
    }

    public void setEffectTime(String effectTime) {
        this.effectTime = effectTime;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }


    public String getRuleStatus() {
        return ruleStatus;
    }

    public void setRuleStatus(String ruleStatus) {
        this.ruleStatus = ruleStatus;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }
}
