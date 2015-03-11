package com.sunlights.customer.web;

import com.sunlights.BaseTest;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.customer.vo.ShareVo;
import org.junit.Before;
import org.junit.Test;
import play.Logger;
import play.libs.Json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.status;

public class CustomerShareControllerTest extends BaseTest {
    @Before
    public void getCookie() {

        super.startPlay();
        final String mobilePhoneNo = "13811599307";
        final String password = "1";
        String channel = AppConst.CHANNEL_PC;
        cookie = getCookieAfterLogin(mobilePhoneNo, password, channel);

    }


    //@Test
    public void testsendFriend() throws Exception {//分享好友

        Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", "13811599307");
        formParams.put("deviceNo", getDeviceNo());

        play.mvc.Result result = getResult("/customer/activity/sharecontent", formParams, cookie);

        Logger.info(contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);


    }


    @Test
    public void testgetQRcodeToByte() throws Exception {//byte二维码

        Map<String, String> formParams = new HashMap<>();
        formParams.put("mobilePhoneNo", "13811599307");
        formParams.put("deviceNo", getDeviceNo());

        play.mvc.Result result = getResult("/customer/activity/getqrcode", formParams, cookie);

        Logger.info(contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);

    }

    @Test
    public void testInviteShare() throws Exception {//分享好友

        Map<String, String> formParams = new HashMap<>();
        formParams.put("type", "0");
        //formParams.put("id", "41921");

        play.mvc.Result result = getResult("/customer/activity/share", formParams, cookie);

        Logger.info("result is :" + contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.SHARE_QUERY_SUCC.getCode());

        /**
         * 验证message与value
         */
        String testString = null;
        try {
            testString = getJsonFile("json/CustShare.json");//获得json文件内容
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageVo testMessage = toMessageVo(testString);
        assertThat(testMessage).isEqualTo(message);//此处判断message
        ShareVo testShareVo = Json.fromJson(Json.toJson(testMessage.getValue()), ShareVo.class);
        ShareVo shareVo = Json.fromJson(Json.toJson(message.getValue()), ShareVo.class);
        assertThat(testShareVo).isEqualTo(shareVo);//此处判断value

        Logger.info("============testSignInObtainReward result====\n" + contentAsString(result));


    }

    //@Test
    public void testNotLogginInviteShare() throws Exception {//分享好友

        Map<String, String> formParams = new HashMap<>();
        formParams.put("type", "0");
        //formParams.put("id", "41921");

        play.mvc.Result result = getResult("/customer/activity/share", formParams);

        Logger.info(contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.SHARE_QUERY_SUCC.getCode());
        Logger.info("============testSignInObtainReward result====\n" + contentAsString(result));


    }

    //@Test
    public void testNotSupportShare() throws Exception {//分享好友

        Map<String, String> formParams = new HashMap<>();
        formParams.put("type", "1000");
        //formParams.put("id", "41921");

        play.mvc.Result result = getResult("/customer/activity/share", formParams);

        Logger.info(contentAsString(result));
        assertThat(status(result)).isEqualTo(OK);
        MessageVo message = toMessageVo(result);
        assertThat(message.getMessage().getCode()).isEqualTo(MsgCode.NOT_SUPPORT_SHARE_TYPE.getCode());
        Logger.info("============testSignInObtainReward result====\n" + contentAsString(result));

    }


}
