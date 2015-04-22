package com.sunlights.op.service.activity;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.activity.ActivityVo;
import models.Activity;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/13.
 */
public interface ActivityService {

    public Activity add(ActivityVo vo);

    public List<ActivityVo> findActicities();

    public Activity modifyActivity(ActivityVo vo);

    public void deleteActivity(Long id);

    public List<ActivityVo> findActivityWithRule(PageVo pageVo);

    public String getH5Content(Long activityId);

    public void saveH5Content(Long activityId, String h5Content);

}
