package com.sunlights.account.vo;

/**
 * Created by tangweiqun on 2014/11/13.
 */
public class ActivityParamter {
    /**
     * 活动主键
     */
    private Long activityId;
    /**
     * 活动场景
     */
    private String scene;

    private int index;
    private int pageSize;


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}
