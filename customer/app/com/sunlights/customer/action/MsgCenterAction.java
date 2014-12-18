package com.sunlights.customer.action;

import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCenterCategory;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.customer.dal.MsgCenterDao;
import com.sunlights.customer.dal.impl.MsgCenterDaoImpl;
import com.sunlights.customer.service.impl.CustomerService;
import models.CustomerSession;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.libs.Json;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Iterator;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: PushAction.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class MsgCenterAction extends Action.Simple{
    @Override
    public F.Promise<Result> call(Http.Context context) throws Throwable {

//        EventBus eventBus = new EventBus();
//        eventBus.

        Logger.info("=============================");
        String uri = context.request().uri();

        F.Promise<Result> result = delegate.call(context);

        try {
            if (!validate(context)) {
                return result;
            }
            final String routeActionMethod = (String)context.args.get(AppConst.ROUTE_ACTION_METHOD);
            final String token = getResponseToken(context);
            if (token == null) {
                return result;
            }
            JPA.withTransaction(new F.Callback0() {
                @Override
                public void invoke() throws Throwable {
                    CustomerService customerService = new CustomerService();
                    CustomerSession customerSession = customerService.getCustomerSession(token);
                    if (customerSession == null) {
                        Logger.info(">>无效的token,非法入侵！");
                        throw new BusinessRuntimeException(">>无效的token,非法入侵！");
                    }
                    String ruleCode = MsgCenterCategory.getRuleCodeByMethodName(routeActionMethod);
                    MsgCenterDao centerDao = new MsgCenterDaoImpl();
                    PushMessageVo pushMessageVo = centerDao.findMessageRuleByCode(ruleCode);
                    if (pushMessageVo == null) {
                        Logger.info(">>消息规则未配置！");
                        throw new BusinessRuntimeException(">>消息规则未配置！");
                    }

                    Long id = pushMessageVo.getGroupId();
                    if (id != null && id != 0) {//TODO 针对某个群组操作

                    }else{//个人操作

                        String pushInd = pushMessageVo.getPushInd();
                        String smsInd = pushMessageVo.getSmsInd();
                        String pushTimed = pushMessageVo.getPushTimed();
                        if (AppConst.STATUS_VALID.equals(pushInd)) {
                            MsgCenterActionService actionService = new MsgCenterActionService();
                            actionService.sendPush(pushMessageVo, customerSession.getCustomerId(), ruleCode);
                        }
//                if (AppConst.STATUS_VALID.equals(smsInd)) {
//                    createMsgSmsTxn();
//                }
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }


    private boolean validate(Http.Context context){
        String headerMsg = context.response().getHeaders().get(AppConst.HEADER_MSG);
        Logger.debug("headerMsg:" + headerMsg);
        Message message = Json.fromJson(Json.parse(headerMsg), Message.class);
        if (Severity.INFO.getLevel() == message.getSeverity()) {
            return true;
        }
        return false;
    }

    private String getResponseToken(Http.Context context) {
        Iterable<Http.Cookie> cookies = context.response().cookies();
        Iterator<Http.Cookie> iterable = cookies.iterator();
        while (iterable.hasNext()) {
            Http.Cookie cookie = iterable.next();
            if (AppConst.TOKEN.equals(cookie.name())){
                String value = cookie.value();
                return value;
            }
        }

        return null;
    }
}
