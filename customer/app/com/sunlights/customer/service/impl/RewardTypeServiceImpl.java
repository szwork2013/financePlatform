package com.sunlights.customer.service.impl;


import com.sunlights.common.cache.Cacheable;
import com.sunlights.customer.dal.RewardTypeDao;
import com.sunlights.customer.dal.impl.RewardTypeDaoImpl;
import com.sunlights.customer.service.RewardTypeService;
import models.RewardType;

/**
 * Created by Administrator on 2014/11/19.
 */
public class RewardTypeServiceImpl implements RewardTypeService {

    private RewardTypeDao rewardTypeDao = new RewardTypeDaoImpl();

    @Cacheable(key = "findByTypeCode", duration = 3000)
    @Override
    public RewardType findByTypeCode(String code) {

        return rewardTypeDao.findByTypeCode(code);
    }
}
