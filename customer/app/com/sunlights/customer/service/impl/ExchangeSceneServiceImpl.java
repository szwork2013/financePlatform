package com.sunlights.customer.service.impl;

import com.sunlights.common.utils.CommonUtil;
import com.sunlights.customer.dal.ExchangeSceneDao;
import com.sunlights.customer.dal.HoldRewardDao;
import com.sunlights.customer.dal.impl.ExchangeSceneDaoImpl;
import com.sunlights.customer.dal.impl.HoldRewardDaoImpl;
import com.sunlights.customer.service.ExchangeSceneService;
import com.sunlights.customer.service.RewardFlowService;
import com.sunlights.customer.service.RewardTypeService;
import com.sunlights.customer.vo.Data4ExchangeItem;
import com.sunlights.customer.vo.Data4ExchangeVo;
import com.sunlights.customer.vo.ExchangeSceneListVo;
import com.sunlights.customer.vo.ExchangeSceneVo;
import models.ExchangeScene;
import models.HoldReward;
import models.RewardType;
import play.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeSceneServiceImpl implements ExchangeSceneService {
    private ExchangeSceneDao exchangeSceneDao = new ExchangeSceneDaoImpl();
    private HoldRewardDao holdRewardDao = new HoldRewardDaoImpl();

    private RewardTypeService rewardTypeService = new RewardTypeServiceImpl();
    private RewardFlowService rewardFlowService = new RewardFlowServiceImpl();

    @Override
    public ExchangeScene findByscene(String exchangeScene) {
        return exchangeSceneDao.queryByScene(exchangeScene);
    }

    @Override
    public ExchangeSceneListVo loadSceneByCustId(String custId) {
        List<ExchangeScene> exchangeScenes = loadAllExchangescenes();
        List<ExchangeSceneVo> result = new ArrayList<ExchangeSceneVo>();
        ExchangeSceneListVo exchangeSceneListVo = new ExchangeSceneListVo();
        ExchangeSceneVo exchangeSceneVo = null;
        for(ExchangeScene exchangeScene : exchangeScenes) {
            String activityType = exchangeScene.getActivityType();
            String rewardType = exchangeScene.getRewardType();
            List<HoldReward> holdRewards = holdRewardDao.findByCustIdAndRewardType(custId, rewardType, activityType);
            Logger.debug("holdRewards == " + holdRewards.size());
            Long total = Long.valueOf(0);
            BigDecimal money = BigDecimal.ZERO;
            for(HoldReward holdReward : holdRewards) {
                total += (holdReward.getHoldReward() - holdReward.getFrozenReward());
                money = money.add(holdReward.getHoldMoney().subtract(holdReward.getFrozenMoney()));
            }
            if(total < exchangeScene.getRequireAmt()) {
                Logger.debug("total = " + total + " < " + " RequireAmt = " + exchangeScene.getRequireAmt());
                continue;
            }
            exchangeSceneVo = new ExchangeSceneVo();
            exchangeSceneVo.setId(String.valueOf(exchangeScene.getId()));
            exchangeSceneVo.setTitle(exchangeScene.getTitle());
            exchangeSceneVo.setDetail("首次申购" + money.toString() + "元");
            exchangeSceneVo.setLogo(exchangeScene.getLogo());
            result.add(exchangeSceneVo);
        }
        exchangeSceneListVo.setList(result);
        return exchangeSceneListVo;
    }

    @Override
    public Data4ExchangeVo prepareData4Exchange(String custId, String sceneId) {
        ExchangeScene exchangeScene = findById(sceneId);
        String rewardType = exchangeScene.getRewardType();
        String activityType = exchangeScene.getActivityType();

        List<HoldReward> holdRewards = holdRewardDao.findByCustIdAndRewardType(custId, rewardType, activityType);
        Logger.debug("holdRewards == " + holdRewards.size());
        RewardType rewardTypeModel = rewardTypeService.findByTypeCode(rewardType);
        Long total = Long.valueOf(0);
        BigDecimal money = BigDecimal.ZERO;
        BigDecimal canPayed = BigDecimal.ZERO;
        BigDecimal totalMoney = BigDecimal.ZERO;
        for(HoldReward holdReward : holdRewards) {
            total += (holdReward.getHoldReward() - holdReward.getFrozenReward());
            money = money.add(holdReward.getHoldMoney().subtract(holdReward.getFrozenMoney()));
            totalMoney = totalMoney.add(holdReward.getGetMoney());
            canPayed = canPayed.add(money);
        }
        if(total > exchangeScene.getExchangeLimit()) {
            canPayed = BigDecimal.valueOf(exchangeScene.getExchangeLimit()).divide(BigDecimal.valueOf(rewardTypeModel.getUnit()));
        }

        Data4ExchangeVo data4ExchangeVo = new Data4ExchangeVo();
        data4ExchangeVo.setCanPayed(canPayed.toString());
        data4ExchangeVo.setMaxPayed(canPayed.toString());
        data4ExchangeVo.setAccountDate(calcAccountDate(exchangeScene.getTimeLimit(), null));
        data4ExchangeVo.setSummary("首次申购" + totalMoney.toString() + "元");

        List<Data4ExchangeItem> items = rewardFlowService.getItemsByType(custId, activityType, rewardType);
        data4ExchangeVo.setList(items);
        data4ExchangeVo.setLogo(exchangeScene.getLogo());

        return data4ExchangeVo;
    }

    @Override
    public ExchangeScene findById(String id) {
        return exchangeSceneDao.queryById(Long.valueOf(id));
    }

    @Override
    public List<ExchangeScene> loadAllExchangescenes() {
        return exchangeSceneDao.loadAll();
    }

    @Override
    public String calcAccountDate(Integer days, Date exchangeDate) {
        if(days == null) {
            days = 0;
        }
        Calendar calendar = Calendar.getInstance();
        if(exchangeDate != null) {
            calendar.setTime(exchangeDate);
        }
        calendar.add(Calendar.DATE, days);
        String accountDate = CommonUtil.dateToString(new Date(calendar.getTimeInMillis()), CommonUtil.DATE_FORMAT_LONG);
        return accountDate;
    }
}
