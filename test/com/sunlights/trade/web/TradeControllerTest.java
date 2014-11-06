package com.sunlights.trade.web;

import com.sunlights.BaseTest;
import com.sunlights.common.vo.MessageVo;
import models.CustomerSession;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.mvc.Http;
import play.test.FakeRequest;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;
import static play.test.Helpers.route;
import static play.test.Helpers.status;

public class TradeControllerTest extends BaseTest {


  @Test
  public void testGetTradeList() throws Exception {
    running(fakeApplication(), new Runnable() {
      public void run() {
        final String mobilePhoneNo = "15821948594";
        final String deviceNo = "1111";

        final Map<String, String> formParams = new HashMap<String, String>();
        formParams.put("mobilePhoneNo", "15821948594");
        formParams.put("deviceNo", deviceNo);
        formParams.put("passWord", "1");

        Logger.info("===============login=====Test=============");
        FakeRequest formRequest = fakeRequest(POST, "/customer/login");
        formRequest = formRequest.withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
        play.mvc.Result result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0101");
        assertThat(message.getMessage().getSummary()).isEqualTo("登录成功");

        JPA.withTransaction(new F.Callback0() {
          @Override
          public void invoke() throws Throwable {
            Query query = JPA.em().createNativeQuery("select cs.* FROM c_customer_session cs,c_customer c where c.mobile = ?0 and c.customer_id = cs.CUSTOMER_ID order by cs.created_datetime desc limit 1 offset 0", CustomerSession.class);
            query.setParameter(0, mobilePhoneNo);
            CustomerSession customerSession = (CustomerSession) query.getSingleResult();
            formParams.put("token", customerSession.getToken());
            Logger.info("===============search===customoerSession===============");
          }
        });
        Http.Cookie cookie = new Http.Cookie("token", formParams.get("token"), null, null, null, false, false);

        formRequest = fakeRequest(POST, "/trade/tradelist").withHeader("Content-Type", "application/x-www-form-urlencoded").withCookies(cookie).withFormUrlEncodedBody(formParams);
        result = route(formRequest);
        Logger.info("result=========================" + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

      }
    });
  }

  @Test
  public void testFindCapitalProductDetailTrade() throws Exception {
    running(fakeApplication(), new Runnable() {
      public void run() {
        final String mobilePhoneNo = "15821948594";
        final String deviceNo = "1111";

        final Map<String, String> formParams = new HashMap<String, String>();
        formParams.put("mobilePhoneNo", "15821948594");
        formParams.put("deviceNo", deviceNo);
        formParams.put("passWord", "1");


        Logger.info("===============login=====Test=============");
        FakeRequest formRequest = fakeRequest(POST, "/customer/login").withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
        play.mvc.Result result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0101");
        assertThat(message.getMessage().getSummary()).isEqualTo("登录成功");

        JPA.withTransaction(new F.Callback0() {
          @Override
          public void invoke() throws Throwable {
            Query query = JPA.em().createNativeQuery("select cs.* FROM c_customer_session cs,c_customer c where c.mobile = ?0 and c.customer_id = cs.CUSTOMER_ID order by cs.created_datetime desc limit 1 offset 0", CustomerSession.class);
            query.setParameter(0, mobilePhoneNo);
            CustomerSession customerSession = (CustomerSession) query.getSingleResult();
            formParams.put("token", customerSession.getToken());
            Logger.info("===============search===customoerSession===============");
          }
        });
        Http.Cookie cookie = new Http.Cookie("token", formParams.get("token"), null, null, null, false, false);

        formParams.put("prdType", "0");
        formParams.put("prdCode", "GYHB");
        formRequest = fakeRequest(POST, "/trade/productdetail").withHeader("Content-Type", "application/x-www-form-urlencoded").withCookies(cookie).withFormUrlEncodedBody(formParams);
        result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

      }
    });
  }
}