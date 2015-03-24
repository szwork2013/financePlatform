package com.sunlights.customer.service;

import models.RewardAccountBalance;

import java.math.BigDecimal;

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

    /**
     * 更新奖励账户  并产生流水
     * @param custId    客户号
     * @param scene 场景
     * @param rewardType 奖励类型
     * @param amt   获取/兑换奖励的数量
     * @param amtMoney  获取/兑换奖励的折现
     * @param fundFlowType  资金流向  0-收入，1-支出
     */
    public void updateRewardAccount(String custId, String scene, String rewardType, long amt, BigDecimal amtMoney, String fundFlowType);

}
