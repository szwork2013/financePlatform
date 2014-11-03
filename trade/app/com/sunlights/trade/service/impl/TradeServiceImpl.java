package com.sunlights.trade.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.vo.Message;
import models.CustomerSession;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.trade.dal.TradeDao;
import com.sunlights.trade.dal.impl.TradeDaoImpl;
import model.Trade;
import com.sunlights.trade.service.TradeService;
import com.sunlights.trade.vo.TradeVo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: TradeServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class TradeServiceImpl implements TradeService {
    private TradeDao tradeDao = new TradeDaoImpl();
    private CustomerService customerService = new CustomerService();

    @Override
    public List<TradeVo> getTradeListByCustomerId(String customerId, String productType) {
        List<TradeVo> returnList = new ArrayList<TradeVo>();

        List<Trade> list = tradeDao.getTradeListByCustomerId(customerId, productType);
        for (Trade infoVo : list){
            returnList.add(getTradeVo(infoVo));
        }

        return returnList;
    }

    @Override
    public List<TradeVo> getTradeListByToken(String token, String productType) throws BusinessRuntimeException{
        CustomerSession customerSession = customerService.getCustomerSession(token);
        if (customerSession == null) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.LOGIN_TIMEOUT));
        }
        return getTradeListByCustomerId(customerSession.getCustomerId(), productType);
    }


    private TradeVo getTradeVo(Trade trade){
        TradeVo tradeVo = new TradeVo();

        tradeVo.setProductName(trade.getProductName());
//        tradeVo.setTradeTime(trade.getTradeTime());//TODO

        String tradeTypeDesc = "";//类型
        if ("3".equals(trade.getType())){// 1:申购 2:赎回 3:分红
            tradeTypeDesc += "回款";
//            tradeVo.setTradeAmount(ArithUtil.bigToScale2(infoVo.getYesterdayProfit()) + "元");
        }else{
            String tradeAmount = ArithUtil.bigToScale2(trade.getTradeAmount().abs());
            if ("1".equals(trade.getType())) {
                tradeTypeDesc += "购买";
                tradeVo.setTradeAmount("+" + tradeAmount + "元");
            }else if ("2".equals(trade.getType())) {
                tradeTypeDesc += "赎回";
                tradeVo.setTradeAmount("-" + tradeAmount + "元");
            }
//            String bankCardNo = infoVo.getBankCardNo();
//            if (bankCardNo != null){
//                bankCardNo = bankCardNo.substring(bankCardNo.length() - 4, bankCardNo.length());
//            }
//            tradeVo.setPaymentTypeDesc(infoVo.getBankName() + "(尾号" + bankCardNo + ")" + tradeVo.getTradeAmount());
        }
        tradeVo.setTradeTypeDesc(tradeTypeDesc);

        String tradeStatusDesc = "";//状态
        if ("1".equals(trade.getTradeStatus())) {//1：中、2：成功、3：失败
            tradeStatusDesc += tradeTypeDesc + "中";
        }else if ("2".equals(trade.getTradeStatus())) {
            tradeStatusDesc += tradeTypeDesc + "成功";
        }else{
            tradeStatusDesc += tradeTypeDesc + "失败";
        }
        tradeVo.setTradeStatusDesc(tradeStatusDesc);

        return tradeVo;
    }


}
