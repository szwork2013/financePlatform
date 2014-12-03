package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.CustJoinActivityDao;
import models.CustJoinActivity;
import models.HoldReward;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public class CustJoinActivityDaoImpl extends EntityBaseDao implements CustJoinActivityDao {

    @Override
    public void doInsert(CustJoinActivity custJoinActivity) {
        super.create(custJoinActivity);
    }

    @Override
    public List<CustJoinActivity> queryByCondition(CustJoinActivity custJoinActivity) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select c from CustJoinActivity c where 1 =1")
                .append("/~  and c.custId  = {custId} ~/")
                .append("/~  and c.activityId  = {activityId} ~/")
                .append("/~  and c.scene  = {scene} ~/");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EQS_custId", custJoinActivity.getCustId());
        params.put("EQL_activityId", custJoinActivity.getActivityId());
        params.put("EQS_scene", custJoinActivity.getScene());

        List<CustJoinActivity> list = findByMap(jpql.toString(), params);
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }
}
