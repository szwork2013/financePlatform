package com.sunlights.account.service;

import com.sunlights.account.dal.RewardTypeDao;
import models.RewardType;

/**
 * Created by Administrator on 2014/11/19.
 */
public interface RewardTypeService {



    public RewardType findByTypeCode(String code);

}
