package com.sunlights.trade.web;

import com.sunlights.BaseTest;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.MessageVo;
import models.ProductManage;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.util.*;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class ShuMiTradeControllerTest extends BaseTest {
    private Http.Cookie cookie = null;

    public void login(){
        String mobilePhoneNo = "13811599308";
        String password = "1";
        cookie = getCookieAfterLogin(mobilePhoneNo, password);
    }

    @Test
    public void testTradeOrder() throws Exception {
        String applySerial = CommonUtil.dateToString(new Date(),CommonUtil.YYYYMMDDHHMMSS);// -->申请编号
        final String fundCode = "482002"; //-->基金代码
        String fundName = "工银货币";// -->基金名称
        String applySum = "100";// -->认购金额
        String bankCardInfo = "工商银行***1416";// -->支付卡信息
        String dateTime = getDateTime();//"2013-05-10T17:56:57.396875+08:00";// -->扣款时间
        String bankName = "工商银行";// -->银行名称
        String bankAcco = "140826195608226018";// -->银行卡号
        
        final Map<String, String> params = new HashMap<String, String>();
        params.put("applySerial", applySerial);
        params.put("fundCode", fundCode);
        params.put("fundName", fundName);
        params.put("applySum", applySum);
        params.put("bankCardInfo", bankCardInfo);
        params.put("dateTime", dateTime);
        params.put("bankName", bankName);
        params.put("bankAcco", bankAcco);

        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                login();

                Logger.info("==============testTradeOrder start=====================");

                int preBuyNum = getBuyNum(fundCode);

                Result result = getResult("/trade/shumitradeorder", params, cookie);

                Logger.info("==============testTradeOrder end=====================\n" + contentAsString(result));

                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0400");
                assertThat(message.getMessage().getSummary()).isEqualTo("下单成功");


                /**
                 * 验证message与value
                 */
                String testString= null;
                try {
                    testString = getJsonFile("json/CustTradeOrder.json");//获得json文件内容
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MessageVo testMessage = toMessageVo(testString);
                assertThat(testMessage).isEqualTo(message);//此处判断message

                int aftBuyNum = getBuyNum(fundCode);

                assertThat(preBuyNum + 1).isEqualTo(aftBuyNum);
                
            }
        });

    }

    private int getBuyNum(final String productCode){
        final Integer[] num = {0};
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                String sql = "select p from ProductManage p where p.productCode = ?1";
                EntityManager em = JPA.em();
                Query query = em.createQuery(sql, ProductManage.class);
                query.setParameter(1, productCode);
                ProductManage productManage = (ProductManage)query.getResultList().get(0);
                num[0] = productManage.getInitBuyedCount();
            }
        });

        return num[0];
    }

    private String getDateTime() throws Exception{
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar)ca);

        String dateTime = calendar.toString();//"2013-05-10T17:56:57.396875+08:00";// -->扣款时间
        return dateTime;
    }

    //@Test
    public void testTradeRedeem() throws Exception {
        String applySerial = CommonUtil.dateToString(new Date(),CommonUtil.YYYYMMDDHHMMSS);// -->申请编号
        final String fundCode = "482002"; //-->基金代码
        String fundName = "工银货币";// -->基金名称
        String applySum = "100";// -->认购金额
        String bankCardInfo = "工商银行***1416";// -->支付卡信息
        String dateTime = getDateTime();//"2013-05-10T17:56:57.396875+08:00";// -->扣款时间

        final Map<String, String> params = new HashMap<String, String>();
        params.put("applySerial", applySerial);
        params.put("fundCode", fundCode);
        params.put("fundName", fundName);
        params.put("applySum", applySum);
        params.put("bankCardInfo", bankCardInfo);
        params.put("dateTime", dateTime);

        running(fakeApplication(), new Runnable() {
            @Override
            public void run() {
                login();
                Logger.info("==============testTradeRedeem start=====================");

                Result result = getResult("/trade/shumitraderedeem", params, cookie);

                Logger.info("==============testTradeRedeem end=====================\n" + contentAsString(result));

                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0401");
                assertThat(message.getMessage().getSummary()).isEqualTo("赎回成功");

                /**
                 * 验证message与value
                 */
                String testString= null;
                try {
                    testString = getJsonFile("json/CustTradeRedeem.json");//获得json文件内容
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MessageVo testMessage = toMessageVo(testString);
                assertThat(testMessage).isEqualTo(message);//此处判断message

            }
        });

    }
}
