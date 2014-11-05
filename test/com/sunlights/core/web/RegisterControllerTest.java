package com.sunlights.core.web;

import com.sunlights.BaseTest;
import com.sunlights.common.vo.MessageVo;
import models.CustomerVerifyCode;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.test.FakeRequest;
import web.TestUtil;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

import static web.TestUtil.*;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

//TODO: 测试完后需要clean up数据
public class RegisterControllerTest extends BaseTest {

    @Test
    public void testRegister() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                final String mobilePhoneNo = "18522222225";
                final String deviceNo = "1111";
                final String type = "REGISTER";

                final Map<String, String> formParams = new HashMap<>();
                formParams.put("mobilePhoneNo", mobilePhoneNo);
                formParams.put("deviceNo", deviceNo);
                formParams.put("type", type);
                formParams.put("passWord", "1");

                FakeRequest formRequest = fakeRequest(POST, "/core/verificationcode").withHeader(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(formParams);
                play.mvc.Result result = route(formRequest);

                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0000");
                assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        Query query = JPA.em().createNativeQuery("select c.* FROM c_customer_verify_code c where c.mobile = ?0 and c.verify_type = ?1 and c.status = 'N' order by created_datetime desc", CustomerVerifyCode.class);
                        query.setParameter(0, mobilePhoneNo);
                        query.setParameter(1, type);
                        CustomerVerifyCode customerVerifyCode = (CustomerVerifyCode) query.getSingleResult();
                        formParams.put("verifyCode", customerVerifyCode.getVerifyCode());
                    }
                });

                formRequest = fakeRequest(POST, "/core/register").withHeader(CONTENT_TYPE, APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(formParams);
                result = route(formRequest);
                Logger.info(result.toString());
                assertThat(status(result)).isEqualTo(OK);

                message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0100");
                assertThat(message.getMessage().getSummary()).isEqualTo("注册成功");
            }
        });
    }


}
