package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.RewardAccountDao;
import models.RewardAccountBalance;

import java.util.Date;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: RewardAccountBalanceServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class RewardAccountDaoImpl extends EntityBaseDao implements RewardAccountDao {
    @Override
    public RewardAccountBalance createRewardAccount(RewardAccountBalance rewardAccountBalance) {
        return create(rewardAccountBalance);
    }

    @Override
    public RewardAccountBalance updateRewardAccount(RewardAccountBalance rewardAccountBalance) {
        rewardAccountBalance.setUpdateTime(new Date());
        return update(rewardAccountBalance);
    }

    @Override
    public RewardAccountBalance findRewardAccountByCustomerId(String customerId) {
        List<RewardAccountBalance> list = findBy(RewardAccountBalance.class, "customerId", customerId);
        return list.isEmpty() ? new RewardAccountBalance() : list.get(0);
    }
}
