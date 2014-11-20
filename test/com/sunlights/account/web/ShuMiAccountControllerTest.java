package com.sunlights.account.web;

import com.sunlights.BaseTest;
import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.service.impl.CustomerService;
import models.Customer;
import models.ShuMiAccount;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class ShuMiAccountControllerTest extends BaseTest {
    private static Http.Cookie cookie;

    @Before
    public void init(){
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String mobilePhoneNo = "13811599307";
                String password = "1";
                cookie = getCookieAfterLogin(mobilePhoneNo, password);
            }
        });
    }

    @Test
    public void testSaveShuMiAccount() throws Exception {
        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                String shumi_tokenKey = "0000a08ffd974254ab9449c8c8c0e190";
                String shumi_tokenSecret = "9939f00d7f214c2f8e218f41a839764c";
                String shumi_userName = "吕小布";
                String shumi_realName = "吕布";
                String shumi_idNumber = "33082519871014621X";
                String shumi_bankName = "招商银行";
                String shumi_bankCardNo = "6227003********3534";
                String shumi_bankSerial = "002";
                String shumi_phoneNum = "13811599308";
                String shumi_email = "y7076580@sina.com";

                final Map<String, Object> params = new HashMap<String, Object>();
                params.put("shumi_tokenKey", shumi_tokenKey);
                params.put("shumi_tokenSecret", shumi_tokenSecret);
                params.put("shumi_userName", shumi_userName);
                params.put("shumi_realName", shumi_realName);
                params.put("shumi_idNumber", shumi_idNumber);
                params.put("shumi_bankName", shumi_bankName);
                params.put("shumi_bankCardNo", shumi_bankCardNo);
                params.put("shumi_bankSerial", shumi_bankSerial);
                params.put("shumi_phoneNum", shumi_phoneNum);
                params.put("shumi_email", shumi_email);

                Logger.info("-------------------testSaveShuMiAccount start------");
                
                Result result = getResult("/account/saveshumiaccount", params, cookie);

                Logger.info("-------------------testSaveShuMiAccount end---------------\n" + contentAsString(result));

                MessageVo messageVo = toMessageVo(result);
                assertThat(messageVo.getMessage().getCode()).isEqualTo(MsgCode.SAVE_SHUMI_ACCOUNT_SUCCESS.getCode());
                assertThat(messageVo.getMessage().getSummary()).isEqualTo(MsgCode.SAVE_SHUMI_ACCOUNT_SUCCESS.getMessage());


                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        CustomerService customerService = new CustomerService();
                        Customer customer = customerService.getCustomerByToken(cookie.value());
                        assertThat(params.get("shumi_idNumber")).isEqualTo(customer.getIdentityNumber());

                        Query query = JPA.em().createNamedQuery("findShuMiAccount", ShuMiAccount.class);
                        query.setParameter(1, customer.getCustomerId());
                        ShuMiAccount shuMiAccount = (ShuMiAccount)query.getSingleResult();

//                        JPA.em().remove(shuMiAccount);
                    }
                });

            }
        });

    }
}
