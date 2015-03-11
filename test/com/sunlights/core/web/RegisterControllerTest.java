package com.sunlights.core.web;

import com.sunlights.BaseTest;
import com.sunlights.common.service.VerifyCodeService;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.vo.AuthenticationVo;
import com.sunlights.customer.vo.CustomerVo;
import models.*;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.libs.Json;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class RegisterControllerTest extends BaseTest {

    @Test
    public void testRegister() throws Exception {

        final String mobilePhoneNo = "18522222243";
//                final String mobilePhoneNo = "15821948594";
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
//                        formParams.put("verifyCode", "3333");
            }
        });

        result = getResult("/core/register", formParams);
        Logger.info("result is " + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);

        message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0100");
        assertThat(message.getMessage().getSummary()).isEqualTo("注册成功");
        CustomerVo customerVo = Json.fromJson(Json.toJson(message.getValue()), CustomerVo.class);
        assertThat(mobilePhoneNo).isEqualTo(customerVo.getMobilePhoneNo());


        /**
         * 验证message与value
         */
        String testString1 = null;
        try {
            testString1 = getJsonFile("json/CoreRegister.json");//获得json文件内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageVo message1 = toMessageVo(result);
        MessageVo testMessage1 = toMessageVo(testString1);
        assertThat(testMessage1.getMessage()).isEqualTo(message1.getMessage());//此处判断message
        CustomerVo testfundVo1 = Json.fromJson(Json.toJson(testMessage1.getValue()), CustomerVo.class);
        CustomerVo fundVo1 = Json.fromJson(Json.toJson(message1.getValue()), CustomerVo.class);
        assertThat(testfundVo1).isEqualTo(fundVo1);//此处判断value


        removeRegister(mobilePhoneNo);


    }


    @Test
    public void testP2pRegister() throws Exception {

        final String mobilePhoneNo = "18522222243";
        final String channel = "1";

        final Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", mobilePhoneNo);
        formParams.put("passWord", "1");
        formParams.put("channel", channel);

        play.mvc.Result result = null;
        MessageVo message = null;

        result = getResult("/core/register", formParams);
        Logger.info("result is " + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);

        message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo("0100");
        assertThat(message.getMessage().getSummary()).isEqualTo("注册成功");
        CustomerVo customerVo = Json.fromJson(Json.toJson(message.getValue()), CustomerVo.class);
        assertThat(mobilePhoneNo).isEqualTo(customerVo.getMobilePhoneNo());


        removeRegister(mobilePhoneNo);


    }

    private void removeRegister(final String mobilePhoneNo) {
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                EntityManager em = JPA.em();

                Query query = em.createNamedQuery("findAuthenticationVoByUserName", Authentication.class);
                query.setParameter("userName", mobilePhoneNo);
                AuthenticationVo authenticationVo = (AuthenticationVo) query.getSingleResult();

                Customer customer = authenticationVo.getCustomer();
                Authentication authentication = authenticationVo.getAuthentication();

                query = em.createQuery("select ba from BaseAccount ba where ba.custId = :customerId", BaseAccount.class);
                query.setParameter("customerId", customer.getCustomerId());
                BaseAccount baseAccount = (BaseAccount) query.getSingleResult();

                query = em.createQuery("select lh from LoginHistory lh where lh.customerId = :customerId", LoginHistory.class);
                query.setParameter("customerId", customer.getCustomerId());
                LoginHistory loginHistory = (LoginHistory) query.getSingleResult();

                query = em.createQuery("select cs from CustomerSession cs where cs.customerId = :customerId", CustomerSession.class);
                query.setParameter("customerId", customer.getCustomerId());
                CustomerSession customerSession = (CustomerSession) query.getSingleResult();

                em.remove(loginHistory);
                em.remove(baseAccount);
                em.remove(customerSession);
                em.remove(authentication);
                em.remove(customer);
            }
        });
    }

}
