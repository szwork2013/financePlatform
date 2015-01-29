package com.sunlights.customer.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.ActivityPageUtil;
import com.sunlights.customer.dal.ExchangeSceneDao;
import com.sunlights.customer.dal.HoldRewardDao;
import com.sunlights.customer.dal.impl.HoldRewardDaoImpl;
import com.sunlights.customer.factory.ActivityDaoFactory;
import com.sunlights.customer.factory.ActivityServiceFactory;
import com.sunlights.customer.service.*;
import com.sunlights.customer.vo.*;
import models.ExchangeRewardRule;
import models.ExchangeScene;
import models.HoldReward;
import models.RewardType;
import org.apache.commons.lang3.StringUtils;
import play.Configuration;
import play.Logger;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by tangweiqun on 2014/12/3.
 */
public class ExchangeSceneServiceImpl implements ExchangeSceneService {
    private ExchangeSceneDao exchangeSceneDao = ActivityDaoFactory.getExchangeSceneDao();
    private HoldRewardDao holdRewardDao = new HoldRewardDaoImpl();

    private RewardTypeService rewardTypeService = ActivityServiceFactory.getRewardTypeService();
    private RewardFlowService rewardFlowService = new RewardFlowServiceImpl();
    private HoldRewardService holdRewardService = new HoldRewardServiceImpl();
    private ExchangeRewardRuleService exchangeRewardRuleService = new ExchangeRewardRuleServiceImpl();
    private ParameterService parameterService = new ParameterService();

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
            List<HoldReward> holdRewards = holdRewardDao.findByCustIdAndRewardType(custId, rewardType, activityType, false);
            Logger.debug("holdRewards == " + holdRewards.size());
            Long total = Long.valueOf(0);
            BigDecimal money = BigDecimal.ZERO;
            for(HoldReward holdReward : holdRewards) {
                total += (holdReward.getHoldReward() - holdReward.getFrozenReward());
                money = money.add(holdReward.getHoldMoney().subtract(holdReward.getFrozenMoney()));
            }
            if(total < exchangeScene.getRequireAmt() || total == 0L) {
                Logger.debug("total = " + total + " < " + " RequireAmt = " + exchangeScene.getRequireAmt());
                continue;
            }
            exchangeSceneVo = new ExchangeSceneVo();
            exchangeSceneVo.setId(String.valueOf(exchangeScene.getId()));
            exchangeSceneVo.setTitle(exchangeScene.getTitle());

            if (ActivityConstant.ACTIVITY_EXCHANGE_BEAN_SCENE_CODE.equals(exchangeScene.getScene())) {
                DataBean4ExchangeVo dataBean4ExchangeVo = getDataBean4ExchangeVo();
                double rate = Double.valueOf(dataBean4ExchangeVo.getRate());
                int exchangeAmount = Integer.valueOf(dataBean4ExchangeVo.getExchangeList().get(0));
                BigDecimal limitLowExchangeNum = new BigDecimal(exchangeAmount/rate);
                exchangeSceneVo.setDetail(ArithUtil.BigToString(limitLowExchangeNum) + "金豆即可兑换");
            }else{
                //TODO 这样的话不能将多个参数替换
                String detaiTemplate = Configuration.root().getString(exchangeScene.getScene() + "." + exchangeScene.getRewardType() + "." + exchangeScene.getActivityType());
                exchangeSceneVo.setDetail(MessageFormat.format(detaiTemplate, money));
            }

            exchangeSceneVo.setLogo(exchangeScene.getLogo());
            exchangeSceneVo.setExchangeType(exchangeScene.getExchangeType());
            result.add(exchangeSceneVo);
        }

        return ActivityPageUtil.page(result, pageVo);
    }

    @Override
    public Data4ExchangeVo prepareData4Exchange(String custId, String sceneId) {
        ExchangeScene exchangeScene = findById(sceneId);
        String rewardType = exchangeScene.getRewardType();
        String activityType = exchangeScene.getActivityType();

        List<HoldReward> holdRewards = holdRewardDao.findByCustIdAndRewardType(custId, rewardType, activityType, false);
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

        //TODO 这样的话不能将多个参数替换
        String detaiTemplate = Configuration.root().getString(exchangeScene.getScene() + "." + exchangeScene.getRewardType() + "." + exchangeScene.getActivityType());
        data4ExchangeVo.setSummary(MessageFormat.format(detaiTemplate, totalMoney));

        List<Data4ExchangeItem> items = rewardFlowService.getItemsByType(exchangeScene.getScene(), custId, activityType, rewardType);
        data4ExchangeVo.setList(items);
        data4ExchangeVo.setLogo(exchangeScene.getLogo());

        return data4ExchangeVo;
    }

    public DataBean4ExchangeVo getDataBean4ExchangeVo(){
        ExchangeRewardRule exchangeRewardRule = exchangeRewardRuleService.findByRewardType(ActivityConstant.REWARD_TYPE_JINDOU);
        String exchangeBean = parameterService.getParameterByName(ParameterConst.EXCHANGE_BEAN);
        if (StringUtils.isEmpty(exchangeBean)) {
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.MISSING_PARAM_CONFIG, ParameterConst.EXCHANGE_BEAN);
        }
        List<String> exchangeBeanList = Arrays.asList(exchangeBean.split(";"));

        DataBean4ExchangeVo dataBean4ExchangeVo = new DataBean4ExchangeVo();
        dataBean4ExchangeVo.setRate(ArithUtil.bigToScale2(exchangeRewardRule.getRate()));
        dataBean4ExchangeVo.setExchangeList(exchangeBeanList);

        return dataBean4ExchangeVo;
    }

    private List<String> getExchangeList() {
        String exchangeBean = parameterService.getParameterByName(ParameterConst.EXCHANGE_BEAN);
        if (StringUtils.isEmpty(exchangeBean)) {
            throw CommonUtil.getInstance().errorBusinessException(MsgCode.MISSING_PARAM_CONFIG, ParameterConst.EXCHANGE_BEAN);
        }
        return Arrays.asList(exchangeBean.split(";"));
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
    public String calcAccountDate(Integer days, Date exchangeDate, boolean isLongDate) {
        if(days == null) {
            days = 0;
        }
        Calendar calendar = Calendar.getInstance();
        if(exchangeDate != null) {
            calendar.setTime(exchangeDate);
        }
        calendar.add(Calendar.DATE, days);
        String dateFormatStr = CommonUtil.DATE_FORMAT_LONG;
        if(!isLongDate) {
            dateFormatStr = CommonUtil.DATE_FORMAT_SHORT;
        }
        String accountDate = CommonUtil.dateToString(new Date(calendar.getTimeInMillis()), dateFormatStr);
        return accountDate;
    }
}
