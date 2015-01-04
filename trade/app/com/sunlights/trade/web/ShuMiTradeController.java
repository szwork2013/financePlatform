package com.sunlights.trade.web;

import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.action.MsgCenterAction;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.trade.service.ShuMiTradeService;
import com.sunlights.trade.service.impl.ShuMiTradeServiceImpl;
import com.sunlights.trade.vo.ShuMiTradeFormVo;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import static play.data.Form.form;

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
    

    @With(MsgCenterAction.class)
    public Result tradeOrder(){
        Logger.info("----------tradeOrder start ------------");
        Logger.debug(">>tradeOrder params：" + Json.toJson(form().bindFromRequest().data()));
        customerService.validateCustomerSession(request(),session(),response());

        String token = request().cookie(AppConst.TOKEN).value();
        ShuMiTradeFormVo tradeFormVo = shuMiTradeFormVoForm.bindFromRequest().get();
        Logger.debug("tradeFormVo:" + Json.toJson(tradeFormVo));

        MessageUtil.getInstance().setMessage(new Message(MsgCode.TRADE_ORDER_SUCCESS));

        String headerMsg = shuMiTradeService.shuMiTradeOrder(tradeFormVo, token);

        Logger.debug(">>tradeOrder return：" +  MessageUtil.getInstance().toJson());

        response().setHeader(AppConst.HEADER_MSG, headerMsg);

        return ok(MessageUtil.getInstance().toJson());
    }

    @With(MsgCenterAction.class)
    public Result tradeRedeem(){
        Logger.info("----------tradeRedeem start ------------");
        Logger.debug(">>tradeRedeem params：" + Json.toJson(form().bindFromRequest().data()));
        customerService.validateCustomerSession(request(),session(),response());

        String token = request().cookie(AppConst.TOKEN).value();
        ShuMiTradeFormVo tradeFormVo = shuMiTradeFormVoForm.bindFromRequest().get();
        Logger.debug("tradeFormVo:" + Json.toJson(tradeFormVo));

        MessageUtil.getInstance().setMessage(new Message(MsgCode.TRADE_REDEEM_SUCCESS));

        String headerMsg = shuMiTradeService.shuMiTradeRedeem(tradeFormVo, token);

        Logger.debug(">>tradeRedeem return：" +  MessageUtil.getInstance().toJson());

        response().setHeader(AppConst.HEADER_MSG, headerMsg);

        return ok(MessageUtil.getInstance().toJson());
    }
}
