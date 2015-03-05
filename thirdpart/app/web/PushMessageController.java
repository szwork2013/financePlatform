package web;

import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.PushMessageService;

import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PushMessageVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

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
@Api(value = "/thirdpart", description = "第三方服务接口")
public class PushMessageController extends Controller{
    private Form<PushMessageVo> pushMessageVoFrom = Form.form(PushMessageVo.class);
    private PushMessageService pushMessageService = new PushMessageService();

    @ApiOperation(value = "推送消息",
            nickname = "push",
            response = PushMessageVo.class, httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(value = "PushMessageVo object", required = true, dataType = "PushMessageVo", paramType = "body")})
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
