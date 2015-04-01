package com.sunlights.op.dal.activity.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.op.dal.activity.RewardTypeDao;
import com.sunlights.op.vo.activity.RewardTypeVo;
import models.RewardType;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/14.
 */
public class RewardTypeDaoImpl extends EntityBaseDao implements RewardTypeDao {

    @Override
    public List<RewardType> findAllTypes() {
        return findAll(RewardType.class);
    }

    @Override
    public RewardType findById(Long id) {
        List<RewardType> types = findBy(RewardType.class, "id", id);
        if(types.isEmpty()) {
            return null;
        } else {
            return types.get(0);
        }

    }

    @Override
    public List<RewardTypeVo> findAllTypeWithRule() {
        StringBuilder sb = new StringBuilder();
        String keys = "typeId,code,name,unit,ruleUrl,ruleId,rate,limitTime";
        String columns = " a.id,a.code,a.name,a.unit,a.rule_url, b.id as ruleId, b.rate,b.limit_time ";
        sb.append("select ").append(columns)
                .append("FROM F_REWARD_TYPE a left join f_exchange_reward_rule b on a.code = b.reward_type order by a.code");

        List<Object[]> resultRows = createLocalQuery(sb.toString()).getResultList();
        List<RewardTypeVo> rewardTypeVos = ConverterUtil.convert(keys, resultRows, RewardTypeVo.class);

        return rewardTypeVos;
    }

    @Override
    public boolean doUpdate(RewardType type) {
        RewardType oldType = findById(type.getId());
        if(oldType == null) {
            return false;
        }
        type.setCreateTime(oldType.getCreateTime());
        type.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        update(type);
        return true;
    }

    @Override
    public boolean doInsert(RewardType type) {
        type.setCreateTime(new Timestamp(System.currentTimeMillis()));

        create(type);
        return true;
    }

    @Override
    public boolean doDelete(Long id) {
        RewardType type = findById(id);
        if(type == null) {
            return false;
        }

        delete(type);
        return false;
    }

    @Override
    public RewardType findByCode(String code) {
        List<RewardType> types = findBy(RewardType.class, "code", code);
        if(types.isEmpty()) {
            return null;
        } else {
            return types.get(0);
        }
    }

    @Override
    public boolean removeByCode(String code) {
        RewardType type = findByCode(code);
        if(type == null) {
            return false;
        }

        delete(type);
        return false;
    }
}
