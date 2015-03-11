package web;


import models.MessageSmsTxn;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.SmsMessageService;


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
    private Form<MessageSmsTxn> smsMessageFrom = Form.form(MessageSmsTxn.class);
    private SmsMessageService smsMessageService = new SmsMessageService();

    public Result sendSms() {

        MessageSmsTxn smsMessage = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            smsMessage = Json.fromJson(body.asJson(), MessageSmsTxn.class);
        } else {
            smsMessage = smsMessageFrom.bindFromRequest().get();
        }
        Logger.debug("sendSms params：" + Json.toJson(smsMessage));

        smsMessageService.sendSms(smsMessage);

        Logger.debug("sendSms return：" + Json.toJson(smsMessage));
        return ok(Json.toJson(smsMessage));
    }

}
