package com.sunlights.trade.web;

import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.trade.service.ShuMiTradeService;
import com.sunlights.trade.service.impl.ShuMiTradeServiceImpl;
import com.sunlights.trade.vo.ShuMiTradeFormVo;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
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
@Transactional
public class ShuMiTradeController extends Controller{
    private Form<ShuMiTradeFormVo> shuMiTradeFormVoForm = Form.form(ShuMiTradeFormVo.class);
    
    private CustomerService customerService = new CustomerService();
    private ShuMiTradeService shuMiTradeService = new ShuMiTradeServiceImpl();
    

    public Result tradeOrder(){
        Logger.info("----------tradeOrder start ------------");

        customerService.validateCustomerSession(request(),session(),response());

        String token = request().cookie(AppConst.TOKEN).value();
        ShuMiTradeFormVo tradeFormVo = shuMiTradeFormVoForm.bindFromRequest().get();

        shuMiTradeService.shuMiTradeOrder(tradeFormVo, token);

        MessageUtil.getInstance().setMessage(new Message(MsgCode.TRADE_ORDER_SUCCESS));

        Logger.info("----------tradeOrder end: ------------\n");

        return ok(MessageUtil.getInstance().toJson());
    }

    public Result tradeRedeem(){
        Logger.info("----------tradeRedeem start ------------");

        customerService.validateCustomerSession(request(),session(),response());

        String token = request().cookie(AppConst.TOKEN).value();
        ShuMiTradeFormVo tradeFormVo = shuMiTradeFormVoForm.bindFromRequest().get();

        shuMiTradeService.shuMiTradeRedeem(tradeFormVo, token);

        MessageUtil.getInstance().setMessage(new Message(MsgCode.TRADE_REDEEM_SUCCESS));

        Logger.info("----------tradeRedeem end: ------------\n");

        return ok(MessageUtil.getInstance().toJson());
    }
}
