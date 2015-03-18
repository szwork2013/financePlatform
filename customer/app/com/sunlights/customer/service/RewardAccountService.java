package com.sunlights.customer.service;

import models.RewardAccountBalance;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: RewardAccountBalanceService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public interface RewardAccountService {

    /**
     * 创建奖励账户
     * @param customerId
     * @return
     */
    public RewardAccountBalance createRewardAccount(String customerId);

    public RewardAccountBalance findRewardAccountByCustomerId(String customerId);

}
