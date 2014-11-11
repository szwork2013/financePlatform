package com.sunlights.account.web;

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
import static play.test.Helpers.*;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: AccountControllerTest.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class AccountControllerTest extends BaseTest {


  @Test
  public void testResetAccountPwd() throws Exception {
    running(fakeApplication(), new Runnable() {
      public void run() {
        final String mobilePhoneNo = "18522222225";
        final String deviceNo = "1111";

        final Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", mobilePhoneNo);
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
            Query query = JPA.em().createNativeQuery("select cs.* FROM c_customer_session cs,c_customer c where c.mobile = ?0 and c.customer_id = cs.CUSTOMER_ID order by cs.create_time desc limit 1 offset 0", CustomerSession.class);
            query.setParameter(0, mobilePhoneNo);
            CustomerSession customerSession = (CustomerSession) query.getSingleResult();
            formParams.put("token", customerSession.getToken());
            Logger.info("===============search===customoerSession===============");
          }
        });
        Http.Cookie cookie = new Http.Cookie("token", formParams.get("token"), null, null, null, false, false);

        formRequest = fakeRequest(POST, "/account/resetaccpwd").withHeader("Content-Type", "application/x-www-form-urlencoded").withCookies(cookie).withFormUrlEncodedBody(formParams);
        result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0200");
        assertThat(message.getMessage().getSummary()).isEqualTo("交易密码重置成功");

      }
    });
  }

  @Test
  public void testGetTotalCapitalInfo() throws Exception {
    running(fakeApplication(), new Runnable() {
      public void run() {
        Map<String, String> formParams = new HashMap<String, String>();
        formParams.put("mobile", "15821948594");
        FakeRequest formRequest = fakeRequest(POST, "/account/accountital/totalcapital").withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
        play.mvc.Result result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

      }
    });
  }

  @Test
  public void testGetAllCapital4Prd() throws Exception {
    running(fakeApplication(), new Runnable() {
      public void run() {
        Map<String, String> formParams = new HashMap<String, String>();
        formParams.put("mobile", "15821948594");
        FakeRequest formRequest = fakeRequest(POST, "/account/accountital/allcapital4prd").withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
        play.mvc.Result result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

      }
    });
  }

  @Test
  public void testGetMyCapital() throws Exception {
    running(fakeApplication(), new Runnable() {
      public void run() {
        Map<String, String> formParams = new HashMap<String, String>();
        formParams.put("mobile", "15821948594");
        FakeRequest formRequest = fakeRequest(POST, "/account/accountital/mycapital").withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
        play.mvc.Result result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

      }
    });
  }


}
