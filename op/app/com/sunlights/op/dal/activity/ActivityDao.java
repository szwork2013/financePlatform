package com.sunlights.op.dal.activity;

import com.sunlights.op.vo.activity.ActivityVo;
import models.Activity;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/13.
 */
public interface ActivityDao {

    public Activity add(Activity vo);

    public List<Activity> findActicities();

    public Activity modifyActivity(Activity vo);

    public boolean deleteActivity(Long id);

    public Activity findById(Long id);

    public List<ActivityVo> findActivityWithRule(Long id, String title, String type);

}
