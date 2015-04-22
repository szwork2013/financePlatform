package com.sunlights.op.dal.activity;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.activity.RewardTypeVo;
import models.RewardType;

import java.util.List;

/**
 * Created by Administrator on 2014/11/14.
 */
public interface RewardTypeDao {

    public List<RewardType> findAllTypes();

    public boolean doInsert(RewardType type);

    public boolean doUpdate(RewardType type);

    public boolean doDelete(Long id);

    public RewardType findById(Long id);

    public List<RewardTypeVo> findAllTypeWithRule(PageVo pageVo);

    public RewardType findByCode(String code);

    public boolean removeByCode(String code);

}
