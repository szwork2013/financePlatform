package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ExchangeSceneDao;
import models.ExchangeScene;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeSceneDaoImpl extends EntityBaseDao implements ExchangeSceneDao {

    @Override
    public ExchangeScene queryByScene(String exchangeScene) {
        List<ExchangeScene> exchangeScenes = super.findBy(ExchangeScene.class, "scene", exchangeScene);
        if(exchangeScenes == null || exchangeScenes.isEmpty()) {
            return null;
        }
        return exchangeScenes.get(0);
    }
}
