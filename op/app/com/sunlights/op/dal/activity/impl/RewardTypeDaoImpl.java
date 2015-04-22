package com.sunlights.op.dal.activity.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.activity.RewardTypeDao;
import com.sunlights.op.vo.activity.RewardTypeVo;
import com.sunlights.op.vo.statistics.PurchaseInfoVo;
import models.RewardType;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/14.
 */
public class RewardTypeDaoImpl extends EntityBaseDao implements RewardTypeDao {

    private PageDao pageDao = new PageDaoImpl();

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
    public List<RewardTypeVo> findAllTypeWithRule(PageVo pageVo) {
        StringBuilder selectSql = new StringBuilder();
        selectSql.append(" FROM F_REWARD_TYPE a left join f_exchange_reward_rule b on a.code = b.reward_type  ");

        StringBuilder sb = new StringBuilder();
        String keys = "typeId,code,name,unit,ruleUrl,ruleId,rate,limitTime";
        String columns = " a.id,a.code,a.name,a.unit,a.rule_url, b.id as ruleId, b.rate,b.limit_time ";
        sb.append("select ").append(columns)
                .append(selectSql).append(" order by a.code");

        StringBuilder countSb = new StringBuilder();
        countSb.append("select count(*) ")
                .append(selectSql);

        List list = pageDao.findNativeComplexBy(sb.toString(), countSb.toString(), pageVo);

        List<RewardTypeVo> rewardTypeVos = ConverterUtil.convert(keys, list, RewardTypeVo.class);

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
