package com.sunlights.account.dal;

import com.sunlights.account.vo.ActivityVo;
import com.sunlights.common.vo.PageVo;
import models.Activity;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/17.
 */
public interface ActivityDao {

    public List<Activity> getActivityVos(PageVo pageVo);

}
