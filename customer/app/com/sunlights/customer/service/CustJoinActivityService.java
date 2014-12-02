package com.sunlights.customer.service;

import models.CustJoinActivity;

/**
 * 客户与活动关联的服务
 *
 * Created by tangweiqun on 2014/12/2.
 */
public interface CustJoinActivityService {

    public CustJoinActivity getByCustAndActivity(String custId, Long activityId, String scene);

    public String getShortUrl(String custId, Long activity);

    public void saveShortUrl(String custId, Long activityId, String shortUrl);

}
