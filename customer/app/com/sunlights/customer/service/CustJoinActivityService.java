package com.sunlights.customer.service;

import models.CustJoinActivity;

import java.util.List;
import java.util.Map;

/**
 * 客户与活动关联的服务
 *
 * Created by tangweiqun on 2014/12/2.
 */
public interface CustJoinActivityService {

    public CustJoinActivity getByCustAndActivity(String custId, Long activityId, String scene);

    public CustJoinActivity getTodayRecordByCustAndActivity(String custId, Long activityId, String scene);

    public void saveCustJoinActivity(CustJoinActivity custJoinActivity);

    public String getShortUrl(String custId, Long activityId, String scene);

    public void saveShortUrl(String custId, Long activityId, String scene, String shortUrl);

    public Map<String, List<CustJoinActivity>> mapWithScene(String custNo);

}
