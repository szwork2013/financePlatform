package com.sunlights.trade.service.impl;

import com.sunlights.common.utils.ArithUtil;
import com.sunlights.customer.models.CustomerSession;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.trade.dal.TradeDao;
import com.sunlights.trade.service.TradeService;
import com.sunlights.trade.vo.TradeInfoVo;
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

public class TradeServiceImpl implements TradeService{

    private TradeDao tradeDao;


    private CustomerService customerService;

    @Override
    public List<TradeVo> getTradeListByCustomerId(String customerId, String productType) {
        List<TradeVo> returnList = new ArrayList<TradeVo>();

        List<TradeInfoVo> list = tradeDao.getTradeListByCustomerId(customerId, productType);
        for (TradeInfoVo infoVo : list){
            returnList.add(getTradeVo(infoVo));
        }

        return returnList;
    }

    @Override
    public List<TradeVo> getTradeListByToken(String token, String productType) {
        CustomerSession customerSession = customerService.getCustomerSession(token);
        if (customerSession == null) {
            return null;
        }
        return getTradeListByCustomerId(customerSession.getCustomerId(), productType);
    }


    private TradeVo getTradeVo(TradeInfoVo infoVo){
        TradeVo tradeVo = new TradeVo();

        tradeVo.setProductName(infoVo.getProductName());
        tradeVo.setTradeTime(infoVo.getTradeTime());

        String tradeTypeDesc = "";//类型
        if ("3".equals(infoVo.getTradeType())){// 1:申购 2:赎回 3:分红
            tradeTypeDesc += "回款";
            tradeVo.setTradeAmount(ArithUtil.bigToScale2(infoVo.getYesterdayProfit()) + "元");
        }else{
            if ("1".equals(infoVo.getTradeType())) {
                tradeTypeDesc += "投标";
            }else if ("2".equals(infoVo.getTradeType())) {
                tradeTypeDesc += "赎回";
            }
            tradeVo.setTradeAmount(ArithUtil.bigToScale2(infoVo.getSubCapital()) + "元");
            String bankCardNo = infoVo.getBankCardNo();
            if (bankCardNo != null){
                bankCardNo = bankCardNo.substring(bankCardNo.length() - 4, bankCardNo.length());
            }
            tradeVo.setPaymentTypeDesc(infoVo.getBankName() + "(尾号" + bankCardNo + ")" + tradeVo.getTradeAmount());
        }
        tradeVo.setTradeType(infoVo.getTradeType());
        tradeVo.setTradeTypeDesc(tradeTypeDesc);

        String tradeStatusDesc = "";//状态
        if ("1".equals(infoVo.getTradeStatus())) {//1：中、2：成功、3：失败
            tradeStatusDesc += tradeTypeDesc + "中";
        }else if ("2".equals(infoVo.getTradeStatus())) {
            tradeStatusDesc += tradeTypeDesc + "成功";
        }else{
            tradeStatusDesc += tradeTypeDesc + "失败";
        }
        tradeVo.setTradeStatus(infoVo.getTradeStatus());
        tradeVo.setTradeStatusDesc(tradeStatusDesc);
        tradeVo.setTradeNo(infoVo.getTradeNo());

        return tradeVo;
    }


}
