package com.sunlights.customer.web;

import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.MessageHeaderVo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.action.MsgCenterAction;
import com.sunlights.customer.service.ExchangeSceneService;
import com.sunlights.customer.service.impl.ExchangeSceneServiceImpl;
import com.sunlights.customer.service.rewardrules.ActivityHandlerService;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.vo.*;
import models.CustomerSession;
import models.ExchangeScene;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Result;
import play.mvc.With;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/17.
 */
@Transactional
public class ExchangeRewardController extends ActivityBaseController {

    private ActivityHandlerService activityHandlerService = new ActivityHandlerService();

    private ExchangeSceneService exchangeSceneService = new ExchangeSceneServiceImpl();

    public Result queryExchangeScenes() {
        String custId = getCustomerSession().getCustomerId();
        ExchangeParamter exchangeParamter = getExchangeParamter();
        PageVo pageVo = new PageVo();
        pageVo.setIndex(exchangeParamter.getIndex());
        pageVo.setPageSize(exchangeParamter.getPageSize());

        Double version = CommonUtil.getCurrentVersion(request());

        List<ExchangeSceneVo> result = exchangeSceneService.loadSceneByCustId(custId, pageVo);

        if (version < AppConst.APP_VERSION_1_2) {
            List<ExchangeSceneVo> list = Lists.newArrayList();
            for (ExchangeSceneVo exchangeSceneVo : result) {
                if (exchangeSceneVo.getExchangeType().equals("0")) {//<1.2版本只显示  红包取现
                    list.add(exchangeSceneVo);
                }
            }
            pageVo.setList(list);
            pageVo.setCount(list.size());
        }else{
            pageVo.setList(result);
        }


        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.EXCHANGE_SCENE_QUERY_SUCC), pageVo);

        Logger.debug("获取活动的信息：" + messageUtil.toJson().toString());
        return ok(messageUtil.toJson());
    }

    public Result prepareDataBeforeExchange() {

        String custId = getCustomerSession().getCustomerId();
        ExchangeParamter exchangeParamter = getExchangeParamter();
        Data4ExchangeVo result = exchangeSceneService.prepareData4Exchange(custId, exchangeParamter.getId());

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.BEFORE_EXCHANGE_QUERY_SUCC), result);

        Logger.debug("获取活动的信息：" + messageUtil.toJson().toString());
        return ok(messageUtil.toJson());
    }

    public Result prepareDataBeforeBeanExchange(){
        DataBean4ExchangeVo dataBean4ExchangeVo = exchangeSceneService.getDataBean4ExchangeVo();

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.BEAN_BEFORE_EXCHANGE_SUCC), dataBean4ExchangeVo);

        Logger.debug(">>prepareDataBeforeBeanExchange return：" + messageUtil.toJson().toString());
        return ok(messageUtil.toJson());
    }

    /**
     * 奖励的兑换
     *
     * @return
     */
    @With(MsgCenterAction.class)
    public Result exchangeReward() {

        String token = getToken();
        ExchangeParamter exchangeParamter = getExchangeParamter();

        Message message = null;

        CustomerSession customerSession = customerService.getCustomerSession(token);
        String custNo = customerSession.getCustomerId();
        ActivityRequestVo requestVo = new ActivityRequestVo();
        ActivityResponseVo responseVo = new ActivityResponseVo();

        String id = exchangeParamter.getId();

        ExchangeScene exchangeScene = exchangeSceneService.findById(id);
        requestVo.set("exchangeScene", exchangeScene);
        requestVo.setCustId(custNo);
        requestVo.setScene(exchangeScene.getScene());

        requestVo.set("exchangeSceneId", exchangeParamter.getId());
        requestVo.set("bankName", exchangeParamter.getBankName());
        requestVo.set("bankCardNo", exchangeParamter.getBankCard());
        requestVo.set("exchangeAmt", exchangeParamter.getAmount());
        requestVo.set("phone", exchangeParamter.getPhone());
        requestVo.set("exchangeBeanNum", exchangeParamter.getExchangeBeanNum());

        activityHandlerService.service(requestVo, responseVo);

        message = responseVo.getMessage();
        ExchangeResultVo resultVo = new ExchangeResultVo();
        resultVo.setPayed(exchangeParamter.getAmount());
        resultVo.setAccountDate(exchangeSceneService.calcAccountDate(exchangeScene.getTimeLimit(), null, true));

        if(MsgCode.OPERATE_SUCCESS.getCode().equals(message.getCode())) {
            if (ActivityConstant.ACTIVITY_EXCHANGE_BEAN_SCENE_CODE.equals(exchangeScene.getScene())) {
                message.setCode(MsgCode.EXCHANGE_SUCC.getCode());
                messageUtil.setMessage(message);
            }else{
                message.setCode(MsgCode.EXCHANGE_SUCC.getCode());
                messageUtil.setMessage(message, resultVo);
            }
            Logger.info("兑换成功");
        } else {
            message.setSeverity(Severity.ERROR);
            messageUtil.setMessage(message, null);
            Logger.debug("兑换失败 ：" + message.getSummary());
        }

        //发送消息
        List<MessageHeaderVo> messageHeaderVos = responseVo.getMessageHeaderVos();
        response().setHeader(AppConst.HEADER_MSG, MessageUtil.getInstance().setMessageHeader(messageHeaderVos));

        return ok(messageUtil.toJson());
    }

}
