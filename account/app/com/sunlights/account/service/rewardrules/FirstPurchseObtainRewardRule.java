package com.sunlights.account.service.rewardrules;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.dal.ObtainRewardRuleDao;
import com.sunlights.account.dal.impl.ObtainRewardRuleDaoImpl;
import com.sunlights.account.vo.RewardResultVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import models.Trade;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/25.
 */
public class FirstPurchseObtainRewardRule extends AbstractObtainRewardRule{

    private ObtainRewardRuleDao obtainRewardRuleDao = new ObtainRewardRuleDaoImpl();

    @Override
    public String getScene() {
        return AccountConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE;
    }

    @Override
    public RewardResultVo validate(String custId) {
        List<Trade> trades = obtainRewardRuleDao.getTradesByCustId(custId);
        if(trades != null && trades.size() > 0) {
            RewardResultVo vo = new RewardResultVo();
            Message message = new Message(Severity.INFO, MsgCode.ALREADY_PURCHASE);
            vo.setStatus(AccountConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
            vo.setReturnMessage(message);
            return vo;
        }
        return super.validate(custId);
    }
}
