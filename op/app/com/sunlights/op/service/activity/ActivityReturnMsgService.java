package com.sunlights.op.service.activity;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.activity.ActivityReturnMsgVo;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/25.
 */
public interface ActivityReturnMsgService {

    public List<ActivityReturnMsgVo> findByCondition(PageVo pageVo);

    public ActivityReturnMsgVo save(ActivityReturnMsgVo activityReturnMsgVo);

    public ActivityReturnMsgVo modify(ActivityReturnMsgVo activityReturnMsgVo);

    public void remove(Long id);

}
