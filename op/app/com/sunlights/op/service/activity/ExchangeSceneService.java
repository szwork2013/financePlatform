package com.sunlights.op.service.activity;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.activity.ExchangeSceneVo;
import models.ExchangeScene;

import java.util.List;

/**
 * Created by Administrator on 2014/12/11.
 */
public interface ExchangeSceneService {

    public List<ExchangeSceneVo> findAllScenes(PageVo pageVo);

    public List<ExchangeScene> findExchangeScenes();

    public ExchangeSceneVo save(ExchangeScene exchangeScene);

    public ExchangeSceneVo modify(ExchangeScene exchangeScene);

    public void remove(Long id);

    public ExchangeScene findByScene(String scene);

}
