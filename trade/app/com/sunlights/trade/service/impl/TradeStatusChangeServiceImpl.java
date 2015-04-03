package com.sunlights.trade.service.impl;

import com.sunlights.common.DictConst;
import com.sunlights.common.ParameterConst;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
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
        if (DictConst.TRADE_TYPE_1.equals(trade.getType())) {//申购
            createPurchaseTradeStatusChangeInfo(trade);
        }else{//赎回
            createRedeemTradeStatusChangeInfo(trade);
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
        if ("022".equals(tradeType)) {//申购
            tradeType = DictConst.TRADE_TYPE_1;
        }else if ("024".equals(tradeType)) {//赎回
            tradeType = DictConst.TRADE_TYPE_2;
        }
        List<TradeForecastDetailVo> tradeStatusInfoVoList = tradeStatusChangeDao.findTradeStatusChangeList(tradeNo, productCode, tradeType);
        return tradeStatusInfoVoList;
    }


    private void createPurchaseTradeStatusChangeInfo(Trade trade){
        Date tradeTime = trade.getTradeTime();

        LocalDate confirmLocalDate = dateCalcService.getEndTradeDate(CommonUtil.dateToString(tradeTime, CommonUtil.DATE_FORMAT_LONG), 1);
        LocalDate earningLocalDate = dateCalcService.getEndTradeDate(CommonUtil.dateToString(tradeTime, CommonUtil.DATE_FORMAT_LONG), 2);

        createTradeStatusChange(trade, CommonUtil.dateToString(tradeTime, CommonUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM), ParameterConst.TRADE_PURCHASE_APPLY, "1", "Y");
        createTradeStatusChange(trade, confirmLocalDate.toString(CommonUtil.DATE_FORMAT_SHORT), ParameterConst.TRADE_PURCHASE_CONFIRMINCOME, "2", "N");
        createTradeStatusChange(trade, earningLocalDate.toString(CommonUtil.DATE_FORMAT_SHORT), ParameterConst.TRADE_PURCHASE_SHOWINCOME, "4", "N");
    }

    private void createRedeemTradeStatusChangeInfo(Trade trade){
        createTradeStatusChange(trade, CommonUtil.dateToString(trade.getTradeTime(), CommonUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM), ParameterConst.TRADE_REDEEM_APPLY, "1", "Y");
        createTradeStatusChange(trade, parameterService.getParameterByName(ParameterConst.TRADE_REDEEM_CONFIRMTIME), ParameterConst.TRADE_REDEEM_CONFIRM, "2", "N");
    }

    private void createTradeStatusChange(Trade trade, String statusChangeTime, String statusName, String tradeStatus, String finishedStatus){
        Date tradeTime = trade.getTradeTime();
        String tradeNo = trade.getTradeNo();

        TradeStatusChange tradeStatusChange = new TradeStatusChange();
        tradeStatusChange.setTradeNo(tradeNo);
        tradeStatusChange.setTradeTime(tradeTime);
        tradeStatusChange.setStatusChangeTime(statusChangeTime);
        tradeStatusChange.setStatusDesc(parameterService.getParameterByName(statusName));
        tradeStatusChange.setTradeStatus(tradeStatus);
        tradeStatusChange.setTradeType(trade.getType());
        tradeStatusChange.setProductCode(trade.getProductCode());
        tradeStatusChange.setFinishedStatus(finishedStatus);
        tradeStatusChange.setCreateTime(DBHelper.getCurrentTime());

        tradeStatusChangeDao.createTradeStatusChange(tradeStatusChange);
    }
}
