package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.RewardAccountDetailsDao;
import models.RewardAccountDetails;

import java.util.Date;

/**
 * Created by Administrator on 2015/3/19.
 */
public class RewardAccountDetailsDaoImpl extends EntityBaseDao implements RewardAccountDetailsDao {

    @Override
    public void doInsert(RewardAccountDetails rewardAccountDetails) {
        rewardAccountDetails.setCreateTime(new Date());
        super.create(rewardAccountDetails);
    }
}
