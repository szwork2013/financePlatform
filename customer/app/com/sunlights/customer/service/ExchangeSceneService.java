package com.sunlights.customer.service;

import com.sunlights.customer.vo.Data4ExchangeVo;
import com.sunlights.customer.vo.ExchangeSceneListVo;
import models.ExchangeScene;

import java.util.Date;
import java.util.List;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public interface ExchangeSceneService {

    public ExchangeScene findByscene(String exchangeScene);

    public ExchangeSceneListVo loadSceneByCustId(String custId);

    public Data4ExchangeVo prepareData4Exchange(String custId, String sceneId);

    public ExchangeScene findById(String id);

    public List<ExchangeScene> loadAllExchangescenes();

    public String calcAccountDate(Integer days, Date exchangeDate);

}
