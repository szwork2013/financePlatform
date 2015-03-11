package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ActivityReturnMsgDao;
import models.ActivityReturnMsg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/12/23.
 */
public class ActivityReturnMsgDaoImpl extends EntityBaseDao implements ActivityReturnMsgDao {

    @Override
    public ActivityReturnMsg getByCondition(ActivityReturnMsg activityReturnMsg) {
        List<ActivityReturnMsg> list = getList(activityReturnMsg);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ActivityReturnMsg> getList(ActivityReturnMsg activityReturnMsg) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select h from ActivityReturnMsg h where 1 =1")
                .append("/~  and h.scene  = {scene} ~/")
                .append("/~  and h.type  = {type} ~/")
                .append("/~  and h.rewardType  = {rewardType} ~/")
                .append("/~  and h.category  = {category} ~/")
                .append("/~  and h.errorCode  = {errorCode} ~/");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EQS_scene", activityReturnMsg.getScene());
        params.put("EQS_type", activityReturnMsg.getType());
        params.put("EQS_rewardType", activityReturnMsg.getRewardType());
        params.put("EQS_category", activityReturnMsg.getCategory());
        params.put("EQS_errorCode", activityReturnMsg.getErrorCode());

        List<ActivityReturnMsg> list = findByMap(jpql.toString(), params);
        return list;
    }
}
