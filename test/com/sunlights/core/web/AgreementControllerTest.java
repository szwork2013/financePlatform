package com.sunlights.core.web;

import com.sunlights.BaseTest;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.core.vo.AgreementVo;
import org.junit.Test;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.test.FakeRequest;
import web.TestUtil;

import java.io.IOException;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class AgreementControllerTest extends BaseTest {
    private static Form<AgreementVo> agreeForm = Form.form(AgreementVo.class);

    @Test
    public void testFindAgreementVoByAgreementNo() throws Exception {


        AgreementVo agreementVo = new AgreementVo();
        agreementVo.setCode("0001");

        FakeRequest banksRequest = fakeRequest(POST, "/core/agreement/findlinkbycode");
        // form request
        Map<String, String> paramMap = agreeForm.bind(Json.toJson(agreementVo)).data();
        Logger.info("[paramMap]" + paramMap);

        FakeRequest formRequest = banksRequest.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
        play.mvc.Result result = route(formRequest);

        Logger.info("result is " + contentAsString(result));

        assertThat(contentAsString(result)).contains("0000");

        /**
         * 验证message与value
         */
        String testString = null;
        try {
            testString = getJsonFile("json/CoreAgreement.json");//获得json文件内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageVo message = toMessageVo(result);
        MessageVo testMessage = toMessageVo(testString);
        assertThat(testMessage).isEqualTo(message);//此处判断message
        AgreementVo testAgreementVo = Json.fromJson(Json.toJson(testMessage.getValue()), AgreementVo.class);
        AgreementVo agreementVo1 = Json.fromJson(Json.toJson(message.getValue()), AgreementVo.class);
        assertThat(testAgreementVo).isEqualTo(agreementVo1);//此处判断value


    }
}