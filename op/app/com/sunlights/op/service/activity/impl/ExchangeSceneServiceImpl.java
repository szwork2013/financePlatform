package com.sunlights.op.service.activity.impl;

import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.activity.ExchangeSceneDao;
import com.sunlights.op.dal.activity.impl.ExchangeSceneDaoImpl;
import com.sunlights.op.service.activity.ExchangeSceneService;
import com.sunlights.op.service.activity.RewardTypeService;
import com.sunlights.op.vo.activity.ExchangeSceneVo;
import models.ExchangeScene;
import models.RewardType;

import java.util.List;

/**
 * Created by Administrator on 2014/12/11.
 */
public class ExchangeSceneServiceImpl implements ExchangeSceneService {
    private ExchangeSceneDao exchangeSceneDao = new ExchangeSceneDaoImpl();
    private RewardTypeService rewardTypeService = new RewardTypeServiceImpl();

    @Override
    public List<ExchangeSceneVo> findAllScenes(PageVo pageVo) {
        return exchangeSceneDao.queryAll(pageVo);
    }

    @Override
    public List<ExchangeScene> findExchangeScenes() {
        return exchangeSceneDao.findExchangeScenes();
    }

    @Override
    public ExchangeSceneVo save(ExchangeScene exchangeScene) {
        ExchangeScene newScene = exchangeSceneDao.doInsert(exchangeScene);
        ExchangeSceneVo exchangeSceneVo = convert(newScene);
        return exchangeSceneVo;
    }

    @Override
    public ExchangeSceneVo modify(ExchangeScene exchangeScene) {
        ExchangeScene newScene = exchangeSceneDao.doUpdate(exchangeScene);

        ExchangeSceneVo exchangeSceneVo = convert(newScene);

        return exchangeSceneVo;
    }

    private ExchangeSceneVo convert(ExchangeScene newScene) {
        try {
            ExchangeSceneVo exchangeSceneVo = ConverterUtil.fromEntity(new ExchangeSceneVo(), newScene);
            RewardType rewardType = rewardTypeService.findByCode(newScene.getRewardType());
            if(rewardType != null) {
                exchangeSceneVo.setRewardTypeStr(rewardType.getName());
            }
            return exchangeSceneVo;
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public void remove(Long id) {
        exchangeSceneDao.doDelete(id);
    }

    @Override
    public ExchangeScene findByScene(String scene) {
        return exchangeSceneDao.findByScene(scene);
    }
}
