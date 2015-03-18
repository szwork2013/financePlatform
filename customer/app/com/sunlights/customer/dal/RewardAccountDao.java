package com.sunlights.customer.dal;

import models.RewardAccountBalance;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: RewardAccountBalanceDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface RewardAccountDao {

    public RewardAccountBalance createRewardAccount(RewardAccountBalance rewardAccountBalance);

    public RewardAccountBalance updateRewardAccount(RewardAccountBalance rewardAccountBalance);

    public RewardAccountBalance findRewardAccountByCustomerId(String customerId);
}
