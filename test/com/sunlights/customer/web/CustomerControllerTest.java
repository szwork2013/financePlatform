package com.sunlights.customer.web;

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
import static play.test.Helpers.fakeRequest;

public class CustomerControllerTest extends BaseTest {

  @Test
  public void testGetCustomerByMobilePhoneNo() throws Exception {
    running(fakeApplication(), new Runnable() {
      public void run() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", "15821948594");
        formParams.put("deviceNo", "1111");

        FakeRequest formRequest = fakeRequest(POST, "/customer/getusermstr").withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
        play.mvc.Result result = route(formRequest);
        Logger.info(contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");
      }
    });
  }

  @Test
  public void testLogin() throws Exception {
    running(fakeApplication(), new Runnable() {
      public void run() {
        final String mobilePhoneNo = "18522222226";
        final String deviceNo = "1111";

        final Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", mobilePhoneNo);
        formParams.put("deviceNo", deviceNo);
        formParams.put("passWord", "1");

        Logger.info("===============login=====Test=============");
        login(formParams);

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

        Logger.info("===============savegespwd=====Test=============");
        savegespwd(formParams, cookie);


        Logger.info("===============logout========Test==========");
        logout(formParams, cookie);


        Logger.info("===============loginByges=====Test=============");
        loginbyges(formParams);
      }

      private void login(Map<String, String> formParams) {
        FakeRequest formRequest = fakeRequest(POST, "/customer/login").withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
        play.mvc.Result result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0101");
        assertThat(message.getMessage().getSummary()).isEqualTo("登录成功");
      }

      private void savegespwd(Map<String, String> formParams, Http.Cookie cookie) {
        FakeRequest formRequest;
        play.mvc.Result result;
        MessageVo message;
        formParams.put("gestureOpened", "1");
        formParams.put("gesturePassWord", "0000");
        formRequest = fakeRequest(POST, "/customer/savegespwd").withHeader("Content-Type", "application/x-www-form-urlencoded").withCookies(cookie).withCookies(cookie).withFormUrlEncodedBody(formParams);
        result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0103");
        assertThat(message.getMessage().getSummary()).isEqualTo("手势密码设置成功");
      }

      private void logout(Map<String, String> formParams, Http.Cookie cookie) {
        FakeRequest formRequest;
        play.mvc.Result result;
        MessageVo message;
        formRequest = fakeRequest(POST, "/customer/logout").withHeader("Content-Type", "application/x-www-form-urlencoded").withCookies(cookie).withFormUrlEncodedBody(formParams);
        result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0104");
        assertThat(message.getMessage().getSummary()).isEqualTo("登出成功");
      }

      private void loginbyges(Map<String, String> formParams) {
        FakeRequest formRequest;
        play.mvc.Result result;
        MessageVo message;
        formRequest = fakeRequest(POST, "/customer/loginbyges").withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
        result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0101");
        assertThat(message.getMessage().getSummary()).isEqualTo("登录成功");
      }
    });
  }

  @Test
  public void testResetPwd() throws Exception {
    running(fakeApplication(), new Runnable() {
      public void run() {
        final String mobilePhoneNo = "18522222226";
        final String deviceNo = "1111";

        final Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", mobilePhoneNo);
        formParams.put("deviceNo", deviceNo);
        formParams.put("passWord", "1");

        Logger.info("===============login=====Test=============");
        login(formParams);

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


        Logger.info("===============resetpwd======Test============");
        resetpwdWithCookie(formParams, cookie);

        confirmpwd(formParams, cookie);


        resetpwd(formParams);
      }

      private void login(Map<String, String> formParams) {
        FakeRequest formRequest = fakeRequest(POST, "/customer/login").withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
        play.mvc.Result result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0101");
        assertThat(message.getMessage().getSummary()).isEqualTo("登录成功");
      }

      private void resetpwdWithCookie(Map<String, String> formParams, Http.Cookie cookie) {
        FakeRequest formRequest;
        play.mvc.Result result;
        MessageVo message;
        formParams.put("passWord", "2");
        formRequest = fakeRequest(POST, "/customer/resetpwd").withHeader("Content-Type", "application/x-www-form-urlencoded").withCookies(cookie).withFormUrlEncodedBody(formParams);
        result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        message = toMessageVo(result);
        assertThat(message.getMessage().getSeverity() == 0);
      }

      private void confirmpwd(Map<String, String> formParams, Http.Cookie cookie) {
        FakeRequest formRequest;
        play.mvc.Result result;
        MessageVo message;
        Logger.info("===============confirmpwd=====Test=============");
        formRequest = fakeRequest(POST, "/customer/confirmpwd").withHeader("Content-Type", "application/x-www-form-urlencoded").withCookies(cookie).withFormUrlEncodedBody(formParams);
        result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");
      }

      private void resetpwd(Map<String, String> formParams) {
        FakeRequest formRequest;
        play.mvc.Result result;
        MessageVo message;
        formParams.put("passWord", "1");
        formRequest = fakeRequest(POST, "/customer/resetpwd").withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(formParams);
        result = route(formRequest);
        assertThat(status(result)).isEqualTo(OK);
        message = toMessageVo(result);
        assertThat(message.getMessage().getSeverity() == 0);
      }
    });
  }

}
