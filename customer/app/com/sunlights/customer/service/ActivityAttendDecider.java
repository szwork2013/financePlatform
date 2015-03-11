package com.sunlights.customer.service;

import models.CustJoinActivity;

import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/12/17.
 * 判断用户是否还可以参加某个场景的活动
 */
public interface ActivityAttendDecider {

    /**
     * 判断用户是否还可以参加某个场景的活动
     *
     * @param custId  客户号
     * @param listMap 客户参加过活动的记录  按照活动场景为key，活动参加记录为value
     * @return true表示还可以继续参加  false表示用户不能参加了
     */
    public boolean decide(String custId, Map<String, List<CustJoinActivity>> listMap);

}
