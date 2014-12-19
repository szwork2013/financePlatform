package com.sunlights.customer.service.rewardrules.obtain;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.service.BankCardService;
import com.sunlights.customer.service.impl.BankCardServiceImpl;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.service.rewardrules.vo.ObtainRewardRuleVo;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

import java.util.List;
import java.util.Map;

/**
 * 送红包的话，如果没有在金豆荚中绑定银行卡，则不送红包
 *
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
        String custId = requestVo.getCustId();
        String bankCard = bankCardService.getBankCardByCunstId(custId);
        if(StringUtils.isEmpty(bankCard)) {
            Map<Long, List<ObtainRewardRuleVo>> obtainRewardRuleMap = requestVo.getObtainRewardRuleMap();
            for(Map.Entry<Long, List<ObtainRewardRuleVo>> entry : obtainRewardRuleMap.entrySet()) {
                List<ObtainRewardRuleVo> obtainRewardRuleVos = entry.getValue();
                for(ObtainRewardRuleVo obtainRewardRuleVo : obtainRewardRuleVos) {
                    if(ActivityConstant.REWARD_TYPE_REDPACKET.equals(obtainRewardRuleVo.getRewardTypeModel().getCode())) {
                        Logger.debug("没有银行卡，不送红包");
                        obtainRewardRuleVos.remove(obtainRewardRuleVo);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "ValidBankCardHandler";
    }
}
