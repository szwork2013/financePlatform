package com.sunlights.customer.service.impl;

import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
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
import models.Activity;
import models.ExchangeScene;
import models.HoldReward;
import models.RewardType;
import play.Configuration;
import play.Logger;

import java.math.BigDecimal;
import java.text.MessageFormat;
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
    public List<ExchangeSceneVo> loadSceneByCustId(String custId, PageVo pageVo) {
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
                money = money.add(holdReward.getHoldMoney());
            }
            if(total < exchangeScene.getRequireAmt()) {
                Logger.debug("total = " + total + " < " + " RequireAmt = " + exchangeScene.getRequireAmt());
                continue;
            }
            exchangeSceneVo = new ExchangeSceneVo();
            exchangeSceneVo.setId(String.valueOf(exchangeScene.getId()));
            exchangeSceneVo.setTitle(exchangeScene.getTitle());
            String detaiTemplate = Configuration.root().getString(exchangeScene.getScene() + "." + exchangeScene.getRewardType() + "." + exchangeScene.getActivityType());
            exchangeSceneVo.setDetail(MessageFormat.format(detaiTemplate, money));
            exchangeSceneVo.setLogo(exchangeScene.getLogo());
            result.add(exchangeSceneVo);
        }

        return page(result, pageVo);
    }

    private List<ExchangeSceneVo> page(List<ExchangeSceneVo> exchangeSceneVos, PageVo pageVo) {
        //TODO 注意严谨  代码可以复用
        int length = exchangeSceneVos.size();
        pageVo.setCount(length);
        if(length <= pageVo.getPageSize()) {
            return exchangeSceneVos;
        }
        if(pageVo.getIndex() + pageVo.getPageSize() > length) {
            return exchangeSceneVos.subList(pageVo.getIndex(), length);
        }
        return exchangeSceneVos.subList(pageVo.getIndex(), pageVo.getIndex() + pageVo.getPageSize());
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
        String detaiTemplate = Configuration.root().getString(exchangeScene.getScene() + "." + exchangeScene.getRewardType() + "." + exchangeScene.getActivityType());
        data4ExchangeVo.setSummary(MessageFormat.format(detaiTemplate, money));

        List<Data4ExchangeItem> items = rewardFlowService.getItemsByType(exchangeScene.getScene(), custId, activityType, rewardType);
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
