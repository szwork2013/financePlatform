package com.sunlights.op.dal.activity.impl;

import com.google.common.collect.Maps;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.op.dal.activity.ObtainRewardRuleDao;
import com.sunlights.op.vo.activity.ObtainRewardRuleVo;
import models.ObtainRewardRule;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/11/14.
 */
public class ObtainRewardRuleDaoImpl extends EntityBaseDao implements ObtainRewardRuleDao {
    @Override
    public List<ObtainRewardRule> findAllTypes() {
        return findAll(ObtainRewardRule.class);
    }

    @Override
    public List<ObtainRewardRule> findRulesByActivityId(Long activityId) {

        return findBy(ObtainRewardRule.class, "activityId", activityId);
    }

    @Override
    public ObtainRewardRule doInsert(ObtainRewardRule rule) {
        rule.setStatus("N");
        rule.setCreateTime(new Timestamp(System.currentTimeMillis()));
        create(rule);
        return rule;
    }

    @Override
    public boolean doDelete(Long id) {
        ObtainRewardRule rule = findById(id);
        if(rule == null) {
            return false;
        }
        rule.setStatus("F");
        rule.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        delete(rule);
        return true;
    }

    @Override
    public boolean doUpdate(ObtainRewardRule rule) {
        ObtainRewardRule ruleOld = findById(rule.getId());
        if(ruleOld == null) {
            return false;
        }
        rule.setCreateTime(ruleOld.getCreateTime());
        rule.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        update(rule);
        return true;
    }

    @Override
    public ObtainRewardRule findById(Long id) {
        List<ObtainRewardRule> list = findBy(ObtainRewardRule.class, "id", id);
        if(list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public void deleteByActivityId(Long activityId) {
        if(activityId == null) {
            return;
        }
        String sql = "delete from f_get_reward_rule where activity_id = " + activityId;

        em.createNativeQuery(sql).executeUpdate();

    }

    @Override
    public List<ObtainRewardRuleVo> findVosByActivityId(Long activityId) {
        StringBuilder sb = new StringBuilder();
        String keys = "id,activityId,rewardType,status,shouldReward,realReward,effectTime,validTime,inviter,createTime,rewardTypeStr  ";
        String columns = " a.id,  a.activity_id,a.reward_type,a.status, a.should_reward,a.real_reward,a.effect_time,a.valid_time,a.IS_INVITER,a.create_time,s.name ";
        sb.append("select ").append(columns)
                .append("from F_GET_REWARD_RULE as a left join f_reward_type s on a.reward_type = s.code ");
        sb.append(" where 1 = 1 ");
        sb.append("  /~and a.activity_id = {activityId}~/ ");
        sb.append(" order by id ");

        Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(5);


        filterMap.put("EQL_activityId", activityId);

        List<Object[]> resultRows = createNativeQueryByMap(sb.toString(), filterMap).getResultList();
        List<ObtainRewardRuleVo> obtainRewardRuleVos = ConverterUtil.convert(keys, resultRows, ObtainRewardRuleVo.class);
        return obtainRewardRuleVos;

    }
}
