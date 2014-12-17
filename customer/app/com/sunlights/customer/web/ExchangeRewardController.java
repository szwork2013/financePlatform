package com.sunlights.customer.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.rewardrules.ActivityHandlerService;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.vo.*;
import models.CustomerSession;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

/**
 * Created by Administrator on 2014/12/17.
 */
public class ExchangeRewardController extends ActivityBaseController {

    private ActivityHandlerService activityHandlerService = new ActivityHandlerService();

    public Result queryExchangeScenes() {

        //TODO
        ExchangeSceneListVo exchangeSceneListVo = new ExchangeSceneListVo();
        ExchangeSceneVo exchangeSceneVo = new ExchangeSceneVo();
        exchangeSceneVo.setId("123");
        exchangeSceneVo.setTitle("首次购买送红包");
        exchangeSceneVo.setDetail("首次购买送红包20元");
        exchangeSceneVo.setLogo("http://192.168.1.97/activity/images/loggon_icon.png");

        exchangeSceneListVo.addRecord(exchangeSceneVo);

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.EXCHANGE_SCENE_QUERY_SUCC), exchangeSceneListVo);

        Logger.debug("获取活动的信息：" + messageUtil.toJson().toString());
        return ok(messageUtil.toJson());
    }

    public Result prepareDataBeforeExchange() {

        //TODO
        Data4ExchangeVo data4ExchangeVo = new Data4ExchangeVo();
        data4ExchangeVo.setCanPayed("361.00");
        data4ExchangeVo.setMaxPayed("361.00");
        data4ExchangeVo.setAccountDate("2014-12-16 12:00:00");
        data4ExchangeVo.setSummary("首次购买20元");

        Data4ExchangeItem item = new Data4ExchangeItem();
        item.setTitle("首次购买送红包");
        item.setLogo("http://192.168.1.97/activity/images/loggon_icon.png");
        item.setDetail("首次购买送红包20元");
        item.setCreateTime("2014-12-16 12:00:00");

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.BEFORE_EXCHANGE_QUERY_SUCC), data4ExchangeVo);

        Logger.debug("获取活动的信息：" + messageUtil.toJson().toString());
        return ok(messageUtil.toJson());
    }

    /**
     * 奖励的兑换
     *
     * @return
     */
    public Result exchangeReward() {

        String token = getToken();
        ExchangeParamter exchangeParamter = getExchangeParamter();

        Message message = null;

        CustomerSession customerSession = customerService.getCustomerSession(token);
        String custNo = customerSession.getCustomerId();

        ActivityRequestVo requestVo = new ActivityRequestVo();
        ActivityResponseVo responseVo = new ActivityResponseVo();

        requestVo.setCustId(custNo);

        requestVo.set("exchangeSceneId", exchangeParamter.getId());
        requestVo.set("bankName", exchangeParamter.getBankName());
        requestVo.set("bankCardNo", exchangeParamter.getBankCardNo());
        requestVo.set("exchangeAmt", exchangeParamter.getExchangeAmt());
        requestVo.set("phone", exchangeParamter.getPhone());

        activityHandlerService.service(requestVo, responseVo);

        message = responseVo.getMessage();

        if(MsgCode.OBTAIN_SUCC.getCode().equals(message.getCode())) {
            //TODO
            Logger.info("兑换成功");
        } else {
            //TODO
            Logger.debug("兑换失败 ：" + message.getSummary());
        }

        return ok(Json.toJson("succ"));
    }

}
