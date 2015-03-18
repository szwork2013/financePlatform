package com.sunlights.customer.service.rewardrules.obtain;

import com.google.common.collect.Lists;
import com.sunlights.common.DictConst;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.vo.IntegerNullSerialize;
import com.sunlights.customer.dal.ActivityDao;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.impl.ActivityDaoImpl;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.ObtainRewardRuleVo;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 处理活动获取规则的特殊的条件(比如判断老客户是否是第一次申购，判断我们活动的参加的人数是否到达我们指定的人数)
 * 即过滤无效的获取规则
 * </p>
 *
 * Created by tangweiqun on 2015/3/18.
 */
public class FilterInvalidGainRuleHandler extends AbstractObtainRuleHandler {

    private CustomerDao customerDao = new CustomerDaoImpl();

    private ActivityService activityService = new ActivityServiceImpl();

    public FilterInvalidGainRuleHandler() {

    }

    public FilterInvalidGainRuleHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }


    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        Map<Long, List<ObtainRewardRuleVo>> ruleMap = requestVo.getObtainRewardRuleMap();
        for(Map.Entry<Long, List<ObtainRewardRuleVo>> entry : ruleMap.entrySet()) {
            List<ObtainRewardRuleVo> obtainRewardRuleVos = entry.getValue();
            List<ObtainRewardRuleVo> temp = Lists.newArrayListWithCapacity(obtainRewardRuleVos.size());
            temp.addAll(obtainRewardRuleVos);
            for(ObtainRewardRuleVo obtainRewardRuleVo : temp) {
                String activityCondition = obtainRewardRuleVo.getActivityCondition();
                if(!StringUtils.isEmpty(activityCondition)) {
                    if(notPass(activityCondition, requestVo, obtainRewardRuleVo)) {
                        obtainRewardRuleVos.remove(obtainRewardRuleVo);
                    }
                }
            }

            ruleMap.put(entry.getKey(), obtainRewardRuleVos);
        }

        requestVo.setObtainRewardRuleMap(ruleMap);
    }

    private boolean notPass(String activityCondition, ActivityRequestVo requestVo, ObtainRewardRuleVo obtainRewardRuleVo) {
        if(DictConst.ACTIVITY_NEW_TRADE.equals(activityCondition)) {
            boolean isNotFirstPurchase = customerDao.validateHasFirstPurchase(requestVo.getCustId());
            if (isNotFirstPurchase) {
                Logger.info("老客户不是第一次购买需要过滤掉");
                return true;
            }
        }

        if(notPass4NumberOver(obtainRewardRuleVo)) {
            return true;
        }

        return false;
    }

    private boolean notPass4NumberOver(ObtainRewardRuleVo obtainRewardRuleVo) {

        Integer remainCount = activityService.getRemainNum(obtainRewardRuleVo);

        if (remainCount != null && remainCount.intValue() == 0) {
            Logger.debug("活动已经达到最大数量，不能再参加了");
            return true;
        }

        return false;
    }


}
