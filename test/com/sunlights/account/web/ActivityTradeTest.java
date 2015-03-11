package com.sunlights.account.web;

import com.sunlights.BaseTest;
import com.sunlights.common.AppConst;
import com.sunlights.common.utils.CommonUtil;
import org.junit.Test;
import play.Logger;
import play.mvc.Result;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;

import static play.test.Helpers.contentAsString;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: ActivityTradeTest.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class ActivityTradeTest extends BaseTest {

    @Test
    public void testActivityTrade() throws Exception{
        final Random random = new Random();

        for (int i = 0; i<= 2; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String mobilePhoneNo = "222222";
                    for (int j = 0; j< 5; j++) {
                        mobilePhoneNo += random.nextInt(9);
                    }
                    final Map<String, String> formParams = new HashMap<>();
                    formParams.put("mobilePhoneNo", mobilePhoneNo);
                    formParams.put("deviceNo", getDeviceNo());
                    formParams.put("type", "REGISTER");
                    formParams.put("passWord", "1");

                    getResult("/core/register", formParams);
                    cookie = getCookieAfterLogin(mobilePhoneNo, "1", AppConst.CHANNEL_PC);
                    tradeTest();
                }
            });
            thread.start();
            thread.run();
        }

    }

    private void tradeTest() {
        try {
            String applySerial = CommonUtil.dateToString(new Date(), CommonUtil.YYYYMMDDHHMMSS);// -->申请编号
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

            Logger.info("==============testTradeOrder start=====================");


            Result result = getResult("/trade/shumitradeorder", params, cookie);

            Logger.info("==============testTradeOrder end=====================\n" + contentAsString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDateTime() throws Exception {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) ca);

        String dateTime = calendar.toString();//"2013-05-10T17:56:57.396875+08:00";// -->扣款时间
        return dateTime;
    }
}
