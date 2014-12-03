package com.sunlights.customer.dal.impl;


import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.HoldRewardDao;
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
    public List<HoldReward> findByCustIdAndRewardType(String custId, String rewardType, String activityType) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("select h from HoldReward h where 1 =1")
                .append("/~  and h.custId  = {custId} ~/")
                .append("/~  and h.rewardType  = {rewardType} ~/")
                .append("/~  and h.activityType  = {activityType} ~/");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EQS_custId", custId);
        params.put("EQS_rewardType", rewardType);
        params.put("EQS_activityType", activityType);

        List<HoldReward> list = findByMap(jpql.toString(), params);

        return list;
    }

    @Override
    public HoldReward findByCustIdAndRewardType(String custId, String rewardType) {
        List<HoldReward> holdRewards = findByCustIdAndRewardType(custId, rewardType, null);

        if(holdRewards == null || holdRewards.isEmpty()) {
            return null;
        }
        HoldReward holdReward = new HoldReward();
        holdReward.setCustId(custId);
        holdReward.setRewardType(rewardType);
        for(HoldReward temp : holdRewards) {
            holdReward.setHoldReward(holdReward.getHoldReward() + temp.getHoldReward());
            holdReward.setHoldMoney(holdReward.getHoldMoney().add(temp.getHoldMoney()));

        }
        return holdReward;
    }
}
