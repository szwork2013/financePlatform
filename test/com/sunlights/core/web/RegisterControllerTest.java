package com.sunlights.core.web;

import com.sunlights.BaseTest;
import com.sunlights.common.service.VerifyCodeService;
import com.sunlights.common.vo.MessageVo;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

//TODO: 测试完后需要clean up数据
public class RegisterControllerTest extends BaseTest {

  @Test
  public void testRegister() throws Exception {
    running(fakeApplication(), new Runnable() {
      public void run() {
        final String mobilePhoneNo = "18522222227";
        final String deviceNo = getDeviceNo();
        final String type = "REGISTER";

        final Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", mobilePhoneNo);
        formParams.put("deviceNo", deviceNo);
        formParams.put("type", type);
        formParams.put("passWord", "1");

        play.mvc.Result result = getResult("/core/verificationcode", formParams);

        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0000");
        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

        JPA.withTransaction(new F.Callback0() {
          @Override
          public void invoke() throws Throwable {
              VerifyCodeService verifyCodeService = new VerifyCodeService();
              String verifyCode = verifyCodeService.genVerificationCode(mobilePhoneNo, type, deviceNo);
              formParams.put("verifyCode", verifyCode);
          }
        });

        result = getResult("/core/register", formParams);
        Logger.info(result.toString());
        assertThat(status(result)).isEqualTo(OK);

        message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0100");
        assertThat(message.getMessage().getSummary()).isEqualTo("注册成功");
      }
    });
  }


}
