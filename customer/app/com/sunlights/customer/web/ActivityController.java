package com.sunlights.customer.web;



import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.factory.ActivityListQueryFactory;
import com.sunlights.customer.service.ActivityListQuery;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.impl.ActivityServiceImpl;
import com.sunlights.customer.service.rewardrules.ActivityHandlerService;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import com.sunlights.customer.vo.*;
import models.CustomerSession;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/13.
 */
@Transactional
public class ActivityController extends ActivityBaseController  {
    private ActivityService activityService = new ActivityServiceImpl();

    private ActivityHandlerService activityHandlerService = new ActivityHandlerService();

    public Result getActivityList() {
        String custId = "";

        try {
            custId = getCustomerSession().getCustomerId();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.debug("没有登录，查询活动中心");
        }

        ActivityParamter activityParamter = getActivityParamter();
        PageVo pageVo = new PageVo();
        pageVo.setIndex(activityParamter.getIndex());
        pageVo.setPageSize(activityParamter.getPageSize());
        String filter = activityParamter.getFilter();

        if(StringUtils.isEmpty(filter)) {
            filter = ActivityConstant.ACTIVITY_QUERY_CENTER;
        }
        Logger.debug("filter = " + filter);

        ActivityQueryContext context = new ActivityQueryContext();
        context.setPageVo(pageVo);
        context.setCustNo(custId);

        ActivityListQuery activityListQuery = ActivityListQueryFactory.getQueryStyle(filter);

        if(activityListQuery == null) {
            Logger.error("不支持的活动查询的方式");
            return ok();
        }
        List<ActivityVo> activityVos = activityListQuery.queryActivityList(context);

        pageVo.setList(activityVos);

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.ACTIVITY_QUERY_SUCC), pageVo);

        Logger.debug("获取活动的信息：" + messageUtil.toJson().toString());
        return ok(messageUtil.toJson());
    }

    /**
     * 用户获取奖励接口(签到)
     * 调用这个接口需要将活动场景参数送过来
     * @return
     */

    public Result signinObtainReward() {
        return obtainReward(ActivityConstant.ACTIVITY_SIGNIN_SCENE_CODE);
    }

    /**
     * 用户获取奖励接口
     *
     * @return
     */
    public Result obtainReward(String scene) {
        //1：获取请求参数
        String token = getToken();
        ActivityParamter activityParamter = getActivityParamter();

        //2:获取获取奖励需要的参数
        CustomerSession customerSession = customerService.getCustomerSession(token);
        String custNo = customerSession.getCustomerId();
        if(StringUtils.isEmpty(scene)) {
            scene = activityParamter.getScene();
        }

        ActivityRequestVo requestVo = new ActivityRequestVo();
        ActivityResponseVo responseVo = new ActivityResponseVo();

        requestVo.setCustId(custNo);
        requestVo.setScene(scene);



        activityHandlerService.service(requestVo, responseVo);

        List<ObtainRewardVo> obtainRewardVos = responseVo.getObtainRewardVo();
        ObtainRewardVo obtainRewardVo = null;
        if(obtainRewardVos == null || obtainRewardVos.isEmpty()) {
            obtainRewardVo = new ObtainRewardVo();
            obtainRewardVo.setAlreadyGet(0L);
            obtainRewardVo.setNotGet(0L);
            obtainRewardVo.setScene(scene);
            obtainRewardVo.setStatus(ActivityConstant.ACTIVITY_CUSTONER_STATUS_FORBIDDEN);
        } else {
            obtainRewardVo = obtainRewardVos.get(0);
        }
        messageUtil.setMessage(responseVo.getMessage(), obtainRewardVo);
        return ok(messageUtil.toJson());
    }

    /**
     * 注册获取奖励
     * @return
     */
    public Result registerObtainReward() {
        return obtainReward(ActivityConstant.ACTIVITY_REGISTER_SCENE_CODE);
    }

    /**
     * 购买获取奖励
     * @return
     */
    public Result purchaseObtainReward() {
        //1：获取请求参数
        String token = getToken();
        ActivityParamter activityParamter = getActivityParamter();
        String scene = "";
        Message message = null;
        if(ActivityConstant.TRADE_TYPE_PURCHASE.equals(activityParamter.getTradeType())) {
            scene = ActivityConstant.ACTIVITY_PURCHASE_SCENE_CODE;
        } else {
            Logger.debug("不支持的交易类型tradeType = " + activityParamter.getTradeType());
            message = new Message(Severity.INFO, MsgCode.NOT_SUPPORT_TRADE_TYPE);
            TradeObtainRewardFailVo tradeObtainRewardFailVo = new TradeObtainRewardFailVo();
            tradeObtainRewardFailVo.setFundCode(activityParamter.getFundCode());
            tradeObtainRewardFailVo.setSupplySum(activityParamter.getSupplySum());
            messageUtil.setMessage(message, tradeObtainRewardFailVo);
            return ok(messageUtil.toJson());
        }

        //2:获取获取奖励需要的参数
        CustomerSession customerSession = customerService.getCustomerSession(token);
        String custNo = customerSession.getCustomerId();

        ActivityRequestVo requestVo = new ActivityRequestVo();
        ActivityResponseVo responseVo = new ActivityResponseVo();

        requestVo.setCustId(custNo);
        requestVo.setScene(scene);

        //购买场景时会用到的字段
        requestVo.set("prdCode", activityParamter.getFundCode());
        requestVo.set("supplySum", activityParamter.getSupplySum());

        activityHandlerService.service(requestVo, responseVo);

        message = responseVo.getMessage();
        List<ActivityResultVo> activityResultVos = responseVo.getActivityResultVos();

        if(MsgCode.OBTAIN_SUCC.getCode().equals(message.getCode())) {
            TradeObtainRewardSuccVo tradeObtainRewardSuccVo = new TradeObtainRewardSuccVo();
            tradeObtainRewardSuccVo.setFundCode(activityParamter.getFundCode());
            tradeObtainRewardSuccVo.setSupplySum(activityParamter.getSupplySum());
            tradeObtainRewardSuccVo.setRecords(activityResultVos);
            messageUtil.setMessage(responseVo.getMessage(), tradeObtainRewardSuccVo);
        } else {
            TradeObtainRewardFailVo tradeObtainRewardFailVo = new TradeObtainRewardFailVo();
            tradeObtainRewardFailVo.setFundCode(activityParamter.getFundCode());
            tradeObtainRewardFailVo.setSupplySum(activityParamter.getSupplySum());
            messageUtil.setMessage(responseVo.getMessage(), tradeObtainRewardFailVo);
        }

        return ok(messageUtil.toJson());
    }


}
