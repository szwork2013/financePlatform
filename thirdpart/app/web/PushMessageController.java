package web;

import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PushMessageVo;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.PushMessageService;

/**
 * <p>Project: thirdpartyservice</p>
 * <p>Title: PushController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class PushMessageController extends Controller{
    private Form<PushMessageVo> pushMessageVoFrom = Form.form(PushMessageVo.class);
    private PushMessageService pushMessageService = new PushMessageService();

    public Result sendPush() {
        Logger.info("sendPush params：" + request().body().asJson());

        PushMessageVo pushMessageVo = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            pushMessageVo = Json.fromJson(body.asJson(), PushMessageVo.class);
        }else{
            pushMessageVo = pushMessageVoFrom.bindFromRequest().get();
        }
        MessageVo messageVo = pushMessageService.sendPush(pushMessageVo);

        Logger.info("sendPush return：" + Json.toJson(messageVo));
        return ok(Json.toJson(messageVo));
    }

}
