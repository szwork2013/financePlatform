package web;

import com.sunlights.core.vo.AgreementVo;
import org.junit.Test;
import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.test.FakeRequest;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class AgreementControllerTest {
    private static Form<AgreementVo> agreeForm = Form.form(AgreementVo.class);

    @Test
    public void testFindAgreementVoByAgreementNo() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {

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

            }

        });
    }
}