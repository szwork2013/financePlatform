package com.sunlights.trade.web;

import com.sunlights.customer.service.impl.CustomerService;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ShuMiTradeController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class ShuMiTradeController extends Controller{
    private CustomerService customerService = new CustomerService();

    public Result tradeOrder(){
//        Logger.info("----------tradeOrder start ------------");
//
//        customerService.validateCustomerSession(request(),session(),response());
//
//        TradeFormVo tradeFormVo = tradeFormVoForm.bindFromRequest().get();
//        String token = request().cookie(AppConst.TOKEN).value();
//
//        TotalCapitalInfo totalCapitalInfo = tradeService.tradeFundOrder(tradeFormVo, token);
//
//        JsonNode json = MessageUtil.getInstance().msgToJson(new Message(MsgCode.TRADE_ORDER_SUCCESS), totalCapitalInfo);
//        Logger.info("----------tradeOrder end: ------------\n" + json);

        return ok();
    }
}
