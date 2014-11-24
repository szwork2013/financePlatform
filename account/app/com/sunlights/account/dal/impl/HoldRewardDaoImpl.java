package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.HoldRewardDao;
import com.sunlights.common.dal.EntityBaseDao;
import models.HoldReward;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class HoldRewardDaoImpl extends EntityBaseDao implements HoldRewardDao {

    @Override
    public void doInsert(HoldReward holdReward) {
        create(holdReward);
    }

    @Override
    public void doUpdate(HoldReward holdReward) {
        update(holdReward);
    }

    @Override
    public HoldReward findByCustIdAndRewardType(String custId, String rewardType) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select h from HoldReward h where 1 =1")
                .append("/~  and h.custId  = {custId} ~/")
                .append("/~  and h.rewardType  = {rewardType} ~/");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EQS_custId", custId);
        params.put("EQS_rewardType", rewardType);

        List<HoldReward> list = findByMap(jpql.toString(), params);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
