package com.sunlights.core.web;

import com.sunlights.BaseTest;
import com.sunlights.common.vo.MessageVo;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.status;

public class SecurityControllerTest extends BaseTest {

  @Test
  public void testGenVerificationCode() throws Exception {


      String  mobilePhoneNo = "";
        for (int i = 0; i< 200;i++) {
            mobilePhoneNo = randomVerifyCode(11);
            getVerifyCode(mobilePhoneNo);
        }

      JPA.withTransaction(new F.Callback0() {
          @Override
          public void invoke() throws Throwable {
              EntityManager entityManager = JPA.em();
              Query query = entityManager.createNativeQuery("select count(*) from c_message_sms_txn");
              String count = query.getSingleResult().toString();
              Logger.info("放了多少个了？ " + count);
          }
      });


  }

    private static String randomVerifyCode(int size) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            int num = random.nextInt(9);
            sb.append(num);
        }
        return sb.toString();
    }

    private void getVerifyCode(String mobile) {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", mobile);
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
        String testString = null;
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
