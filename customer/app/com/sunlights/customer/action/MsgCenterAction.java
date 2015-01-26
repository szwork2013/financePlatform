package com.sunlights.customer.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.vo.MessageHeaderVo;
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
import java.util.List;

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
        F.Promise<Result> result = delegate.call(context);
        try {
            String headerMsg = context.response().getHeaders().get(AppConst.HEADER_MSG);
            Logger.debug("headerMsg:" + headerMsg);
            if (headerMsg == null) {
                return result;
            }
            JsonNode messageJsonNode = Json.parse(headerMsg).get("message");
            if (messageJsonNode == null || messageJsonNode.asInt() != 0) {
                return result;
            }

            final String token = getResponseToken(context);
            if (token == null) {
                return result;
            }
            JsonNode jsonNode = Json.parse(headerMsg).get("headerValue");
            final List<MessageHeaderVo> messageHeaderVoList = Lists.newArrayList();
            if (jsonNode != null && jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    MessageHeaderVo messageHeaderVo = Json.fromJson(node, MessageHeaderVo.class);
                    messageHeaderVoList.add(messageHeaderVo);
                }
            }else{
                return result;
            }

            final String routeActionMethod = (String)context.args.get(AppConst.ROUTE_ACTION_METHOD);
            JPA.withTransaction(new F.Callback0() {
                @Override
                public void invoke() throws Throwable {
                    CustomerService customerService = new CustomerService();
                    CustomerSession customerSession = customerService.getCustomerSession(token);
                    if (customerSession == null) {
                        Logger.info(">>无效的token,非法入侵！");
                        throw new BusinessRuntimeException(">>无效的token,非法入侵！");
                    }
                    MsgCenterActionService msgService = new MsgCenterActionService();
                    Logger.info(">>开始查询待发消息>>");
                    msgService.sendMsg(routeActionMethod, messageHeaderVoList);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }


        return result;
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
