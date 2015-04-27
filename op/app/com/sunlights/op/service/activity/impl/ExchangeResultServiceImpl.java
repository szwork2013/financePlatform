package com.sunlights.op.service.activity.impl;

import com.google.common.collect.Lists;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.common.constants.ActivityConstant;
import com.sunlights.op.dal.activity.ExchangeResultDao;
import com.sunlights.op.dal.activity.impl.ExchangeResultDaoImpl;
import com.sunlights.op.dto.BaseXlsDto;
import com.sunlights.op.dto.ExchangeBeanResultXlsDto;
import com.sunlights.op.dto.ExchangeResultXlsDto;
import com.sunlights.op.service.activity.ExchangeResultService;
import com.sunlights.op.service.activity.ExchangeRewardRuleService;
import com.sunlights.op.service.activity.ExchangeSceneService;
import com.sunlights.op.vo.activity.ExchangeBeanResultVo;
import com.sunlights.op.vo.activity.ExchangeResultStatus;
import com.sunlights.op.vo.activity.ExchangeResultVo;
import models.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tangweiqun on 2014/12/11.
 */
public class ExchangeResultServiceImpl implements ExchangeResultService {
	private ExchangeResultDao exchangeResultDao = new ExchangeResultDaoImpl();
	private ExchangeSceneService exchangeSceneService = new ExchangeSceneServiceImpl();
	private ExchangeRewardRuleService exchangeRewardRuleService = new ExchangeRewardRuleServiceImpl();

	@Override
	public void updateStatus(ExchangeResult exchangeResult) {
		ExchangeScene exchangeScene = exchangeSceneService.findByScene(exchangeResult.getExchangeScene());

		ExchangeRewardRule exchangeRewardRule = exchangeRewardRuleService.findRulesByRewardType(exchangeScene.getRewardType()).get(0);

		BigDecimal rate = exchangeRewardRule.getRate();

		Long rewardAmt = exchangeResult.getAmount().divide(rate).longValue();

		genRewardFlow(exchangeResult, exchangeScene, rewardAmt);

		exchangeResult.setUpdateTime(new Date());
		exchangeResultDao.updateExchangeResult(exchangeResult);
	}

	@Override
	public void genRewardFlow(ExchangeResult exchangeResult, ExchangeScene exchangeScene, Long rewardAmt) {
		// 产生流水
		RewardFlow flow = new RewardFlow();
		flow.setActivityTitle(exchangeScene.getTitle());
		flow.setCustId(exchangeResult.getCustId());
		flow.setRewardType(exchangeScene.getRewardType());
		flow.setActivityType(exchangeScene.getActivityType());

		flow.setOperatorType(ActivityConstant.REWARD_FLOW_EXCHANGE);
		flow.setStatus(RewardFlowStatus.EXCHANGE_SUCC.getStatus());

		flow.setCreateTime(new Date());
		flow.setScene(exchangeScene.getScene());
		flow.setRewardAmt(rewardAmt);
		flow.setMoney(exchangeResult.getAmount());

		exchangeResultDao.saveRewardFlow(flow);

        updateHoldReward(exchangeResult, exchangeScene, rewardAmt, exchangeResult.getAmount(), ActivityConstant.EXCHANGE_BEAN_SUC);
	}


    public void updateExchangeBeanResult(List<BaseXlsDto> dtoList) {
        List<MessageHeaderVo> messageHeaderVoList = Lists.newArrayList();
        Logger.info(">>updateExchangeBeanResult start>>");
        ExchangeResult exchangeResult = null;
        String exchangeBeanType = null;//1兑换成功 2兑换失败 3部分兑换成功，即 兑换金额 > 实际兑换金额
        BigDecimal realExchangeAmount = BigDecimal.ZERO;

        int successfulNum = 0;
        int failNum = 0;

        for (BaseXlsDto baseXlsDto : dtoList) {
            ExchangeBeanResultXlsDto exchangeBeanResultVo = (ExchangeBeanResultXlsDto)baseXlsDto;
            Logger.info(">>Id:" + exchangeBeanResultVo.getId());
            if (StringUtils.isEmpty(exchangeBeanResultVo.getId())) {
                continue;
            }
            exchangeResult = exchangeResultDao.findById(Long.valueOf(exchangeBeanResultVo.getId()));
            //等待兑换状态
            if (exchangeResult != null && exchangeResult.getStatus() == ExchangeResultStatus.EXCHANGEING.getStatus()) {
                String exchangedAmount = exchangeBeanResultVo.getHasExchangeAmount();
                if (StringUtils.isEmpty(exchangedAmount)){
                    exchangeBeanType = ActivityConstant.EXCHANGE_BEAN_FAIL;
                    realExchangeAmount = exchangeResult.getAmount();
                    failNum++;
                }else{
                    try {
                        BigDecimal hasExchangeAmount = new BigDecimal(exchangedAmount);
                        if (BigDecimal.ZERO.compareTo(hasExchangeAmount) == 0) {
                            failNum++;
                            exchangeBeanType = ActivityConstant.EXCHANGE_BEAN_FAIL;
                            realExchangeAmount = exchangeResult.getAmount();
                        }else if (exchangeResult.getAmount().compareTo(hasExchangeAmount) > 0) {
                            realExchangeAmount = hasExchangeAmount;
                            exchangeBeanType = ActivityConstant.EXCHANGE_BEAN_MID;
                            successfulNum++;
                        }else{
                            exchangeBeanType = ActivityConstant.EXCHANGE_BEAN_SUC;
                            realExchangeAmount = exchangeResult.getAmount();
                            successfulNum++;
                        }
                    }catch (Exception e){
                        exchangeBeanType = ActivityConstant.EXCHANGE_BEAN_FAIL;
                        failNum++;
                        Logger.error("已兑换金额格式不正确", e);
                    }
                }

                MessageHeaderVo messageHeaderVo = updateResult(exchangeResult, exchangeBeanType, realExchangeAmount);
                messageHeaderVoList.add(messageHeaderVo);
            }
		}

        String message = MessageFormat.format("此次有效兑换数量{0}个，成功{1}个，失败{2}个", successfulNum + failNum, successfulNum, failNum);
        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), message);
        MessageUtil.getInstance().setMessageHeader(messageHeaderVoList);

	}

    private MessageHeaderVo updateResult(ExchangeResult exchangeResult, String exchangeBeanType, BigDecimal realExchangeAmount) {
        ExchangeScene exchangeScene = exchangeSceneService.findByScene(exchangeResult.getExchangeScene());
        Long rewardFlowId = exchangeResult.getRewardFlowId();
        RewardFlow rewardFlow = exchangeResultDao.findRewardFlowById(rewardFlowId);
        Long rewardAmt = 0L;
        String customerId = exchangeResult.getCustId();

        if (ActivityConstant.EXCHANGE_BEAN_SUC.equals(exchangeBeanType)) {
            genRewardFlow(rewardFlow, RewardFlowStatus.EXCHANGE_SUCC.getStatus(),rewardFlow.getRewardAmt(),rewardFlow.getMoney());

            rewardAmt = rewardFlow.getRewardAmt();
            exchangeResult.setStatus(ExchangeResultStatus.EXCHANGE_SUCC.getStatus());
            exchangeResult.setExchangedAmount(realExchangeAmount);

        }else if (ActivityConstant.EXCHANGE_BEAN_FAIL.equals(exchangeBeanType)) {
            genRewardFlow(rewardFlow, RewardFlowStatus.WITHDRAW_SUCC.getStatus(),rewardFlow.getRewardAmt(),rewardFlow.getMoney());

            rewardAmt = rewardFlow.getRewardAmt();
            exchangeResult.setStatus(ExchangeResultStatus.EXCHANGE_FAIL.getStatus());

        }else if (ActivityConstant.EXCHANGE_BEAN_MID.equals(exchangeBeanType)) {
            ExchangeRewardRule exchangeRewardRule = exchangeRewardRuleService.findRulesByRewardType(exchangeScene.getRewardType()).get(0);
            BigDecimal rate = exchangeRewardRule.getRate();
            rewardAmt = realExchangeAmount.divide(rate).longValue();
            RewardFlow newRewardFlow = genRewardFlow(rewardFlow, RewardFlowStatus.EXCHANGE_SUCC.getStatus(), rewardAmt, realExchangeAmount);

            exchangeResult.setRewardFlowId(newRewardFlow.getId());
            exchangeResult.setStatus(ExchangeResultStatus.EXCHANGE_SUCC.getStatus());
            exchangeResult.setExchangedAmount(realExchangeAmount);

            realExchangeAmount = rewardFlow.getMoney().subtract(realExchangeAmount);//返回金额
            rewardAmt = rewardFlow.getRewardAmt() - rewardAmt;
        }

        exchangeResult.setUpdateTime(DBHelper.getCurrentTime());
        exchangeResultDao.updateExchangeResult(exchangeResult);

        updateHoldReward(exchangeResult, exchangeScene, rewardAmt, realExchangeAmount, exchangeBeanType);

        MessageHeaderVo messageHeaderVo = new MessageHeaderVo();
        messageHeaderVo.setCustomerId(customerId);
        messageHeaderVo.buildParams(realExchangeAmount.toString());
        if (ActivityConstant.EXCHANGE_BEAN_FAIL.equals(exchangeBeanType)) {
            messageHeaderVo.setMessageType(DictConst.PUSH_TYPE_6);
        }else{
            messageHeaderVo.setMessageType(DictConst.PUSH_TYPE_2);
        }
        messageHeaderVo.setScene("EXC002");

        return messageHeaderVo;
    }

    private RewardFlow genRewardFlow(RewardFlow rewardFlow, Integer status, Long rewardAmt, BigDecimal money){
        try {
            RewardFlow revokeRewardFlow = (RewardFlow)BeanUtils.cloneBean(rewardFlow);
            revokeRewardFlow.setCreateTime(DBHelper.getCurrentTime());
            revokeRewardFlow.setStatus(status);
            revokeRewardFlow.setRewardAmt(rewardAmt);
            revokeRewardFlow.setMoney(money);
            revokeRewardFlow.setId(null);
            return exchangeResultDao.saveRewardFlow(revokeRewardFlow);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private synchronized void updateHoldReward(ExchangeResult exchangeResult, ExchangeScene exchangeScene, Long realRewardAmt, BigDecimal money, String exchangeBeanType){
        List<HoldReward> holdRewardList = exchangeResultDao.findHoldReward(exchangeResult.getCustId(), exchangeScene.getRewardType(), exchangeScene.getActivityType(), true);

        if (ActivityConstant.EXCHANGE_BEAN_SUC.equals(exchangeBeanType)) {
            for (HoldReward oldHoldReward : holdRewardList) {
                if (oldHoldReward.getHoldReward() <= 0) {
                    continue;
                }
                if (oldHoldReward.getHoldReward() - realRewardAmt < 0) {//持有奖励 < 兑换奖励
                    updateHoldReward(oldHoldReward, oldHoldReward.getHoldReward(), oldHoldReward.getHoldMoney());
                    realRewardAmt = realRewardAmt - oldHoldReward.getHoldReward();
                    money = money.subtract(oldHoldReward.getHoldMoney());
                }else{
                    updateHoldReward(oldHoldReward, realRewardAmt, money);
                }
            }
        }else {
            for (HoldReward oldHoldReward : holdRewardList) {
                if (oldHoldReward.getFrozenReward() <= 0) {
                    continue;
                }
                oldHoldReward.setFrozenMoney(oldHoldReward.getFrozenMoney().subtract(money));
                oldHoldReward.setFrozenReward(oldHoldReward.getFrozenReward() - realRewardAmt);
                exchangeResultDao.doUpdateHoldReward(oldHoldReward);
                return;
            }
        }
    }

    private void updateHoldReward(HoldReward oldHoldReward, Long rewardAmt, BigDecimal money) {
        oldHoldReward.setHoldMoney(oldHoldReward.getHoldMoney().subtract(money));
        oldHoldReward.setHoldReward(oldHoldReward.getHoldReward() - rewardAmt);
        oldHoldReward.setFrozenMoney(oldHoldReward.getFrozenMoney().subtract(money));
        oldHoldReward.setFrozenReward(oldHoldReward.getFrozenReward() - rewardAmt);

        exchangeResultDao.doUpdateHoldReward(oldHoldReward);
    }


    public ExchangeResult findExchangeResultById(Long id) {
		return exchangeResultDao.findById(id);
	}

	@Override
	public List<ExchangeBeanResultVo> findExchangeBeanList(PageVo pageVo) {
		ParameterService parameterService = new ParameterService();
		List<ExchangeBeanResultVo> list = exchangeResultDao.findExchangeBeanList(pageVo);

		for (ExchangeBeanResultVo exchangeBeanResultVo : list) {
			String exchangeMobile = exchangeBeanResultVo.getExchangeMobile();
            String carrierName = getCarrierName(parameterService, exchangeMobile);
			exchangeBeanResultVo.setCarrierName(carrierName);
			exchangeBeanResultVo.setStatusDesc(ExchangeResultStatus.getDescByStatus(Integer.valueOf(exchangeBeanResultVo.getStatus())));
		}

		return list;
	}

    private String getCarrierName(ParameterService parameterService, String exchangeMobile) {
        String carrierName = null;
        if (exchangeMobile.matches(parameterService.getParameterByName(ParameterConst.CMCC))) {
            carrierName = "移动";
        } else if (exchangeMobile.matches(parameterService.getParameterByName(ParameterConst.CTCC))) {
            carrierName = "电信";
        } else if (exchangeMobile.matches(parameterService.getParameterByName(ParameterConst.CUCC))) {
            carrierName = "联通";
        }
        return carrierName;
    }

    public void updateBatchResult(List<Long> exchangeResultIds){
        exchangeResultDao.updateBatchResult(exchangeResultIds);
    }

    /**
     *
     * @param pageVo
     * @return
     * @author Yuan
     */
	@Override
	public List<ExchangeResultVo> findRedPacketExchangeBy(PageVo pageVo) {
		return exchangeResultDao.findRedPacketExchangeBy(pageVo);
	}

    /**
     *
     * @param exchangeResultVos
     * @return
     * @author Yuan
     */
	@Override
	public int exportSuccessfully(List<ExchangeResultVo> exchangeResultVos) {
		return exchangeResultDao.exportSuccessfully(exchangeResultVos);
	}

    /**
     *
     * @param baseXlsDtos
     * @return
     * @author Yuan
     */
	@Override
	public List<MessageHeaderVo> checkExchangeResults(List<BaseXlsDto> baseXlsDtos) {
        List<MessageHeaderVo> messageHeaderVoList = Lists.newArrayList();
		int i = 0;
		for (BaseXlsDto  baseXlsDto: baseXlsDtos) {
			ExchangeResultXlsDto exchangeResultXlsDto = (ExchangeResultXlsDto) baseXlsDto;
			ExchangeResult exchangeResult =exchangeResultDao.findById(Long.valueOf(exchangeResultXlsDto.getId()));
			if (ExchangeResultStatus.AUDIT_PASS.getStatus() == (exchangeResult.getStatus())
					|| ExchangeResultStatus.EXCHANGEING.getStatus() == (exchangeResult.getStatus())
					|| ExchangeResultStatus.EXCHANGE_FAIL.getStatus() == (exchangeResult.getStatus())) {
				BigDecimal amount = exchangeResult.getAmount();
				String exchangedAmount = exchangeResultXlsDto.getExchangedAmount();
				amount = amount == null ? BigDecimal.ZERO : amount;
				BigDecimal amt = StringUtils.isBlank(exchangedAmount) ? BigDecimal.ZERO : new BigDecimal(exchangedAmount);
				exchangeResult.setExchangedAmount(amt);
				exchangeResult.setPaymentReceiptNo(exchangeResultXlsDto.getPaymentReceiptNo());
				if (amount.compareTo(amt) == 0) {
					exchangeResult.setStatus(ExchangeResultStatus.EXCHANGE_SUCC.getStatus());

					//fix bug T180
					updateStatus(exchangeResult);

					// send message
					MessageHeaderVo headerVo = new MessageHeaderVo();
					headerVo.setCustomerId(exchangeResult.getCustId());
					headerVo.buildParams(amount.toString());
                    headerVo.setMessageType(DictConst.PUSH_TYPE_2);
                    headerVo.setScene("EXC001");
                    messageHeaderVoList.add(headerVo);
					i++;
				} else {
					exchangeResult.setStatus(ExchangeResultStatus.EXCHANGE_FAIL.getStatus());
					exchangeResultDao.updateExchangeResult(exchangeResult);
				}
			}
		}
		Logger.info("[check success size is]" + i);
		return messageHeaderVoList;
	}

}
