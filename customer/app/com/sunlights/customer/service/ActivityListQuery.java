package com.sunlights.customer.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.vo.ActivityQueryContext;
import com.sunlights.customer.vo.ActivityVo;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/17.
 */
public interface ActivityListQuery {

    public List<ActivityVo> queryActivityList(ActivityQueryContext context);

}
