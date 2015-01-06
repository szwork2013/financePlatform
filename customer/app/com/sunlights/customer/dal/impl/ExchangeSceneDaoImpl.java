package com.sunlights.customer.dal.impl;

import com.sunlights.common.cache.Cacheable;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ExchangeSceneDao;
import models.ExchangeScene;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeSceneDaoImpl extends EntityBaseDao implements ExchangeSceneDao {

    @Cacheable(key = "queryByScene", duration = 3000)
    @Override
    public ExchangeScene queryByScene(String exchangeScene) {
        List<ExchangeScene> exchangeScenes = super.findBy(ExchangeScene.class, "scene", exchangeScene);
        if(exchangeScenes == null || exchangeScenes.isEmpty()) {
            return null;
        }
        return exchangeScenes.get(0);
    }

    @Cacheable(key = "queryById", duration = 3000)
    @Override
    public ExchangeScene queryById(Long id) {
        List<ExchangeScene> exchangeScenes = super.findBy(ExchangeScene.class, "id", id);
        if(exchangeScenes == null || exchangeScenes.isEmpty()) {
            return null;
        }
        return exchangeScenes.get(0);
    }

    @Cacheable(key = "loadAll", duration = 3000)
    @Override
    public List<ExchangeScene> loadAll() {
        return super.findAll(ExchangeScene.class);
    }
}
