package com.sunlights.customer.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.sunlights.common.AppConst;
import com.sunlights.common.vo.MessageHeaderVo;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.libs.Json;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

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
public class MsgCenterAction extends Action.Simple {
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

            JsonNode jsonNode = Json.parse(headerMsg).get("headerValue");
            final List<MessageHeaderVo> messageHeaderVoList = Lists.newArrayList();
            if (jsonNode != null && jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    MessageHeaderVo messageHeaderVo = Json.fromJson(node, MessageHeaderVo.class);
                    messageHeaderVoList.add(messageHeaderVo);
                }
            } else {
                return result;
            }

            final String routeActionMethod = (String) context.args.get(AppConst.ROUTE_ACTION_METHOD);
            JPA.withTransaction(new F.Callback0() {
                @Override
                public void invoke() throws Throwable {
                    MsgCenterActionService msgService = new MsgCenterActionService();
                    msgService.msgCenterSendMsg(routeActionMethod, messageHeaderVoList);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }


}
