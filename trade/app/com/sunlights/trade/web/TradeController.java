package com.sunlights.trade.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.msg.Message;
import com.sunlights.common.utils.msg.MessageUtil;
import com.sunlights.trade.service.TradeService;
import com.sunlights.trade.vo.TradeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import play.data.Form;
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


public class TradeController {
    private Logger logger = LoggerFactory.getLogger(TradeController.class);


    private TradeService tradeService;

    public Result getTradeList(){
        logger.info("----------getTradeList start ------------");
        Http.Cookie cookie = Controller.request().cookie(AppConst.TOKEN);
        String token = cookie == null ? null : cookie.value();

        Map<String, String> params = Form.form().bindFromRequest().data();
        String productType = params.get("productType");

        List<TradeVo> list = tradeService.getTradeListByToken(token, productType);

        JsonNode json = MessageUtil.getInstance().msgToJson(new Message(MsgCode.OPERATE_SUCCESS), list);
        logger.info("----------getTradeList end" + json);
        return Controller.ok(json);
    }


}
