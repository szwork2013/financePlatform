package com.sunlights.account.vo;

/**
 * Created by Administrator on 2014/11/13.
 */
public class ActivityParamter {
    private Long activityId;

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
}
