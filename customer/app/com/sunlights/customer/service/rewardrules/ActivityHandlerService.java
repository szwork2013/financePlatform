package com.sunlights.customer.service.rewardrules;



import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.vo.Message;
import com.sunlights.customer.service.rewardrules.vo.ActivityRequestVo;
import com.sunlights.customer.service.rewardrules.vo.ActivityResponseVo;
import org.apache.commons.lang3.StringUtils;
import play.Logger;

/**
 * 活动处理服务
 * 对外提供服务：1：参加活动获取奖励 2：兑换奖励
 * 不管是获取奖励还是兑换奖励调用这个接口都必须传responseVo.setScene()（场景：对应着活动场景和兑换场景）
 * 流程：
 * 1：对请求特殊化处理
 * 2：根据请求获取活动处理执行链（包括前过滤器，处理功能链，后过滤器）
 * 3：适配我们当前系统是否支持这条功能链，如果支持的话则返回适配到的适配器，没有的话则直接返回
 * 4：执行执行链中的前过滤器，执行返回false的话则直接返回
 * 5：执行执行链中的处理功能链的逻辑
 * 6：执行执行链中的后过滤器
 * 7：清理请求对象中所占有的资源
 *
 * Created by tangweiqun on 2014/12/1.
 */
public class ActivityHandlerService {

    public void service(ActivityRequestVo requestVo, ActivityResponseVo responseVo) {
        ActivityRequestVo processeRequest = requestVo;
        HandlerExecutionChain mappedHandler = null;

        try {
            processeRequest = checkRequest(requestVo);
            mappedHandler = getHandler(processeRequest);

            if(mappedHandler == null || mappedHandler.getHandler() == null) {
                Message message = new Message(Severity.INFO, MsgCode.NOT_CONFIG_ACTIVITY_SCENE);
                responseVo.setMessage(message);
                responseVo.setFlowStop(true);
                return;
            }

            HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

            ha.handle(processeRequest, responseVo, mappedHandler.getHandler());

        } catch (Exception e) {
            Message message = new Message(Severity.INFO, MsgCode.ACTIVITY_SYS_ERROR);
            responseVo.setMessage(message);
            Logger.error("活动操作失败", e);
            throw new RuntimeException("活动操作失败", e);
        } finally {
//            clearupRequest(processeRequest);
        }
    }


    protected ActivityRequestVo checkRequest(ActivityRequestVo requestVo) {
        if(requestVo == null || StringUtils.isEmpty(requestVo.getScene())) {
            throw new IllegalArgumentException("请求参数的scene不能为空");
        }
        return requestVo;
    }

    protected HandlerExecutionChain getHandler(ActivityRequestVo requestVo) throws Exception {
        for(HandlerMapping hm : RewardRuleFactory.getHandlerMapping()) {
            HandlerExecutionChain handler = hm.getHandler(requestVo);
            if(handler != null) {
                return handler;
            }
        }
        return null;
    }

    protected  HandlerAdapter getHandlerAdapter(Object handler) {
        for(HandlerAdapter ha : RewardRuleFactory.getHandlerAdapters()) {
            if(ha.supports(handler)) {
                return ha;
            }
        }
        throw new RuntimeException("No Adapter for handler [" + handler + "] : Does you handler implement a supported interface like RuleHandler");
    }

    protected void clearupRequest(ActivityRequestVo requestVo) {
        requestVo.recyle();
    }
}
