package com.sunlights.customer.dal;

import models.RewardType;

/**
 * Created by Administrator on 2014/11/19.
 */
public interface RewardTypeDao {

    public RewardType findByTypeCode(String code);
}
