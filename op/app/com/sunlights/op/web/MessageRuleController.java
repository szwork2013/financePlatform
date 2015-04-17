package com.sunlights.op.web;

import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.common.vo.PushMessageVo;
import com.sunlights.op.service.MessageRuleService;
import com.sunlights.op.service.impl.MessageRuleServiceImpl;
import com.sunlights.op.vo.KeyValueVo;
import com.sunlights.op.vo.MessageRuleVo;
import models.Group;
import models.MessagePushConfig;
import models.MessagePushTxn;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/12/12.
 */
@Transactional
public class MessageRuleController extends Controller {
    MessageRuleService messageRuleService=new MessageRuleServiceImpl();
    /**
     * 含分页的查询信息
     * @return
     */
    public Result findMessageRule() {
        PageVo pageVo = new PageVo();
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }
        List<MessageRuleVo> messagePushListVos = messageRuleService.findMessagePush(pageVo);
        pageVo.setList(messagePushListVos);
        return ok(Json.toJson(pageVo));
    }

    /**
     * 修改操作
     * @return
     */
    public Result updateMessageRule() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            MessageRuleVo messagePushVos = Json.fromJson(body.asJson(), MessageRuleVo.class);
            messageRuleService.update(messagePushVos);
            return ok(MsgCode.UPDATE_SUCCESS.getMessage());
        }
        return ok(MsgCode.UPDATE_FAILURE.getMessage());
    }

    public Result createMessageRule(){
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            MessageRuleVo messagePushVos = Json.fromJson(body.asJson(), MessageRuleVo.class);
            messageRuleService.save(messagePushVos);
            return ok(MsgCode.CREATE_SUCCESS.getMessage());
        }
        return ok(MsgCode.CREATE_FAILURE.getMessage());
    }

    public Result getMessRuleConfig() {

        List<MessagePushConfig> messPushConfigVos = messageRuleService.getMessPushConfig();
        List<KeyValueVo> result = new ArrayList<KeyValueVo>();
        for(MessagePushConfig type : messPushConfigVos) {
            result.add(new KeyValueVo(type.getId(), type.getRemarks()));
        }
        return ok(Json.toJson(result));
    }

    public Result getMessPushGroup() {

        List<Group> group = messageRuleService.getMessPushGroup();
        List<KeyValueVo> result = new ArrayList<KeyValueVo>();
        for(Group type : group) {
            result.add(new KeyValueVo(type.getId(), type.getName()));
        }
        return ok(Json.toJson(result));
    }


    public Result insertToMessPushTXN(){
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            MessageRuleVo messagePushVo = Json.fromJson(body.asJson(), MessageRuleVo.class);

            MessagePushTxn messagePushTxn = messageRuleService.saveMessPushTxn(messagePushVo);

            messagePushVo.setMessageTxnId(messagePushTxn.getId());
            MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_SUCCESS), messagePushVo);
        }else{
            MessageUtil.getInstance().setMessage(new Message(MsgCode.OPERATE_FAILURE));
        }
        return ok(MessageUtil.getInstance().toJson());
    }

    public Result immediatelyPush(){
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            MessageRuleVo messagePushVo = Json.fromJson(body.asJson(), MessageRuleVo.class);

            Long messPushConfigId = messagePushVo.getMessagePushConfigId();
            if(!messageRuleService.checkMessPushConfig(messPushConfigId)){
                return ok(MsgCode.PUSH_TIMED_IND_ERROR.getMessage());
            }

            //消息做过推送  不能再推送
            MessagePushTxn messagePushTxn = messageRuleService.findMessagePushTxnById(messagePushVo.getMessageTxnId());
            if (!DictConst.PUSH_STATUS_2.equals(messagePushTxn.getPushStatus())) {
                return ok(MsgCode.PUSH_STATUS_ERROR.getMessage());
            }
            List<PushMessageVo> list = messageRuleService.findPushMessage(messagePushVo.getMessageTxnId());

            messageRuleService.pushMessage(list);
        }
        return ok(MsgCode.PUSH_SUCCESS.getMessage());
    }
}
