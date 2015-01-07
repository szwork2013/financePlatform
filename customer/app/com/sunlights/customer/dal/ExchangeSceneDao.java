package com.sunlights.customer.dal;

import models.ExchangeScene;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public interface ExchangeSceneDao {

    public ExchangeScene queryByScene(String exchangeScene);

    public ExchangeScene queryById(Long id);

    public List<ExchangeScene> loadAll();

}
