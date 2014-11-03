package com.sunlights.trade.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.trade.service.TradeService;
import com.sunlights.trade.service.impl.TradeServiceImpl;
import com.sunlights.trade.vo.TradeVo;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.Map;

/**
 * <p>Project: fsp</p>
 * <p>Title: WebTradeSrvice.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class TradeController extends Controller{

    private TradeService tradeService = new TradeServiceImpl();

    public Result getTradeList(){
        Logger.info("----------getTradeList start ------------");
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();

        Map<String, String> params = Form.form().bindFromRequest().data();
        String productType = params.get("productType");

        List<TradeVo> list = tradeService.getTradeListByToken(token, productType);

        Message message = new Message(MsgCode.OPERATE_SUCCESS);
        JsonNode json = MessageUtil.getInstance().msgToJson(message, list);
        Logger.info("----------getTradeList end" + json);
        return ok(json);
    }


}
