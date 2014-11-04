package com.sunlights.core.web;

import com.sunlights.common.vo.PageVo;
import com.sunlights.core.vo.BankCardVo;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.test.FakeApplication;
import play.test.FakeRequest;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class BankControllerTest {

    private static Form<PageVo> pagerForm = Form.form(PageVo.class);
    private static Form<BankCardVo> bankCardForm = Form.form(BankCardVo.class);

    @Test
    public void testCreateBankCard() throws Exception {

    }

    @Test
    public void testDeleteBankCards() throws Exception {

    }

    @Test
    public void testValidateBankCard() throws Exception {

    }

    @Test
    public void testFindBankCards() throws Exception {

    }

    @Test
    public void testFindBankByBankCardNo() throws Exception {

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
                FakeRequest formRequest = banksRequest.withHeader("Content-Type", "application/x-www-form-urlencoded").withFormUrlEncodedBody(paramMap);
                result = route(formRequest);
                Logger.info("result is " + contentAsString(result));
                assertThat(contentAsString(result)).contains("0000");
            }
        });
    }
}