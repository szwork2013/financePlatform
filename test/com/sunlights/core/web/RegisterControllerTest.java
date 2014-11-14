package com.sunlights.core.web;

import com.sunlights.BaseTest;
import com.sunlights.common.service.VerifyCodeService;
import com.sunlights.common.vo.MessageVo;
import models.BaseAccount;
import models.Customer;
import models.CustomerSession;
import models.LoginHistory;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class RegisterControllerTest extends BaseTest {

    @Test
    public void testRegister() throws Exception {
        running(fakeApplication(), new Runnable() {
            public void run() {
                final String mobilePhoneNo = "18522222239";
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


                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        EntityManager em = JPA.em();
                        Query query = em.createQuery("select c from Customer c where c.mobile = :mobile", Customer.class);
                        query.setParameter("mobile", mobilePhoneNo);
                        Customer customer = (Customer)query.getSingleResult();
                        
                        query = em.createQuery("select ba from BaseAccount ba where ba.custId = :customerId", BaseAccount.class);
                        query.setParameter("customerId", customer.getCustomerId());
                        BaseAccount baseAccount = (BaseAccount)query.getSingleResult();

                        query = em.createQuery("select lh from LoginHistory lh where lh.customerId = :customerId", LoginHistory.class);
                        query.setParameter("customerId", customer.getCustomerId());
                        LoginHistory loginHistory = (LoginHistory)query.getSingleResult();
                        
                        query = em.createQuery("select cs from CustomerSession cs where cs.customerId = :customerId", CustomerSession.class);
                        query.setParameter("customerId", customer.getCustomerId());
                        CustomerSession customerSession = (CustomerSession)query.getSingleResult();

                        em.remove(loginHistory);
                        em.remove(baseAccount);
                        em.remove(customerSession);
                        em.remove(customer);
                    }
                });

            }
        });
    }


}
