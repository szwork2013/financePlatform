package com.sunlights.op.vo.activity;

import com.sunlights.common.MsgCode;
import models.ActivityReturnMsg;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by tangweiqun on 2014/12/24.
 */
public class ActivityReturnMsgVo extends ActivityReturnMsg {
    private String sceneStr;
    private String activityTypeStr;
    private String rewardTypeStr;

    private String categoryStr;
    private String errorMessage;

    public String getSceneStr() {
        return SceneType.getDescByType(getScene());
    }

    public void setSceneStr(String sceneStr) {
        this.sceneStr = sceneStr;
    }

    public String getActivityTypeStr() {
        String desc = ActivityType.getDescByType(getType());
        if(StringUtils.isEmpty(desc)) {
            desc = ExchangeType.getDescByType(getType());
        }
        return desc;
    }

    public void setActivityTypeStr(String activityTypeStr) {
        this.activityTypeStr = activityTypeStr;
    }

    public String getRewardTypeStr() {
        return rewardTypeStr;
    }

    public void setRewardTypeStr(String rewardTypeStr) {
        this.rewardTypeStr = rewardTypeStr;
    }

    public String getCategoryStr() {
        return ReturnMsgCatogory.getDescByType(getCategory());
    }

    public void setCategoryStr(String categoryStr) {
        this.categoryStr = categoryStr;
    }

    public String getErrorMessage() {
        return MsgCode.getDescByCode(getErrorCode());
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
