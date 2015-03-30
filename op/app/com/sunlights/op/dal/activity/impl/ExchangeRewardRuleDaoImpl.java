package com.sunlights.op.dal.activity.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.op.dal.activity.ExchangeRewardRuleDao;
import models.ExchangeRewardRule;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/14.
 */
public class ExchangeRewardRuleDaoImpl extends EntityBaseDao implements ExchangeRewardRuleDao {

    @Override
    public List<ExchangeRewardRule> findAllTypes() {
        return findAll(ExchangeRewardRule.class);
    }

    @Override
    public ExchangeRewardRule findById(Long id) {
        List<ExchangeRewardRule> rules = findBy(ExchangeRewardRule.class, "id", id);
        if(rules.isEmpty()) {
            return null;
        } else {
            return rules.get(0);
        }

    }

    @Override
    public List<ExchangeRewardRule> findRulesByRewardType(String rewardType) {

        return findBy(ExchangeRewardRule.class, "rewardType", rewardType);
    }

    @Override
    public boolean doDelete(Long id) {
        ExchangeRewardRule rule = findById(id);
        if(rule == null) {
            return false;
        }

        rule.setStatus("F");
        rule.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        delete(rule);
        return true;
    }

    @Override
    public boolean doUpdate(ExchangeRewardRule rule) {
        ExchangeRewardRule oldRule = findById(rule.getId());
        if(oldRule == null) {
            return false;
        }
        rule.setCreateTime(oldRule.getCreateTime());
        rule.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        update(rule);
        return true;
    }

    @Override
    public boolean doInsert(ExchangeRewardRule rule) {
        rule.setCreateTime(new Timestamp(System.currentTimeMillis()));
        create(rule);
        return true;
    }

    @Override
    public boolean doDeleteByTypeId(String rewardType) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ExchangeRewardRule where rewardType = " + rewardType);
        em.createQuery(sb.toString()).executeUpdate();

        return true;
    }
}
