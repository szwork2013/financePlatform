package com.sunlights.customer.service;


import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.vo.ActivityVo;
import models.Activity;
import models.ActivityShareInfo;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/17.
 */
public interface ActivityService {

    public List<ActivityVo> getActivityVos(PageVo pageVo);

    /**
     * 根据活动的应用场景查处这个场景下的所有活动
     * @param scene 应用场景
     * @return
     */
    public List<Activity> getActivityByScene(String scene);


    public List<String> getActivityTitles(String prdCode);
    public String getFileFuleUrl(String fileName, String remotDir);


    public ActivityShareInfo getShareInfoByScene(String scene);


}
