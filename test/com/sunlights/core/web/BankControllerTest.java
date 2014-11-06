package com.sunlights.core.web;

import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.vo.BankCardFormVo;
import com.sunlights.core.vo.BankCardVo;
import models.CustomerSession;
import models.FundHistory;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.data.Form;
import play.db.jpa.JPA;
import play.libs.Json;
import play.mvc.Http;
import play.test.FakeApplication;
import play.test.FakeRequest;
import web.TestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class BankControllerTest {

  private static Form<PageVo> pagerForm = Form.form(PageVo.class);
  private static Form<BankCardFormVo> bankCardForm = Form.form(BankCardFormVo.class);

  private static String BANK_CARD_NO = "6225885105575635";

  @Test
  public void testCreateAndDeleteBankCard() throws Exception {
    running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
      public void run() {
        BankCardFormVo bankCardVo = new BankCardFormVo();
        bankCardVo.setBankCode("CCB");
        bankCardVo.setNo(BANK_CARD_NO);
        bankCardVo.setBankCardPassword("11111111111");

        final String[] token = {null};
        JPA.withTransaction(new play.libs.F.Callback0() {
          public void invoke() {
            EntityBaseDao entityBaseDao = new EntityBaseDao();
            List<CustomerSession> sessions = entityBaseDao.findAll(CustomerSession.class, "createdDatetime", false);
            if (!sessions.isEmpty()) {
              CustomerSession customerSession = sessions.get(0);
              token[0] = customerSession.getToken();
            }
          }
        });

        play.mvc.Result result = null;
        FakeRequest bankCardCreateRequest = fakeRequest(POST, "/core/bank/bankcard/create");
        Map<String, String> paramMap = bankCardForm.bind(Json.toJson(bankCardVo)).data();

        createBankCard(token[0], bankCardCreateRequest, paramMap);

        // delete bank card
        FakeRequest bankCardDeleteRequest = fakeRequest(POST, "/core/bank/bankcard/delete");
        paramMap = bankCardForm.bind(Json.toJson(bankCardVo)).data();

        FakeRequest formBankCardDeleteRequest = bankCardDeleteRequest.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
        formBankCardDeleteRequest.withCookies(new Http.Cookie(AppConst.TOKEN, token[0], null, null, null, false, false));
        result = route(formBankCardDeleteRequest);

        Logger.info("result is " + contentAsString(result));
        assertThat(contentAsString(result)).contains(MsgCode.BANK_CARD_DELETE_SUCCESS.getCode());

      }
    });

  }

  private void createBankCard(String s, FakeRequest bankCardCreateRequest, Map<String, String> paramMap) {
    play.mvc.Result result;
    FakeRequest formBankCardCreateRequest = bankCardCreateRequest.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
    formBankCardCreateRequest.withCookies(new Http.Cookie(AppConst.TOKEN, s, null, null, null, false, false));
    result = route(formBankCardCreateRequest);

    Logger.info("result is " + contentAsString(result));
    assertThat(contentAsString(result)).contains(MsgCode.BANK_CARD_ADD_SUCCESS.getCode());
  }

  @Test
  public void testValidateBankCard() throws Exception {
    running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
      public void run() {
        BankCardFormVo bankCardVo = new BankCardFormVo();
        bankCardVo.setNo(BANK_CARD_NO);
        bankCardVo.setBankCode("CCB");

        final String[] token = {null};
        JPA.withTransaction(new play.libs.F.Callback0() {
          public void invoke() {
            EntityBaseDao entityBaseDao = new EntityBaseDao();
            List<CustomerSession> sessions = entityBaseDao.findAll(CustomerSession.class, "createdDatetime", false);
            if (!sessions.isEmpty()) {
              CustomerSession customerSession = sessions.get(0);
              token[0] = customerSession.getToken();
            }
          }
        });

        play.mvc.Result result = null;
        FakeRequest bankCardValidateRequest = fakeRequest(POST, "/core/bank/bankcard/validate");
        Map<String, String> paramMap = bankCardForm.bind(Json.toJson(bankCardVo)).data();

        FakeRequest formRequest = bankCardValidateRequest.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
        formRequest.withCookies(new Http.Cookie(AppConst.TOKEN, token[0], null, null, null, false, false));
        result = route(formRequest);

        Logger.info("result is " + contentAsString(result));
        assertThat(contentAsString(result)).contains(MsgCode.OPERATE_SUCCESS.getCode());
      }
    });
  }

  @Test
  public void testFindBankCards() throws Exception {
    running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
      public void run() {
        final String[] token = {null};
        JPA.withTransaction(new play.libs.F.Callback0() {
          public void invoke() {
            EntityBaseDao entityBaseDao = new EntityBaseDao();
            List<CustomerSession> sessions = entityBaseDao.findAll(CustomerSession.class, "createdDatetime", false);
            if (!sessions.isEmpty()) {
              CustomerSession customerSession = sessions.get(0);
              token[0] = customerSession.getToken();
            }
          }
        });
        FakeRequest findBankCards = fakeRequest(POST, "/core/bank/bankcards");
        play.mvc.Result result = null;
        // form request
        PageVo pageVo = new PageVo();
        Map<String, String> paramMap = pagerForm.bind(Json.toJson(pageVo)).data();
        Logger.info("[paramMap]" + paramMap);

        FakeRequest formRequest = findBankCards.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
        System.out.println("[token]" + token[0]);
        formRequest.withCookies(new Http.Cookie(AppConst.TOKEN, token[0], null, null, null, false, false));
        result = route(formRequest);

        Logger.info("result is " + contentAsString(result));

        assertThat(contentAsString(result)).contains(MsgCode.OPERATE_SUCCESS.getCode());
      }
    });
  }

  @Test
  public void testFindBankByBankCardNo() throws Exception {
    running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
      public void run() {
        BankCardVo bankCardVo = new BankCardVo();
        bankCardVo.setNo("6225885105574736");
        FakeRequest findBankRequest = fakeRequest(POST, "/core/bank/findbybankcard");
        play.mvc.Result result = null;
        // form request
        Map<String, String> paramMap = bankCardForm.bind(Json.toJson(bankCardVo)).data();
        Logger.info("[paramMap]" + paramMap);
        FakeRequest formRequest = findBankRequest.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
        result = route(formRequest);
        Logger.info("result is " + contentAsString(result));
        assertThat(contentAsString(result)).contains(MsgCode.OPERATE_SUCCESS.getCode());
      }
    });
  }

  @Test
  public void testFindBanks() throws Exception {
    running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
      public void run() {
        PageVo pageVo = new PageVo();
        FakeRequest banksRequest = fakeRequest(POST, "/core/banks");
        play.mvc.Result result = null;
        // form request
        Map<String, String> paramMap = pagerForm.bind(Json.toJson(pageVo)).data();
        Logger.info("[paramMap]" + paramMap);
        FakeRequest formRequest = banksRequest.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
        result = route(formRequest);
        Logger.info("result is " + contentAsString(result));
        assertThat(contentAsString(result)).contains("0000");
      }
    });
  }
}