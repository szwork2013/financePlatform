package com.sunlights;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.vo.MessageVo;
import models.CustomerSession;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.FakeRequest;

import javax.persistence.Query;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

/**
 * Created by Administrator on 2014/11/3.
 */
public class BaseTest {
    public static String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
    public static String CONTENT_TYPE = "Content-Type";
    protected String getDeviceNo(){
        try {
           return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "testdeviceno";
    }

    protected MessageVo toMessageVo(Result result) {
        String content = contentAsString(result);
        JsonNode jsonNode = Json.parse(content);
        return Json.fromJson(jsonNode, MessageVo.class);
    }


    protected Result getResult(String routes, Map formParams){
        formParams.put("deviceNo", getDeviceNo());
        FakeRequest fakeRequest = fakeRequest(POST, routes).withHeader(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(formParams);
        return route(fakeRequest);
    }


    protected Result getResult(String routes, Map formParams, Http.Cookie cookie){
        formParams.put("deviceNo", getDeviceNo());
        FakeRequest fakeRequest = fakeRequest(POST, routes).withHeader(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED).withCookies(cookie).withFormUrlEncodedBody(formParams);
        play.mvc.Result result = route(fakeRequest);
        return result;
    }

    protected Http.Cookie getCookieAfterLogin(String mobilePhoneNo, String passWord){
        Logger.info("===============login=====Test=============");
        final Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", mobilePhoneNo);
        formParams.put("deviceNo", getDeviceNo());
        formParams.put("passWord", passWord);
        
        play.mvc.Result result = getResult("/customer/login", formParams);
        assertThat(status(result)).isEqualTo(OK);
        Http.Cookie tokenCookie = cookie("token", result);
        Logger.info("token = " + tokenCookie.value());
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0101");
        assertThat(message.getMessage().getSummary()).isEqualTo("登录成功");

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                Query query = JPA.em().createNativeQuery("select cs.* from c_customer_session cs,c_customer c where c.mobile = ?0 and c.customer_id = cs.customer_id order by cs.create_time desc limit 1 offset 0", CustomerSession.class);
                query.setParameter(0, formParams.get("mobilePhoneNo"));
                CustomerSession customerSession = (CustomerSession) query.getSingleResult();
                formParams.put("token", customerSession.getToken());
                Logger.info("===============search===customoerSession===============");
            }
        });
        Http.Cookie cookie = new Http.Cookie("token", formParams.get("token"), null, null, null, false, false);
        
        return cookie;
    }
    
}
