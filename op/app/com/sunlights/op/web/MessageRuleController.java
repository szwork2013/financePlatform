package com.sunlights.op.web;

import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.CommonUtil;
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
import java.util.Date;
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
            messagePushVos.setUpdatetime(CommonUtil.dateToString(new Date(), "yyyy-MM-dd"));
            messageRuleService.update(messagePushVos);
            return ok("更新成功");
        }
        return ok("更新失败");
    }

    public Result createMessageRule(){
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            MessageRuleVo messagePushVos = Json.fromJson(body.asJson(), MessageRuleVo.class);
            messagePushVos.setCreatetime(CommonUtil.dateToString(new Date(),"yyyy-MM-dd"));
            messagePushVos.setUpdatetime(CommonUtil.dateToString(new Date(),"yyyy-MM-dd"));
            messagePushVos.setStatus("Y");
            messageRuleService.save(messagePushVos);
            return ok("创建成功");
        }
        return ok("创建失败");
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

            MessagePushTxn messagePushTxn = new MessagePushTxn();
            messagePushTxn.setCreateTime(new Date());
            messagePushTxn.setUpdateTime(new Date());
            messagePushTxn.setMessageRuleId(messagePushVo.getId());
            messagePushTxn.setGroupId(messagePushVo.getGroupid() == null ? 0 : messagePushVo.getGroupid());
            messagePushTxn.setTitle(messagePushVo.getTitle());
            messagePushTxn.setContent(messagePushVo.getContent());

            messagePushTxn = messageRuleService.saveMessPushTxn(messagePushTxn);

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
                return ok("推送任务失败，原因：该任务是定时的！");
            }

            //消息做过推送  不能再推送
            MessagePushTxn messagePushTxn = messageRuleService.findMessagePushTxnById(messagePushVo.getMessageTxnId());
            if (!DictConst.PUSH_STATUS_2.equals(messagePushTxn.getPushStatus())) {
                return ok("推送任务失败，该任务不是待推送状态！");
            }
            List<PushMessageVo> list = messageRuleService.findPushMessage(messagePushVo.getMessageTxnId());

            messageRuleService.pushMessage(list);
        }
        return ok("推送成功");
    }
}
