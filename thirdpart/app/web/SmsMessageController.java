package web;


import com.sunlights.common.vo.SmsMessageVo;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.SmsMessageService;

import java.util.List;


/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: SmsMessageController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class SmsMessageController extends Controller {
    private Form<SmsMessageVo> smsMessageFrom = Form.form(SmsMessageVo.class);
    private SmsMessageService smsMessageService = new SmsMessageService();

    public Result sendSms() {

        SmsMessageVo smsMessage = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            smsMessage = Json.fromJson(body.asJson(), SmsMessageVo.class);
        } else {
            smsMessage = smsMessageFrom.bindFromRequest().get();
        }
        Logger.debug("sendSms params：" + Json.toJson(smsMessage));

        List<SmsMessageVo> smsMessageVoList = smsMessageService.sendSms(smsMessage);

        Logger.debug("sendSms return：" + Json.toJson(smsMessageVoList));
        return ok(Json.toJson(smsMessageVoList));
    }

}
