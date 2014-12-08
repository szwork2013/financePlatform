package com.sunlights.customer.service.rewardrules.vo;

import models.Activity;
import models.ActivityScene;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by tangweiqun on 2014/12/1.
 */
public class ActivityRequestVo {

    private String custId;

    private String scene;

    private Long activityId;

    private String recommendCustId;

    private String rewardType;

    private ActivityScene activityScene;

    private List<Activity> activities;

    private Map<Long, List<ObtainRewardRuleVo>> obtainRewardRuleMap;

    private Map<String, Object> attributes;

    public ActivityRequestVo() {
        attributes = new HashMap<String, Object>();
    }

    public void set(String name,Object value){
        this.attributes.put(name, value);
    }


    public <T> T get(String name, Class<T> clazz) {
        return getDataInternal(name, clazz, attributes);
    }

    private <T> T getDataInternal(String name, Class<T> clazz,  Map<String, Object> resource) {
        Object obj = resource.get(name);
        if(obj == null) {
            return null;
        }
        return clazz.cast(obj);
    }

    public ActivityRequestVo copy() {

        ActivityRequestVo requestVo = new ActivityRequestVo();

        for(String key : this.attributes.keySet()){
            requestVo.set(key, this.attributes.get(key));
        }

        return requestVo;
    }
    public void recyle() {
        this.attributes = null;
    }


    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getRecommendCustId() {
        return recommendCustId;
    }

    public void setRecommendCustId(String recommendCustId) {
        this.recommendCustId = recommendCustId;
    }

    public ActivityScene getActivityScene() {
        return activityScene;
    }

    public void setActivityScene(ActivityScene activityScene) {
        this.activityScene = activityScene;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public Map<Long, List<ObtainRewardRuleVo>> getObtainRewardRuleMap() {
        return obtainRewardRuleMap;
    }

    public void setObtainRewardRuleMap(Map<Long, List<ObtainRewardRuleVo>> obtainRewardRuleMap) {
        this.obtainRewardRuleMap = obtainRewardRuleMap;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    @Override
    public String toString() {
        return "TransactionContext [dataStore=" + attributes + "]";
    }

}
