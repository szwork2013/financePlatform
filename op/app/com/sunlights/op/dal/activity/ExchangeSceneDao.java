package com.sunlights.op.dal.activity;

import com.sunlights.op.vo.activity.ExchangeSceneVo;
import models.ExchangeScene;

import java.util.List;

/**
 * Created by Administrator on 2014/12/11.
 */
public interface ExchangeSceneDao {

    public List<ExchangeSceneVo> queryAll();

    public List<ExchangeScene> findExchangeScenes();

    public ExchangeScene doInsert(ExchangeScene exchangeScene);

    public void doDelete(Long id);

    public ExchangeScene doUpdate(ExchangeScene exchangeScene);

    public ExchangeScene findById(Long id);

    public ExchangeScene findByScene(String scene);

}
