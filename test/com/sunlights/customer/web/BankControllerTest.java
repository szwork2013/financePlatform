package com.sunlights.customer.web;

import com.google.common.collect.Lists;
import com.sunlights.BaseTest;
import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.dal.BankCardDao;
import com.sunlights.customer.dal.impl.BankCardDaoImpl;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.vo.BankCardFormVo;
import com.sunlights.customer.vo.BankCardVo;
import models.BankCard;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.data.Form;
import play.db.jpa.JPA;
import play.libs.F;
import play.libs.Json;
import play.mvc.Result;
import play.test.FakeRequest;
import web.TestUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class BankControllerTest extends BaseTest {

    private static Form<PageVo> pagerForm = Form.form(PageVo.class);
    private static Form<BankCardFormVo> bankCardForm = Form.form(BankCardFormVo.class);

    private static String BANK_CARD_NO = "6225885105575635";

    @Before
    public void init() {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
            public void run() {
                login("13811599308", "1");
            }
        });

    }

    @Test
    public void testCreateBankCard() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
            public void run() {
                String bankName = "招商银行";
                String bankSerial = "002";
                String bankCard = BANK_CARD_NO;

                // create bank card
                Map<String, String> paramMap = new HashMap<String, String>();
                paramMap.put("bankName", bankName);
                paramMap.put("bankSerial", bankSerial);
                paramMap.put("bankCard", bankCard);

                Result result = getResult("/core/bank/bankcard/create", paramMap, cookie);

                Logger.info("result is " + contentAsString(result));
                assertThat(contentAsString(result)).contains(MsgCode.BANK_CARD_ADD_SUCCESS.getCode());

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        EntityManager em = JPA.em();
                        String sql = "select * from c_bank_card bc where bc.bank_card_no = :bankCardNo";
                        Query query = em.createNativeQuery(sql, BankCard.class);
                        query.setParameter("bankCardNo", BANK_CARD_NO);
                        List<BankCard> list = query.getResultList();
                        assertThat(list.size()).isGreaterThan(0);

                        BankCardDao bankCardDao = new BankCardDaoImpl();
                        bankCardDao.deleteById(list.get(0).getId());
                    }
                });


            }
        });
    }

    @Test
    public void testSaveAllBankCard() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
            public void run() {
                BankCardFormVo bankCardFormVo = new BankCardFormVo();
                bankCardFormVo.setNo("912739172312333333");
                bankCardFormVo.setTradeAccount("0299");
                bankCardFormVo.setSubTradeAccount("0299");
                bankCardFormVo.setIsVaild("true");
                bankCardFormVo.setBalance("0");
                bankCardFormVo.setStatus("0");
                bankCardFormVo.setStatusToCN("正常");
                bankCardFormVo.setIsFreeze("false");
                bankCardFormVo.setBankSerial("005");
                bankCardFormVo.setBankName("建设银行");
                bankCardFormVo.setCapitalMode("6");
                bankCardFormVo.setBindWay("0");
                bankCardFormVo.setSupportAutoPay("true");
                bankCardFormVo.setDiscountRate("0.40");
                bankCardFormVo.setLimitDescribe("单笔50万元，日累计50万元");
                bankCardFormVo.setContentDescribe("必须开通网上银行(U盾或动态口令)");

                BankCardFormVo bankCardFormVo2 = new BankCardFormVo();
                bankCardFormVo2.setNo("912739172312333334");
                bankCardFormVo2.setTradeAccount("0266");
                bankCardFormVo2.setSubTradeAccount("0266");
                bankCardFormVo2.setIsVaild("true");
                bankCardFormVo2.setBalance("0");
                bankCardFormVo2.setStatus("0");
                bankCardFormVo2.setStatusToCN("正常");
                bankCardFormVo2.setIsFreeze("false");
                bankCardFormVo2.setBankSerial("005");
                bankCardFormVo2.setBankName("中国银行");
                bankCardFormVo2.setCapitalMode("6");
                bankCardFormVo2.setBindWay("0");
                bankCardFormVo2.setSupportAutoPay("true");
                bankCardFormVo2.setDiscountRate("0.40");
                bankCardFormVo2.setLimitDescribe("单笔50万元，日累计50万元");
                bankCardFormVo2.setContentDescribe("必须开通网上银行(U盾或动态口令)");


                List<BankCardFormVo> list = Lists.newArrayList();
                list.add(bankCardFormVo);
                list.add(bankCardFormVo2);

                Logger.info(">>testSaveAllBankCard params:" + Json.toJson(list).toString());

                // create bank card
                Map<String, String> paramMap = new HashMap<String, String>();
                paramMap.put("cards", Json.toJson(list).toString());

                Result result = getResult("/core/bank/bankcard/saveall", paramMap, cookie);

                Logger.info("result is " + contentAsString(result));
                assertThat(contentAsString(result)).contains(MsgCode.OPERATE_SUCCESS.getCode());

                JPA.withTransaction(new F.Callback0() {
                    @Override
                    public void invoke() throws Throwable {
                        String token = cookie.value();
                        EntityManager em = JPA.em();
                        CustomerService customerService = new CustomerService();
                        BankCardDao bankCardDao = new BankCardDaoImpl();

                        List<BankCard> list = bankCardDao.findBankCards(customerService.getCustomerSession(token).getCustomerId());
                        assertThat(list.size()).isGreaterThan(1);

                        for (BankCard bankCard : list) {
                            bankCardDao.deleteById(bankCard.getId());
                        }
                    }
                });
            }
        });
    }

    @Test
    public void testValidateBankCard() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
            public void run() {
                BankCardFormVo bankCardVo = new BankCardFormVo();
                bankCardVo.setNo(BANK_CARD_NO);
                bankCardVo.setBankName("中国银行");

                play.mvc.Result result = null;
                FakeRequest bankCardValidateRequest = fakeRequest(POST, "/core/bank/bankcard/validate");
                Map<String, String> paramMap = bankCardForm.bind(Json.toJson(bankCardVo)).data();

                FakeRequest formRequest = bankCardValidateRequest.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                formRequest.withCookies(cookie);
                result = route(formRequest);

                Logger.info("result is " + contentAsString(result));
                assertThat(contentAsString(result)).contains(MsgCode.OPERATE_SUCCESS.getCode());
            }
        });
    }

    @Test
    public void testFindAndDeleteBankCards() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
            public void run() {

                FakeRequest findBankCards = fakeRequest(POST, "/core/bank/bankcards");
                play.mvc.Result result = null;
                // form request
                PageVo pageVo = new PageVo();
                Map<String, String> paramMap = pagerForm.bind(Json.toJson(pageVo)).data();
                Logger.info("[paramMap]" + paramMap);

                FakeRequest formRequest = findBankCards.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                formRequest.withCookies(cookie);


                result = route(formRequest);

                Logger.info("result is " + contentAsString(result));

                assertThat(contentAsString(result)).contains(MsgCode.OPERATE_SUCCESS.getCode());

                MessageVo message = toMessageVo(result);
                Object value = message.getValue();
                if (value != null) {
                    List data = (ArrayList) value;
                    if (!data.isEmpty()) {
                        BankCardVo bankCardVo = Json.fromJson(Json.toJson(data.get(0)), BankCardVo.class);
                        // delete bank card
                        FakeRequest bankCardDeleteRequest = fakeRequest(POST, "/core/bank/bankcard/delete");
                        paramMap = bankCardForm.bind(Json.toJson(bankCardVo)).data();

                        FakeRequest formBankCardDeleteRequest = bankCardDeleteRequest.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                        formBankCardDeleteRequest.withCookies(cookie);
                        result = route(formBankCardDeleteRequest);

                        Logger.info("result is " + contentAsString(result));
                        assertThat(contentAsString(result)).contains(MsgCode.BANK_CARD_DELETE_SUCCESS.getCode());
                    }
                }

            }
        });
    }

    @Test
    public void testFindBankByBankCardNo() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {
            public void run() {
                BankCardVo bankCardVo = new BankCardVo();
                bankCardVo.setBankCard("6225885105574736");
                FakeRequest findBankRequest = fakeRequest(POST, "/core/bank/findbybankcard");
                play.mvc.Result result = null;
                // form request
                Map<String, String> paramMap = bankCardForm.bind(Json.toJson(bankCardVo)).data();
                Logger.info("[paramMap]" + paramMap);
                FakeRequest formRequest = findBankRequest.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                result = route(formRequest);
                Logger.info("result is " + contentAsString(result));
                assertThat(contentAsString(result)).contains(MsgCode.OPERATE_SUCCESS.getCode());
            }
        });
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
                FakeRequest formRequest = banksRequest.withHeader("Content-Type", TestUtil.APPLICATION_X_WWW_FORM_URLENCODED).withFormUrlEncodedBody(paramMap);
                result = route(formRequest);
                Logger.info("result is " + contentAsString(result));
                assertThat(contentAsString(result)).contains("0000");
            }
        });
    }
}
