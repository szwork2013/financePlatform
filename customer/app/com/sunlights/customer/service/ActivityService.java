package com.sunlights.customer.service;


import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.vo.Activity4H5Vo;
import com.sunlights.customer.vo.ActivityVo;
import models.Activity;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/17.
 */
public interface ActivityService {

    public List<Activity> getAllActivities();

    public List<ActivityVo> getActivityVos(PageVo pageVo);

    /**
     * 根据活动的应用场景查处这个场景下的所有活动
     *
     * @param scene 应用场景
     * @return
     */
    public List<Activity> getActivityByScene(String scene);


    public List<String> getActivityTitles(String prdCode);

    public String getFileFuleUrl(String fileName, String remotDir);


    public Activity getByUnknowCondition(String condition);


    public List<Activity> getCurrentValidActivities();

    public Activity4H5Vo getH5InfoById(Long id);

    /**
     * 1验证客户是否已做过首次申购   2 验证该首次申购的活动是否未结束
     *
     * @param customerId
     * @param activityId
     * @return
     */
    public boolean validateHasFirstPurchase(String customerId, Long activityId);

    /**
     * 对该活动进行 剩余数量计数
     *
     * @param id
     * @return null 无数量限制 0无剩余数
     */
    public Integer countActivityRemain(Long id);


    /**
     * 验证活动是否结束
     *
     * @param id
     * @return
     */
    public boolean validateActivityIsOver(Long id);


}
