package com.sunlights.trade.service.impl;

import com.google.common.collect.Lists;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.Message;
import com.sunlights.trade.dal.TradeStatusChangeDao;
import com.sunlights.trade.dal.impl.TradeStatusChangeDaoImpl;
import com.sunlights.trade.service.TradeStatusChangeService;
import com.sunlights.trade.vo.TradeForecastDetailVo;
import com.sunlights.trade.vo.TradeForecastFormVo;
import models.Trade;
import models.TradeStatusChange;
import org.joda.time.LocalDate;
import services.DateCalcService;

import java.util.Date;
import java.util.List;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: TradeStatusChangeServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class TradeStatusChangeServiceImpl implements TradeStatusChangeService {

    private DateCalcService dateCalcService = new DateCalcService();
    private ParameterService parameterService = new ParameterService();
    private TradeStatusChangeDao tradeStatusChangeDao = new TradeStatusChangeDaoImpl();


    @Override
    public void createTradeStatusChange(Trade trade) {
        Date tradeTime = trade.getTradeTime();

        TradeForecastFormVo tradeForecastFormVo = new TradeForecastFormVo();
        tradeForecastFormVo.setApplyDateTime(CommonUtil.dateToString(trade.getTradeTime(), CommonUtil.DATE_FORMAT_LONG));
        tradeForecastFormVo.setTradeTime(tradeTime);
        tradeForecastFormVo.setFundCode(trade.getProductCode());
        tradeForecastFormVo.setBusinessType(trade.getType());
        tradeForecastFormVo.setApplySerial(trade.getTradeNo());

        if (DictConst.TRADE_TYPE_1.equals(trade.getType())) {//申购
            LocalDate confirmLocalDate = dateCalcService.getEndTradeDate(CommonUtil.dateToString(tradeTime, CommonUtil.DATE_FORMAT_LONG), 1);
            LocalDate earningLocalDate = dateCalcService.getEndTradeDate(CommonUtil.dateToString(tradeTime, CommonUtil.DATE_FORMAT_LONG), 2);

            createPurchaseTradeStatusChangeInfo(tradeForecastFormVo, confirmLocalDate, earningLocalDate);
        }else{//赎回
            createRedeemTradeStatusChangeInfo(tradeForecastFormVo);
        }
    }

    @Override
    public TradeStatusChange updateTradeStatusChange(TradeStatusChange tradeStatusChange) {
        return tradeStatusChangeDao.updateTradeStatusChange(tradeStatusChange);
    }

    @Override
    public List<TradeForecastDetailVo> findTradeStatusChangeList(TradeForecastFormVo tradeInfoFormVo) {
        String tradeNo = tradeInfoFormVo.getApplySerial();
        String productCode = tradeInfoFormVo.getFundCode();
        String tradeType = tradeInfoFormVo.getBusinessType();
        List<TradeForecastDetailVo> tradeStatusInfoVoList = tradeStatusChangeDao.findTradeStatusChangeList(tradeNo, productCode, tradeType);

        if (tradeStatusInfoVoList.isEmpty()) {//若未查询到数据 1数据遗失 2数米不同商户数据串用 重新组装并插入数据
            tradeStatusInfoVoList = buildForecastList(tradeInfoFormVo);
        }

        return tradeStatusInfoVoList;
    }

    private List<TradeForecastDetailVo> buildForecastList(TradeForecastFormVo tradeInfoFormVo) {
        List<TradeForecastDetailVo> tradeStatusInfoVoList = Lists.newArrayList();
        try {
            Date tradeTime = CommonUtil.stringToDate(tradeInfoFormVo.getApplyDateTime(), CommonUtil.DATE_FORMAT_SHUMI);
            tradeInfoFormVo.setTradeTime(tradeTime);
            if (DictConst.TRADE_TYPE_1.equals(tradeInfoFormVo.getBusinessType())) {
                LocalDate confirmLocalDate = dateCalcService.getEndTradeDate(CommonUtil.dateToString(tradeInfoFormVo.getTradeTime(), CommonUtil.DATE_FORMAT_LONG), 1);
                LocalDate earningLocalDate = dateCalcService.getEndTradeDate(CommonUtil.dateToString(tradeInfoFormVo.getTradeTime(), CommonUtil.DATE_FORMAT_LONG), 2);
                tradeStatusInfoVoList = buildPurchaseTradeInfo(tradeInfoFormVo, confirmLocalDate, earningLocalDate);

                createPurchaseTradeStatusChangeInfo(tradeInfoFormVo, confirmLocalDate, earningLocalDate);
            }else{
                tradeStatusInfoVoList = buildRedeemTradeInfo(tradeInfoFormVo);

                createRedeemTradeStatusChangeInfo(tradeInfoFormVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.DATETIME_FORMAT));
        }

        return tradeStatusInfoVoList;
    }


    private List<TradeForecastDetailVo> buildRedeemTradeInfo(TradeForecastFormVo tradeInfoFormVo){
        List<TradeForecastDetailVo> tradeStatusInfoVos = Lists.newArrayList();
        
        TradeForecastDetailVo tradeStatusInfoVo = new TradeForecastDetailVo();
        tradeStatusInfoVo.setTime(CommonUtil.dateToString(tradeInfoFormVo.getTradeTime(), CommonUtil.DATE_FORMAT_SHORT));
        tradeStatusInfoVo.setDesc(parameterService.getParameterByName(ParameterConst.TRADE_REDEEM_APPLY));
        tradeStatusInfoVo.setCompleteInd("Y");
        tradeStatusInfoVos.add(tradeStatusInfoVo);

        tradeStatusInfoVo = new TradeForecastDetailVo();
        tradeStatusInfoVo.setTime(parameterService.getParameterByName(ParameterConst.TRADE_REDEEM_CONFIRMTIME));
        tradeStatusInfoVo.setDesc(parameterService.getParameterByName(ParameterConst.TRADE_REDEEM_CONFIRM));
        tradeStatusInfoVo.setCompleteInd("N");
        tradeStatusInfoVos.add(tradeStatusInfoVo);

        return tradeStatusInfoVos;
    }

    private List<TradeForecastDetailVo> buildPurchaseTradeInfo(TradeForecastFormVo tradeInfoFormVo, LocalDate confirmLocalDate, LocalDate earningLocalDate){
        List<TradeForecastDetailVo> tradeStatusInfoVos = Lists.newArrayList();
        TradeForecastDetailVo tradeStatusInfoVo = new TradeForecastDetailVo();

        tradeStatusInfoVo.setTime(CommonUtil.dateToString(tradeInfoFormVo.getTradeTime(), CommonUtil.DATE_FORMAT_MM_DD_HH_MM));
        tradeStatusInfoVo.setDesc(parameterService.getParameterByName(ParameterConst.TRADE_PURCHASE_APPLY));
        tradeStatusInfoVo.setCompleteInd("Y");
        tradeStatusInfoVos.add(tradeStatusInfoVo);

        tradeStatusInfoVo = new TradeForecastDetailVo();
        tradeStatusInfoVo.setTime(confirmLocalDate.toString(CommonUtil.DATE_FORMAT_SHORT));
        tradeStatusInfoVo.setDesc(parameterService.getParameterByName(ParameterConst.TRADE_PURCHASE_CONFIRMINCOME));
        tradeStatusInfoVo.setCompleteInd("N");
        tradeStatusInfoVos.add(tradeStatusInfoVo);

        tradeStatusInfoVo = new TradeForecastDetailVo();
        tradeStatusInfoVo.setTime(earningLocalDate.toString(CommonUtil.DATE_FORMAT_SHORT));
        tradeStatusInfoVo.setDesc(parameterService.getParameterByName(ParameterConst.TRADE_PURCHASE_SHOWINCOME));
        tradeStatusInfoVo.setCompleteInd("N");
        tradeStatusInfoVos.add(tradeStatusInfoVo);

        return tradeStatusInfoVos;
    }


    private void createPurchaseTradeStatusChangeInfo(TradeForecastFormVo tradeForecastFormVo, LocalDate confirmDate, LocalDate earningDate){
        Date tradeTime = tradeForecastFormVo.getTradeTime();

        createTradeStatusChange(tradeForecastFormVo, CommonUtil.dateToString(tradeTime, CommonUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM), ParameterConst.TRADE_PURCHASE_APPLY, "1", "Y");
        createTradeStatusChange(tradeForecastFormVo, confirmDate.toString(CommonUtil.DATE_FORMAT_SHORT), ParameterConst.TRADE_PURCHASE_CONFIRMINCOME, "2", "N");
        createTradeStatusChange(tradeForecastFormVo, earningDate.toString(CommonUtil.DATE_FORMAT_SHORT), ParameterConst.TRADE_PURCHASE_SHOWINCOME, "4", "N");
    }

    private void createRedeemTradeStatusChangeInfo(TradeForecastFormVo tradeForecastFormVo){
        createTradeStatusChange(tradeForecastFormVo, CommonUtil.dateToString(tradeForecastFormVo.getTradeTime(), CommonUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM), ParameterConst.TRADE_REDEEM_APPLY, "1", "Y");
        createTradeStatusChange(tradeForecastFormVo, parameterService.getParameterByName(ParameterConst.TRADE_REDEEM_CONFIRMTIME), ParameterConst.TRADE_REDEEM_CONFIRM, "2", "N");
    }

    private void createTradeStatusChange(TradeForecastFormVo tradeForecastFormVo, String statusChangeTime, String statusName, String tradeStatus, String finishedStatus){
        Date tradeTime = tradeForecastFormVo.getTradeTime();

        TradeStatusChange tradeStatusChange = new TradeStatusChange();
        tradeStatusChange.setTradeNo(tradeForecastFormVo.getApplySerial());
        tradeStatusChange.setTradeTime(tradeTime);
        tradeStatusChange.setStatusChangeTime(statusChangeTime);
        tradeStatusChange.setStatusDesc(parameterService.getParameterByName(statusName));
        tradeStatusChange.setTradeStatus(tradeStatus);
        tradeStatusChange.setTradeType(tradeForecastFormVo.getBusinessType());
        tradeStatusChange.setProductCode(tradeForecastFormVo.getFundCode());
        tradeStatusChange.setFinishedStatus(finishedStatus);
        tradeStatusChange.setCreateTime(DBHelper.getCurrentTime());

        tradeStatusChangeDao.createTradeStatusChange(tradeStatusChange);
    }
}
