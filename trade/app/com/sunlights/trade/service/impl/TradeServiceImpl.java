package com.sunlights.trade.service.impl;

import com.sunlights.account.service.CapitalService;
import com.sunlights.account.service.impl.CapitalServiceImpl;
import com.sunlights.account.vo.HoldCapitalVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.trade.dal.TradeDao;
import com.sunlights.trade.dal.impl.TradeDaoImpl;
import com.sunlights.trade.service.TradeService;
import com.sunlights.trade.vo.CapitalProductTradeVo;
import com.sunlights.trade.vo.TradeFormVo;
import com.sunlights.trade.vo.TradeVo;
import models.CustomerSession;

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
    private CapitalService capitalService = new CapitalServiceImpl();

    @Override
    public List<TradeVo> getTradeListByToken(String token, TradeFormVo tradeFormVo, PageVo pageVo) throws BusinessRuntimeException{

        CustomerSession customerSession = customerService.getCustomerSession(token);
        if (customerSession == null) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.LOGIN_TIMEOUT));
        }
        List<TradeVo> list = tradeDao.getTradeListByCustomerId(customerSession.getCustomerId(), tradeFormVo ,pageVo);
        return list;
    }

    public CapitalProductTradeVo findCapitalProductDetailTrade(String token, TradeFormVo tradeFormVo){
        CommonUtil.getInstance().validateParams(tradeFormVo.getPrdType(), tradeFormVo.getPrdCode());
        PageVo pageVo = new PageVo();
        pageVo.setPageSize(3);
        List<TradeVo> list = getTradeListByToken(token, tradeFormVo, pageVo);
        HoldCapitalVo holdCapitalVo = capitalService.findCapitalProductDetail(tradeFormVo.getPrdType(), tradeFormVo.getPrdCode());

        CapitalProductTradeVo capitalProductTradeVo = new CapitalProductTradeVo();
        capitalProductTradeVo.setList(list);
        capitalProductTradeVo.setHoldCapitalVo(holdCapitalVo);
        capitalProductTradeVo.setTradeCount(pageVo.getCount());

        return capitalProductTradeVo;
    }




}
