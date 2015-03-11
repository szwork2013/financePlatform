package com.sunlights.customer.dal.impl;


import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.RewardTypeDao;
import models.RewardType;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class RewardTypeDaoImpl extends EntityBaseDao implements RewardTypeDao {

    @Override
    public RewardType findByTypeCode(String code) {
        List<RewardType> rewardTypes = findBy(RewardType.class, "code", code);
        if (rewardTypes != null && !rewardTypes.isEmpty()) {
            return rewardTypes.get(0);
        }
        return null;
    }
}
