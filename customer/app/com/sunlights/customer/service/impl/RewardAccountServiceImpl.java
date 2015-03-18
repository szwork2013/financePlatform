package com.sunlights.customer.service.impl;

import com.sunlights.common.utils.DBHelper;
import com.sunlights.customer.dal.RewardAccountDao;
import com.sunlights.customer.dal.impl.RewardAccountDaoImpl;
import com.sunlights.customer.service.RewardAccountService;
import models.RewardAccountBalance;

import java.sql.Timestamp;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: RewardAccountBalanceServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class RewardAccountServiceImpl implements RewardAccountService {
    private RewardAccountDao rewardAccountBalanceDao = new RewardAccountDaoImpl();

    @Override
    public RewardAccountBalance createRewardAccount(String customerId) {
        Timestamp currentTime = DBHelper.getCurrentTime();

        RewardAccountBalance rewardAccountBalance = new RewardAccountBalance();
        rewardAccountBalance.setCustomerId(customerId);
        rewardAccountBalance.setCreateTime(currentTime);
        rewardAccountBalanceDao.createRewardAccount(rewardAccountBalance);
        return rewardAccountBalance;
    }

    @Override
    public RewardAccountBalance findRewardAccountByCustomerId(String customerId) {
        return rewardAccountBalanceDao.findRewardAccountByCustomerId(customerId);
    }


}
