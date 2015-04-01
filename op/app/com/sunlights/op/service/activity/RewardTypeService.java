package com.sunlights.op.service.activity;

import com.sunlights.op.vo.activity.RewardTypeVo;
import models.RewardType;

import java.util.List;

/**
 * Created by Administrator on 2014/11/14.
 */
public interface RewardTypeService {

    public List<RewardType> findAllTypes();

    public boolean add(RewardType type);

    public boolean modify(RewardType type);

    public boolean remove(Long id);

    public boolean removeByCode(String code);

    public List<RewardTypeVo> findAllTypeWithRule();


    public RewardType findByCode(String code);

}
