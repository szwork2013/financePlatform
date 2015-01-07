package com.sunlights.core.web;

import com.sunlights.BaseTest;
import com.sunlights.common.vo.MessageVo;
import org.junit.Test;
import play.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class SecurityControllerTest extends BaseTest {

  @Test
  public void testGenVerificationCode() throws Exception {

        Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", "18522222225");
        formParams.put("deviceNo", "deviceNo");
        formParams.put("type", "REGISTER");

        play.mvc.Result result = getResult("/core/verificationcode", formParams);
        assertThat(status(result)).isEqualTo(OK);
//        MessageVo message = toMessageVo(result);
//        assertThat(message.getMessage().getCode()).isEqualTo("0000");
//        assertThat(message.getMessage().getSummary()).isEqualTo("操作成功");

          String contentAsString = contentAsString(result);
          Logger.info("result is " + contentAsString);

          /**
           * 验证message与value
           */
          String testString= null;
          try {
              testString = getJsonFile("json/CoreVerifiCationCode.json");//获得json文件内容
          } catch (IOException e) {
              e.printStackTrace();
          }
          MessageVo message = toMessageVo(result);
          MessageVo testMessage = toMessageVo(testString);
          assertThat(testMessage).isEqualTo(message);//此处判断message


  }

  @Test
  public void testCertify() throws Exception {

        Map<String, String> formParams = new HashMap<>();
        formParams.put("userName", "testusername");
        formParams.put("idCardNo", "254545454545454554");//

        play.mvc.Result result = getResult("/core/certify", formParams);
        assertThat(status(result)).isEqualTo(OK);
//        MessageVo message = toMessageVo(result);
//        assertThat(message.getMessage().getSeverity() == 0);
        Logger.info("result is " + contentAsString(result));

          /**
           * 验证message
           */
          String testString= null;
          try {
              testString = getJsonFile("json/CoreCertify.json");//获得json文件内容
          } catch (IOException e) {
              e.printStackTrace();
          }
          MessageVo message = toMessageVo(result);
          MessageVo testMessage = toMessageVo(testString);
          assertThat(testMessage).isEqualTo(message);//此处判断message

  }

  @Test
  public void testCertifyAndResetTradePwd() throws Exception {

        Map<String, String> formParams = new HashMap<>();
        formParams.put("userName", "testusername");
        formParams.put("idCardNo", "254545454545454554");
        formParams.put("passWord", "0000");

        play.mvc.Result result = getResult("/core/certify", formParams);
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getSeverity() == 0);

  }
}
