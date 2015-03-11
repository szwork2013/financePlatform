package com.sunlights.customer.dal.impl;


import com.google.common.collect.Maps;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ObtainRewardRuleDao;
import models.ObtainRewardRule;
import models.Trade;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/11/19.
 */
public class ObtainRewardRuleDaoImpl extends EntityBaseDao implements ObtainRewardRuleDao {
    @Override
    public List<ObtainRewardRule> getByActivityId(Long activityId) {
        return findBy(ObtainRewardRule.class, "activityId", activityId);
    }

    @Override
    public ObtainRewardRule findRewardRuleByActivityId(Long activityId) {
        String jpql = "select o from ObtainRewardRule o where o.activityId = ?1 and o.status = 'N' and (inviter is null or inviter = 0)";
        Query query = createQuery(jpql, activityId);
        List<ObtainRewardRule> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Trade> getTradesByCustId(String custId) {
        return findBy(Trade.class, "custId", custId);
    }

    @Override
    public List<ObtainRewardRule> findFirstPurchaseEffective(Long activityId) {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
        map.put("activityId", activityId);
        String sql = "select fr " +
                "  from ObtainRewardRule fr ,Activity fa" +
                " where fr.activityId = fa.id" +
                "   and fr.status = 'N'" +
                "   and fa.status = 'N'" +
                "   and fa.scene = 'ASC005'" +
                "   and fa.beginTime <= current_date" +
                "   and fa.endTime >= current_date" +
                "   /~ and fa.activityId = {activityId} ~/";

        List list = findByMap(sql, map);

        return list;
    }
}
