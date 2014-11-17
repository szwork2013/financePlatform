package com.sunlights.account.service;

import com.sunlights.account.vo.ActivityVo;
import com.sunlights.common.vo.PageVo;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/17.
 */
public interface ActivityService {

    public List<ActivityVo> getActivityVos(PageVo pageVo);

}
