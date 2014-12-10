package com.sunlights.customer.dal.impl;


import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.HoldRewardDao;
import models.HoldReward;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class HoldRewardDaoImpl extends EntityBaseDao implements HoldRewardDao {

    @Override
    public void doInsert(HoldReward holdReward) {
        holdReward.setCreateTime(new Date());
        create(holdReward);
    }

    @Override
    public void doUpdate(HoldReward holdReward) {
        holdReward.setUpdateTime(new Date());
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
        holdReward.setFrozenMoney(BigDecimal.ZERO);
        holdReward.setFrozenReward(0L);
        holdReward.setGetMoney(BigDecimal.ZERO);
        holdReward.setGetReward(0L);
        holdReward.setHoldMoney(BigDecimal.ZERO);
        holdReward.setHoldReward(0L);
        holdReward.setCustId(custId);
        holdReward.setRewardType(rewardType);
        for(HoldReward temp : holdRewards) {
            holdReward.setFrozenReward(temp.getFrozenReward() + holdReward.getFrozenReward());
            holdReward.setFrozenMoney(holdReward.getFrozenMoney().add(temp.getFrozenMoney()));
            holdReward.setHoldReward(holdReward.getHoldReward() + temp.getHoldReward());
            holdReward.setHoldMoney(holdReward.getHoldMoney().add(temp.getHoldMoney()));
            holdReward.setGetReward(holdReward.getGetReward() + temp.getGetReward());
            holdReward.setGetMoney(holdReward.getGetMoney().add(temp.getGetMoney()));
        }
        return holdReward;
    }

    @Override
    public List<HoldReward> findListByCustIdAndRewardType(String custId, String rewardType) {
        return findByCustIdAndRewardType(custId, rewardType, null);
    }

    @Override
    public HoldReward findByCondition(String custId, String rewardType, String activityType) {
        List<HoldReward> holdRewards = findByCustIdAndRewardType(custId, rewardType, activityType);
        if(holdRewards == null || holdRewards.isEmpty()) {
            return null;
        }
        return holdRewards.get(0);
    }
}
