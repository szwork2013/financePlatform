package com.sunlights.op.web;


import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.MessagePushConfigService;
import com.sunlights.op.service.impl.MessagePushConfigServiceImpl;
import com.sunlights.op.vo.MessagePushConfigVo;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Date;
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
            MessagePushConfigVo messagePushVos = Json.fromJson(body.asJson(), MessagePushConfigVo.class);
            messagePushVos.setUpdatetime(CommonUtil.dateToString(new Date(), "yyyy-MM-dd"));
            messagePushConfigService.update(messagePushVos);
            return ok("更新成功");
        }
        return ok("更新失败");
    }

    public Result createMessagePushConfig(){
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            MessagePushConfigVo messagePushVos = Json.fromJson(body.asJson(), MessagePushConfigVo.class);
            messagePushVos.setCreatetime(CommonUtil.dateToString(new Date(),"yyyy-MM-dd"));
            messagePushVos.setUpdatetime(CommonUtil.dateToString(new Date(),"yyyy-MM-dd"));
            System.out.print("==============="+messagePushVos.getPlanbegintime());

            messagePushVos.setStatus("Y");
            messagePushConfigService.save(messagePushVos);
            return ok("创建成功");
        }
        return ok("创建失败");
    }
//
//    public Result getMessPushConfigid() {
//
//        List<MessagePushConfig> messPushConfigVos = messagePushService.getMessPushConfigid();
//        List<KeyValueVo> result = new ArrayList<KeyValueVo>();
//        for(MessagePushConfig type : messPushConfigVos) {
//            result.add(new KeyValueVo(type.getId(), type.getRemarks()));
//        }
//        return ok(Json.toJson(result));
//    }
//
//    public Result getMessPushGroup() {
//
//        List<Group> group = messagePushService.getMessPushGroup();
//        List<KeyValueVo> result = new ArrayList<KeyValueVo>();
//        for(Group type : group) {
//            result.add(new KeyValueVo(type.getId(), type.getName()));
//        }
//        return ok(Json.toJson(result));
//    }
//
//
//    public Result insertToMessPushTXN(){
//        Http.RequestBody body = request().body();
//
//        if (body.asJson() != null) {
//            MessagePushVo messagePushVos = Json.fromJson(body.asJson(), MessagePushVo.class);
//            MessagePushTxn messagePushTxnVos= new MessagePushTxn();
//            messagePushTxnVos.setCreateTime(new Date());
//            messagePushTxnVos.setUpdateTime(new Date());
//            messagePushTxnVos.setMessageRuleId(messagePushVos.getId());
//            messagePushTxnVos.setGroupId(messagePushVos.getGroupid());
//            messagePushService.saveMessPushTxn(messagePushTxnVos);
//            return ok("创建成功");
//        }
//        return ok("创建失败");
//    }

}
