package com.sunlights.op.service.activity.impl;


import com.sunlights.op.dal.activity.RewardTypeDao;
import com.sunlights.op.dal.activity.impl.RewardTypeDaoImpl;
import com.sunlights.op.service.activity.RewardTypeService;
import com.sunlights.op.vo.activity.RewardTypeVo;
import models.RewardType;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/14.
 */
public class RewardTypeServiceImpl implements RewardTypeService {

    private RewardTypeDao rewardTypeDao = new RewardTypeDaoImpl();

    @Override
    public List<RewardType> findAllTypes() {
        return rewardTypeDao.findAllTypes();
    }

    @Override
    public boolean add(RewardType type) {
        return rewardTypeDao.doInsert(type);
    }

    @Override
    public boolean modify(RewardType type) {
        return rewardTypeDao.doUpdate(type);
    }

    @Override
    public boolean remove(Long id) {
        return rewardTypeDao.doDelete(id);
    }

    @Override
    public List<RewardTypeVo> findAllTypeWithRule() {
        return rewardTypeDao.findAllTypeWithRule();
    }

    @Override
    public RewardType findByCode(String code) {
        return rewardTypeDao.findByCode(code);
    }

    @Override
    public boolean removeByCode(String code) {
        return rewardTypeDao.removeByCode(code);
    }
}
