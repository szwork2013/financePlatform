package com.sunlights.customer.dal.impl;


import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
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
    public List<HoldReward> findByCustIdAndRewardType(String custId, String rewardType, String activityType, boolean isLock) {
        StringBuilder sb = new StringBuilder();
        String keys = "id,custId,activityType,rewardType,getReward,frozenReward,holdReward,getMoney,holdMoney,frozenMoney,createTime ";
        String columns = " a.id,  a.customer_Id,a.ACTIVITY_TYPE,a.REWARD_TYPE, a.GET_AMOUNT,a.FROZEN_REWARD,a.HOLD_REWARD,a.GET_MONEY,a.HOLD_MONEY, a.FROZEN_MONEY, a.CREATE_TIME ";
        sb.append("select ").append(columns)
                .append("from F_REWARD_COUNT a ");
        sb.append(" where 1 = 1 ");
        sb.append("/~  and a.customer_Id  = {custId} ~/");
        sb.append("/~  and a.REWARD_TYPE  = {rewardType} ~/ ");
        sb.append("/~  and a.ACTIVITY_TYPE  = {activityType} ~/");
        if (isLock) {
            sb.append(" for update ");
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EQS_custId", custId);
        params.put("EQS_rewardType", rewardType);
        params.put("EQS_activityType", activityType);

        List<Object[]> resultRows = createNativeQueryByMap(sb.toString(), params).getResultList();
        List<HoldReward> holdRewards = ConverterUtil.convert(keys, resultRows, HoldReward.class);

        return holdRewards;
    }

    @Override
    public HoldReward findByCustIdAndRewardType(String custId, String rewardType) {
        List<HoldReward> holdRewards = findByCustIdAndRewardType(custId, rewardType, null, false);
        HoldReward holdReward = new HoldReward();
        holdReward.setFrozenMoney(BigDecimal.ZERO);
        holdReward.setFrozenReward(0L);
        holdReward.setGetMoney(BigDecimal.ZERO);
        holdReward.setGetReward(0L);
        holdReward.setHoldMoney(BigDecimal.ZERO);
        holdReward.setHoldReward(0L);
        holdReward.setCustId(custId);
        holdReward.setRewardType(rewardType);
        if (holdRewards == null || holdRewards.isEmpty()) {
            return holdReward;
        }

        for (HoldReward temp : holdRewards) {
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
    public List<HoldReward> findListByCustIdAndRewardType(String custId, String rewardType, boolean isLock) {
        return findByCustIdAndRewardType(custId, rewardType, null, isLock);
    }

    @Override
    public HoldReward findByCondition(String custId, String rewardType, String activityType, boolean isLock) {
        List<HoldReward> holdRewards = findByCustIdAndRewardType(custId, rewardType, activityType, isLock);
        if (holdRewards == null || holdRewards.isEmpty()) {
            return null;
        }
        return holdRewards.get(0);
    }
}
