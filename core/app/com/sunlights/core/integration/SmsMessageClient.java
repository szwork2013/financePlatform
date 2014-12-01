package com.sunlights.core.integration;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.vo.MessageVo;
import models.SmsMessage;
import play.Logger;
import play.Play;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;

/**
 * <p>Project: tradingsystem</p>
 * <p>Title: SendMsgService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

public class SmsMessageClient {

  private ParameterService parameterService = new ParameterService();

  public String sendSms(SmsMessage smsMessage) {
      Logger.info("========sendSms ws interface start===============");

      JsonNode json = Json.newObject()
              .put("mobile", smsMessage.getMobile())
              .put("content", smsMessage.getContent())
              .put("smsid", smsMessage.getSmsId());

      String smsUrl = Play.application().configuration().getString("sms_url");
      F.Promise<WSResponse> wsRequestHolder = WS.url(smsUrl).post(json);

      F.Promise<String> jsonPromise = wsRequestHolder.map(
              new F.Function<WSResponse, String>() {
                  public String apply(WSResponse response) {
                      JsonNode json = response.asJson();
                      MessageVo messageVo = Json.fromJson(json, MessageVo.class);
                      String result = (String)messageVo.getValue();

                      Logger.info("========sendSms ws interface end===============" + result);

                      return result;
                  }
              }
      );

      return jsonPromise.get(9000);
  }

}
