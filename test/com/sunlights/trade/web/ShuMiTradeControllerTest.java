package com.sunlights.trade.web;

import com.sunlights.BaseTest;
import com.sunlights.common.vo.MessageVo;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @Before
    public void testTradeOrder() throws Exception {

        String applySerial = new Date().toString();// -->申请编号
        String fundCode = "000009"; //-->基金代码
        String fundName = "易方达天天A";// -->基金名称
        String applySum = "100";// -->认购金额
        String bankCardInfo = "工商银行***1416";// -->支付卡信息
        String dateTime = "2013-05-10T17:56:57.396875+08:00";// -->扣款时间
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

                Result result = getResult("/trade/shumitradeorder", params, cookie);

                Logger.info("==============testTradeOrder end=====================\n" + contentAsString(result));

                assertThat(status(result)).isEqualTo(OK);
                MessageVo message = toMessageVo(result);
                assertThat(message.getMessage().getCode()).isEqualTo("0400");
                assertThat(message.getMessage().getSummary()).isEqualTo("下单成功");
            }
        });

    }

    @Test
    public void testTradeRedeem() throws Exception {
        String applySerial = "1288478324852";// -->申请编号
        String fundCode = "020001"; //-->基金代码
        String fundName = "国泰金鹰增长";// -->基金名称
        String applySum = "100";// -->认购金额
        String bankCardInfo = "工商银行***1416";// -->支付卡信息
        String dateTime = "2013-05-10T17:56:57.396875+08:00";// -->扣款时间

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

            }
        });

    }
}
