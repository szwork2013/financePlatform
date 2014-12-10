package com.sunlights.common.vo;

import java.io.Serializable;

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
    private Long pushMessageId;
    private String platform; //推送平台
    private String content;
    private String title;
    private Long timeToLive;
    private boolean apnsProducation;
    private Long bigPushDuration;
    private String pushGroupId;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(Long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public boolean getApnsProducation() {
        return apnsProducation;
    }

    public void setApnsProducation(boolean apnsProducation) {
        this.apnsProducation = apnsProducation;
    }

    public Long getBigPushDuration() {
        return bigPushDuration;
    }

    public void setBigPushDuration(Long bigPushDuration) {
        this.bigPushDuration = bigPushDuration;
    }

    public String getPushGroupId() {
        return pushGroupId;
    }

    public void setPushGroupId(String pushGroupId) {
        this.pushGroupId = pushGroupId;
    }

    public Long getPushMessageId() {
        return pushMessageId;
    }

    public void setPushMessageId(Long pushMessageId) {
        this.pushMessageId = pushMessageId;
    }
}
