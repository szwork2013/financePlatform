package com.sunlights.trade.web;

import com.sunlights.common.AppConst;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.action.MsgCenterAction;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.service.rewardrules.ActivityHandlerService;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.trade.service.ShuMiTradeService;
import com.sunlights.trade.service.TradeStatusChangeService;
import com.sunlights.trade.service.impl.ShuMiTradeServiceImpl;
import com.sunlights.trade.service.impl.TradeStatusChangeServiceImpl;
import com.sunlights.trade.vo.ShuMiTradeFormVo;
import com.sunlights.trade.vo.TradeForecastDetailVo;
import com.sunlights.trade.vo.TradeForecastFormVo;
import com.sunlights.trade.vo.TradeForecastVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import models.CustomerSession;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import java.util.List;

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
@Api(value = "/trade", description = "交易服务")
@Transactional
public class ShuMiTradeController extends Controller {
    private Form<ShuMiTradeFormVo> shuMiTradeFormVoForm = Form.form(ShuMiTradeFormVo.class);

    private CustomerService customerService = new CustomerService();
    private ShuMiTradeService shuMiTradeService = new ShuMiTradeServiceImpl();
    private TradeStatusChangeService tradeStatusChangeService = new TradeStatusChangeServiceImpl();
    private ActivityHandlerService activityHandlerService = new ActivityHandlerService();


    @With(MsgCenterAction.class)
    public Result tradeOrder() {
        Logger.info("----------tradeOrder start ------------");
        Logger.debug(">>tradeOrder params：" + Json.toJson(form().bindFromRequest().data()));
        CustomerSession customerSession = customerService.validateCustomerSession(request(), session(), response());

        String token = request().cookie(AppConst.TOKEN).value();
        ShuMiTradeFormVo tradeFormVo = shuMiTradeFormVoForm.bindFromRequest().get();
        Logger.debug("tradeFormVo:" + Json.toJson(tradeFormVo));

        MessageUtil.getInstance().setMessage(new Message(MsgCode.TRADE_ORDER_SUCCESS));

        List<MessageHeaderVo> tradeHeaderMsg = shuMiTradeService.shuMiTradeOrder(tradeFormVo, token);

        Logger.debug(">>tradeOrder return：" + MessageUtil.getInstance().toJson());

        List<MessageHeaderVo> ActivityMessageHeaderVos = takeActivity(customerSession.getCustomerId(), tradeFormVo);
        tradeHeaderMsg.addAll(ActivityMessageHeaderVos);
        response().setHeader(AppConst.HEADER_MSG, MessageUtil.getInstance().setMessageHeader(tradeHeaderMsg));

        return ok(MessageUtil.getInstance().toJson());
    }


    private List<MessageHeaderVo> takeActivity(String custNo, ShuMiTradeFormVo tradeFormVo) {
        Logger.debug("参加活动开始");
        ActivityRequestVo requestVo = new ActivityRequestVo();
        ActivityResponseVo responseVo = new ActivityResponseVo();

        requestVo.setCustId(custNo);
        requestVo.setScene(ActivityConstant.ACTIVITY_PURCHASE_SCENE_CODE);

        //购买场景时会用到的字段
        requestVo.set("prdCode", tradeFormVo.getFundCode());
        requestVo.set("supplySum", tradeFormVo.getApplySum());

        activityHandlerService.service(requestVo, responseVo);

        Logger.debug("参加活动结束");
        List<MessageHeaderVo> activityMessageHeaderVos = responseVo.getMessageHeaderVos();
        return activityMessageHeaderVos;
    }

    @With(MsgCenterAction.class)
    public Result tradeRedeem() {
        Logger.info("----------tradeRedeem start ------------");
        Logger.debug(">>tradeRedeem params：" + Json.toJson(form().bindFromRequest().data()));
        customerService.validateCustomerSession(request(), session(), response());

        String token = request().cookie(AppConst.TOKEN).value();
        ShuMiTradeFormVo tradeFormVo = shuMiTradeFormVoForm.bindFromRequest().get();
        Logger.debug("tradeFormVo:" + Json.toJson(tradeFormVo));

        MessageUtil.getInstance().setMessage(new Message(MsgCode.TRADE_REDEEM_SUCCESS));

        String headerMsg = shuMiTradeService.shuMiTradeRedeem(tradeFormVo, token);

        Logger.debug(">>tradeRedeem return：" + MessageUtil.getInstance().toJson());

        response().setHeader(AppConst.HEADER_MSG, headerMsg);

        return ok(MessageUtil.getInstance().toJson());
    }

    @With(MsgCenterAction.class)
    public Result tradeQuickRedeem() {
        Logger.info("----------tradeQuickRedeem start ------------");
        Logger.debug(">>tradeQuickRedeem params：" + Json.toJson(form().bindFromRequest().data()));
        customerService.validateCustomerSession(request(), session(), response());

        String token = request().cookie(AppConst.TOKEN).value();
        ShuMiTradeFormVo tradeFormVo = shuMiTradeFormVoForm.bindFromRequest().get();
        Logger.debug("tradeFormVo:" + Json.toJson(tradeFormVo));

        MessageUtil.getInstance().setMessage(new Message(MsgCode.TRADE_REDEEM_SUCCESS));

        String headerMsg = shuMiTradeService.shuMiTradeRedeem(tradeFormVo, token);

        Logger.debug(">>tradeQuickRedeem return：" + MessageUtil.getInstance().toJson());

        response().setHeader(AppConst.HEADER_MSG, headerMsg);

        return ok(MessageUtil.getInstance().toJson());
    }

    @ApiOperation(value = "获取交易收益日期列表",
            notes = "MessageVo 的value是TradeForecastVo对象", nickname = "tradeforecast",
            response = MessageVo.class, httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ApplySerial", value = "交易流水号", required = true, paramType = "form"),
            @ApiImplicitParam(name = "FundCode", value = "产品编码", required = true, paramType = "form"),
            @ApiImplicitParam(name = "TradeAccount", value = "交易账号", paramType = "form"),
            @ApiImplicitParam(name = "Amount", value = "交易金额", paramType = "form"),
            @ApiImplicitParam(name = "ApplyDateTime", value = "交易时间(yyyy-MM-dd'T'HH:mm:ss.sss)", required = true, paramType = "form"),
            @ApiImplicitParam(name = "Status", value = "交易状态（9待处理）", paramType = "form"),
            @ApiImplicitParam(name = "BusinessType", value = "交易类型（022充值，024赎回）", required = true, paramType = "form")
    })
    public Result tradeForecast() {
        Logger.info("----------tradeInfoList start ------------");
        TradeForecastFormVo tradeInfoFormVo = Form.form(TradeForecastFormVo.class).bindFromRequest().get();
        Logger.debug(">>tradeInfoList params：" + Json.toJson(tradeInfoFormVo));

        CommonUtil.getInstance().validateParams(tradeInfoFormVo.getApplySerial(), tradeInfoFormVo.getFundCode(), tradeInfoFormVo.getBusinessType(), tradeInfoFormVo.getApplyDateTime());

        customerService.validateCustomerSession(request(), session(), response());

        if ("022".equals(tradeInfoFormVo.getBusinessType())) {//申购
            tradeInfoFormVo.setBusinessType(DictConst.TRADE_TYPE_1);
        }else if ("024".equals(tradeInfoFormVo.getBusinessType())) {//赎回
            tradeInfoFormVo.setBusinessType(DictConst.TRADE_TYPE_2);
        }

        List<TradeForecastDetailVo> tradeStatusInfoVos = tradeStatusChangeService.findTradeStatusChangeList(tradeInfoFormVo);
        TradeForecastVo tradeInfo = new TradeForecastVo();
        tradeInfo.setApplySerial(tradeInfoFormVo.getApplySerial());
        tradeInfo.setFundCode(tradeInfoFormVo.getFundCode());
        tradeInfo.setTradeAccount(tradeInfoFormVo.getTradeAccount());
        tradeInfo.setList(tradeStatusInfoVos);

        MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), tradeInfo);
        Logger.debug(">>tradeInfoList return：" + MessageUtil.getInstance().toJson());

        return ok(MessageUtil.getInstance().toJson());
    }

}
