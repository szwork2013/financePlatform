package com.sunlights.op.dal.activity.impl;

import com.google.common.collect.Maps;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.activity.ActivityReturnMsgDao;
import com.sunlights.op.vo.activity.ActivityReturnMsgVo;
import models.ActivityReturnMsg;
import play.Logger;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/12/25.
 */
public class ActivityReturnMsgDaoImpl extends EntityBaseDao implements ActivityReturnMsgDao {

    @Override
    public List<ActivityReturnMsgVo> findByCondition(PageVo pageVo) {
        StringBuilder sb = new StringBuilder();
        String keys = "id,scene,type,rewardType,category,errorCode,template,createTime,rewardTypeStr ";
        String columns = " a.id,a.scene,a.type,a.reward_type, a.category,a.ERROR_CODE,a.template, a.CREATE_TIME,s.name ";
        sb.append("select ").append(columns)
                .append(" from F_ACTIVITY_RETURNCODE_MSG as a join F_REWARD_TYPE  s on a.REWARD_TYPE = s.code ");
        sb.append(" where 1 = 1 ");
        sb.append("  /~ and a.scene = {scene} ~/ ");
        sb.append("  /~ and a.type = {type} ~/ ");
        sb.append("  /~ and a.reward_type = {rewardType} ~/ ");
        sb.append(" order by a.CREATE_TIME desc ");

        Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(5);


        filterMap.put("EQS_scene", pageVo.get("scene"));
        filterMap.put("EQS_type", pageVo.get("activityType"));
        filterMap.put("EQS_rewardType", pageVo.get("rewardType"));

        List<Object[]> resultRows = createNativeQueryByMap(sb.toString(), filterMap).getResultList();
        List<ActivityReturnMsgVo> activityReturnMsgVos = ConverterUtil.convert(keys, resultRows, ActivityReturnMsgVo.class);


        Logger.debug("activityReturnMsgVos = " + activityReturnMsgVos.size());

        return activityReturnMsgVos;
    }

    @Override
    public ActivityReturnMsg doSave(ActivityReturnMsg activityReturnMsg) {
        activityReturnMsg.setCreateTime(new Date());
        return super.create(activityReturnMsg);
    }

    @Override
    public ActivityReturnMsg doUpdate(ActivityReturnMsg activityReturnMsg) {
        ActivityReturnMsg old = findById(activityReturnMsg.getId());
        if(old == null) {
            return activityReturnMsg;
        }
        activityReturnMsg.setCreateTime(old.getCreateTime());
        activityReturnMsg.setUpdateTime(new Date());
        return super.update(activityReturnMsg);
    }

    @Override
    public void deleteById(Long id) {
        ActivityReturnMsg old = findById(id);
        if(old == null) {
            return;
        }

        delete(old);
    }

    @Override
    public ActivityReturnMsg findById(Long id) {
        List<ActivityReturnMsg> activityReturnMsgs = super.findBy(ActivityReturnMsg.class, "id", id);
        if(activityReturnMsgs == null || activityReturnMsgs.isEmpty()) {
            return null;
        }
        return activityReturnMsgs.get(0);
    }
}
