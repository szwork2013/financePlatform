package com.sunlights.op.web;


import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.MessagePushConfigService;
import com.sunlights.op.service.impl.MessagePushConfigServiceImpl;
import com.sunlights.op.vo.MessagePushConfigVo;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Administrator on 2014/12/12.
 */
@Transactional
public class MessagePushConfigController extends Controller {
    MessagePushConfigService messagePushConfigService=new MessagePushConfigServiceImpl();
    /**
     * 含分页的查询信息
     * @return
     */
    public Result findMessagePushConfig() {
        PageVo pageVo = new PageVo();
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }
        List<MessagePushConfigVo> messagePushListVos = messagePushConfigService.findMessagePushConfig(pageVo);
        pageVo.setList(messagePushListVos);
        return ok(Json.toJson(pageVo));
    }

    /**
     * 修改操作
     * @return
     */
    public Result updateMessagePushConfig() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            MessagePushConfigVo messagePushVo = Json.fromJson(body.asJson(), MessagePushConfigVo.class);
            messagePushConfigService.update(messagePushVo);

            return ok(MsgCode.UPDATE_SUCCESS.getMessage());
        }
        return ok(MsgCode.UPDATE_FAILURE.getMessage());
    }

    public Result createMessagePushConfig(){
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            MessagePushConfigVo messagePushVos = Json.fromJson(body.asJson(), MessagePushConfigVo.class);
            messagePushConfigService.save(messagePushVos);
            return ok(MsgCode.CREATE_SUCCESS.getMessage());
        }
        return ok(MsgCode.CREATE_FAILURE.getMessage());
    }

}
