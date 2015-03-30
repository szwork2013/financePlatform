package com.sunlights.op.service.activity.impl;

import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.activity.ActivityReturnMsgDao;
import com.sunlights.op.dal.activity.impl.ActivityReturnMsgDaoImpl;
import com.sunlights.op.service.activity.ActivityReturnMsgService;
import com.sunlights.op.vo.activity.ActivityReturnMsgVo;
import models.ActivityReturnMsg;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/25.
 */
public class ActivityReturnMsgServiceImpl implements ActivityReturnMsgService {

    private ActivityReturnMsgDao activityReturnMsgDao = new ActivityReturnMsgDaoImpl();

    @Override
    public List<ActivityReturnMsgVo> findByCondition(PageVo pageVo) {

        return activityReturnMsgDao.findByCondition(pageVo);
    }

    @Override
    public ActivityReturnMsgVo save(ActivityReturnMsgVo activityReturnMsgVo) {
        try {
            ActivityReturnMsg activityReturnMsg = ConverterUtil.toEntity(new ActivityReturnMsg(), activityReturnMsgVo);
            activityReturnMsgDao.doSave(activityReturnMsg);
            activityReturnMsgVo.setId(activityReturnMsg.getId());
            activityReturnMsgVo.setCreateTime(activityReturnMsg.getCreateTime());
            return activityReturnMsgVo;
        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public void remove(Long id) {
        activityReturnMsgDao.deleteById(id);
    }

    @Override
    public ActivityReturnMsgVo modify(ActivityReturnMsgVo activityReturnMsgVo) {
        try {
            ActivityReturnMsg activityReturnMsg = ConverterUtil.toEntity(new ActivityReturnMsg(), activityReturnMsgVo);
            activityReturnMsgDao.doUpdate(activityReturnMsg);
            activityReturnMsgVo.setUpdateTime(activityReturnMsg.getUpdateTime());
            return activityReturnMsgVo;
        } catch (Exception e) {

        }
        return null;
    }
}
