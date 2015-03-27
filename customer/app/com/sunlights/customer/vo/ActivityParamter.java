package com.sunlights.customer.vo;

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

    private String filter;

    private String fundCode;

    private String supplySum;

    private String tradeType;

    private String rewardType;

    private String mobilePhoneNo;

    private int index;
    private int pageSize;

    public String getMobilePhoneNo() {
        return mobilePhoneNo;
    }

    public void setMobilePhoneNo(String mobilePhoneNo) {
        this.mobilePhoneNo = mobilePhoneNo;
    }

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

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getSupplySum() {
        return supplySum;
    }

    public void setSupplySum(String supplySum) {
        this.supplySum = supplySum;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
