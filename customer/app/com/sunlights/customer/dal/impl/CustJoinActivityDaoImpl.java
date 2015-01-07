package com.sunlights.customer.dal.impl;

import com.google.common.collect.Lists;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.customer.dal.CustJoinActivityDao;
import models.CustJoinActivity;
import models.HoldReward;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public class CustJoinActivityDaoImpl extends EntityBaseDao implements CustJoinActivityDao {

    @Override
    public void doInsert(CustJoinActivity custJoinActivity) {
        custJoinActivity.setCreateTime(new Date());
        super.create(custJoinActivity);
    }

    @Override
    public List<CustJoinActivity> queryByCondition(CustJoinActivity custJoinActivity) {
        return queryByCondition(custJoinActivity, null, null);
    }

    @Override
    public List<CustJoinActivity> queryByCondition(CustJoinActivity custJoinActivity, String startDate, String endDate) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select c from CustJoinActivity c where 1 =1")
                .append("/~  and c.custId  = {custId} ~/")
                .append("/~  and c.activityId  = {activityId} ~/")
                .append("/~  and c.scene  = {scene} ~/")
                .append("/~  and c.joinTime  >= {startDate} ~/")
                .append("/~  and c.joinTime  <= {endDate} ~/");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EQS_custId", custJoinActivity.getCustId());
        params.put("EQL_activityId", custJoinActivity.getActivityId());
        params.put("EQS_scene", custJoinActivity.getScene());
        try {
            params.put("GED_startDate", startDate == null ? null : CommonUtil.stringToDate(startDate, CommonUtil.DATE_FORMAT_LONG));
            params.put("LED_endDate", endDate == null ? null : CommonUtil.stringToDate(endDate, CommonUtil.DATE_FORMAT_LONG));
        } catch (Exception e) {

        }
        List<CustJoinActivity> list = findByMap(jpql.toString(), params);
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }

        return list;
    }
}
