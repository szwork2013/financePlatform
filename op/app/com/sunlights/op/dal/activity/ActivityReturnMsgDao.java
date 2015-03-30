package com.sunlights.op.dal.activity;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.activity.ActivityReturnMsgVo;
import models.ActivityReturnMsg;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/25.
 */
public interface ActivityReturnMsgDao {

    public List<ActivityReturnMsgVo> findByCondition(PageVo pageVo);

    public ActivityReturnMsg doUpdate(ActivityReturnMsg activityReturnMsg);

    public ActivityReturnMsg doSave(ActivityReturnMsg activityReturnMsg);

    public void deleteById(Long id);

    public ActivityReturnMsg findById(Long id);
}
