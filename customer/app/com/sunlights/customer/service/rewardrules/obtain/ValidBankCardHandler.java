package com.sunlights.customer.service.rewardrules.obtain;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.BankCardService;
import com.sunlights.customer.service.impl.BankCardServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.ObtainRewardRuleVo;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 送红包的话，如果没有在金豆荚中绑定银行卡，则不送红包
 * <p/>
 * Created by tangweiqun on 2014/12/19.
 */
public class ValidBankCardHandler extends AbstractObtainRuleHandler {

    private BankCardService bankCardService = new BankCardServiceImpl();

    public ValidBankCardHandler() {

    }

    public ValidBankCardHandler(ObtainRuleHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void obtainInternal(ActivityRequestVo requestVo, ActivityResponseVo responseVo) throws Exception {
        Logger.debug("送红包时银行卡校验");
        String custId = requestVo.getCustId();
        String bankCard = bankCardService.getBankCardByCustId(custId);
        if (StringUtils.isEmpty(bankCard)) {
            Map<Long, List<ObtainRewardRuleVo>> obtainRewardRuleMap = requestVo.getObtainRewardRuleMap();
            Map<Long, List<ObtainRewardRuleVo>> result = new HashMap<Long, List<ObtainRewardRuleVo>>();
            for (Map.Entry<Long, List<ObtainRewardRuleVo>> entry : obtainRewardRuleMap.entrySet()) {
                Long key = entry.getKey();
                List<ObtainRewardRuleVo> obtainRewardRuleVos = entry.getValue();
                List<ObtainRewardRuleVo> resultList = new ArrayList<ObtainRewardRuleVo>(obtainRewardRuleVos);
                for (ObtainRewardRuleVo obtainRewardRuleVo : obtainRewardRuleVos) {
                    if (ActivityConstant.REWARD_TYPE_REDPACKET.equals(obtainRewardRuleVo.getRewardTypeModel().getCode())) {
                        Logger.debug("没有银行卡，不送红包");
                        resultList.remove(obtainRewardRuleVo);
                    }
                }
                result.put(key, resultList);
            }
            requestVo.setObtainRewardRuleMap(result);
        }
    }

    @Override
    public String toString() {
        return "ValidBankCardHandler";
    }
}
