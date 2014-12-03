package com.sunlights.customer.service.impl;

import com.sunlights.customer.dal.ExchangeSceneDao;
import com.sunlights.customer.dal.impl.ExchangeSceneDaoImpl;
import com.sunlights.customer.service.ExchangeSceneService;
import models.ExchangeScene;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeSceneServiceImpl implements ExchangeSceneService {
    private ExchangeSceneDao exchangeSceneDao = new ExchangeSceneDaoImpl();

    @Override
    public ExchangeScene findByscene(String exchangeScene) {
        return exchangeSceneDao.queryByScene(exchangeScene);
    }
}
