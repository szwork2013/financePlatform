package com.sunlights.customer.service.impl;


import com.sunlights.customer.dal.RewardTypeDao;
import com.sunlights.customer.dal.impl.RewardTypeDaoImpl;
import com.sunlights.customer.service.RewardTypeService;
import models.RewardType;

/**
 * Created by Administrator on 2014/11/19.
 */
public class RewardTypeServiceImpl implements RewardTypeService {
    //TODO
    private RewardTypeDao rewardTypeDao = new RewardTypeDaoImpl();

    @Override
    public RewardType findByTypeCode(String code) {

        return rewardTypeDao.findByTypeCode(code);
    }
}
