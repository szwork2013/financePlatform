package com.sunlights.op.dal.activity.impl;

import com.google.common.collect.Maps;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.op.dal.activity.ExchangeSceneDao;
import com.sunlights.op.vo.activity.ExchangeSceneVo;
import models.ExchangeScene;
import play.Logger;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/12/11.
 */
public class ExchangeSceneDaoImpl extends EntityBaseDao implements ExchangeSceneDao {

    @Override
    public List<ExchangeSceneVo> queryAll() {
        StringBuilder sb = new StringBuilder();
        String keys = "id,scene,title,status,rewardType,exchangeType,requireAmt,exchangedAmt,exchangeLimit,timeLimit,createTime,rewardTypeStr,activityType,logo ";
        String columns = " a.id,a.scene,a.title,a.status, a.reward_type,a.EXCHANGE_TYPE, a.AMOUNT,a.EXCHANGED_AMT,a.EXCHANGE_LIMIT,a.TIME_LIMIT,a.create_time,s.name,a.activity_type,a.logo_url ";
        sb.append("select ").append(columns)
                .append("from F_EXCAHNGE_SCENE as a join F_REWARD_TYPE  s on a.reward_type = s.code ");
        sb.append(" where 1 = 1 ");
        sb.append(" order by scene ");

        Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(5);

        List<Object[]> resultRows = createNativeQueryByMap(sb.toString(), filterMap).getResultList();
        List<ExchangeSceneVo> exchangeSceneVos = ConverterUtil.convert(keys, resultRows, ExchangeSceneVo.class);


        Logger.debug("exchangeSceneVos = " + exchangeSceneVos.size());

        return exchangeSceneVos;
    }

    @Override
    public List<ExchangeScene> findExchangeScenes() {
        return findAll(ExchangeScene.class);
    }

    @Override
    public ExchangeScene doInsert(ExchangeScene exchangeScene) {
        exchangeScene.setCreateTime(new Date());
        return super.create(exchangeScene);
    }

    @Override
    public ExchangeScene doUpdate(ExchangeScene exchangeScene) {
        ExchangeScene old = findById(exchangeScene.getId());
        if(old == null) {
            return null;
        }
        exchangeScene.setUpdateTime(new Date());
        update(exchangeScene);
        return exchangeScene;
    }

    @Override
    public void doDelete(Long id) {
        ExchangeScene old = findById(id);
        if(old == null) {
            return ;
        }
        delete(old);
    }

    @Override
    public ExchangeScene findById(Long id) {
        List<ExchangeScene> exchangeScenes = findBy(ExchangeScene.class, "id", id);
        if(exchangeScenes == null || exchangeScenes.isEmpty()) {
            return null;
        }
        return exchangeScenes.get(0);
    }

    @Override
    public ExchangeScene findByScene(String scene) {
        List<ExchangeScene> exchangeScenes = findBy(ExchangeScene.class, "scene", scene);
        if(exchangeScenes == null || exchangeScenes.isEmpty()) {
            return null;
        }
        return exchangeScenes.get(0);
    }
}
