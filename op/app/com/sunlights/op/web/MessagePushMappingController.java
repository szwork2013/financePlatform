package com.sunlights.op.web;


import com.sunlights.common.MsgCode;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.MessagePushMappingService;
import com.sunlights.op.service.activity.ExchangeSceneService;
import com.sunlights.op.service.activity.impl.ExchangeSceneServiceImpl;
import com.sunlights.op.service.impl.MessagePushMappingServiceImpl;
import com.sunlights.op.vo.KeyValueVo;
import com.sunlights.op.vo.MessagePushMappingVo;
import models.Activity;
import models.ActivityScene;
import models.ExchangeScene;
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
public class MessagePushMappingController extends Controller {
    private MessagePushMappingService messagePushMappingService=new MessagePushMappingServiceImpl();
    private ExchangeSceneService exchangeSceneService = new ExchangeSceneServiceImpl();
    /**
     * 含分页的查询信息
     * @return
     */
    public Result findMessagePushMapping() {
        PageVo pageVo = new PageVo();
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }
        List<MessagePushMappingVo> messagePushListVos = messagePushMappingService.findMessagePushMapping(pageVo);
        pageVo.setList(messagePushListVos);
        return ok(Json.toJson(pageVo));
    }

    public Result saveMessagePushMapping(){
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            MessagePushMappingVo messagePushVo = Json.fromJson(body.asJson(), MessagePushMappingVo.class);
            if (messagePushVo.getId() != null) {
                messagePushMappingService.update(messagePushVo);
                return ok(MsgCode.UPDATE_SUCCESS.getMessage());
            }else{
                messagePushMappingService.save(messagePushVo);
                return ok(MsgCode.CREATE_SUCCESS.getMessage());
            }
        }
        return ok(MsgCode.OPERATE_FAILURE.getMessage());
    }

    public Result getActivityScene() {

        List<ActivityScene> scenes = messagePushMappingService.getScenes();
        List<KeyValueVo> result = new ArrayList<KeyValueVo>();
        for(ActivityScene type : scenes) {
            result.add(new KeyValueVo(type.getScene(), type.getTitle()));
        }
        List<ExchangeScene> list = exchangeSceneService.findExchangeScenes();
        for (ExchangeScene exchangeScene : list) {
            result.add(new KeyValueVo(exchangeScene.getScene(), exchangeScene.getTitle()));
        }

        return ok(Json.toJson(result));
    }

    public Result findActivityIdByScene() {
        String scene = request().getHeader("params");
        List<Activity> activityList = messagePushMappingService.findActivityIdByScene(scene);
        List<KeyValueVo> result = new ArrayList<KeyValueVo>();
        for(Activity type : activityList) {
            result.add(new KeyValueVo(type.getId(), type.getTitle()));
        }
        return ok(Json.toJson(result));
    }


    public Result deleteMesPushMappingById(){
        MessagePushMappingVo messagePushVos = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            messagePushVos = Json.fromJson(body.asJson(), MessagePushMappingVo.class);
        }else{
            messagePushVos = RequestUtil.fromQueryString(request().queryString(), MessagePushMappingVo.class);
        }
        messagePushMappingService.deleteById(Long.valueOf(messagePushVos.getId()));
        return ok(MsgCode.DELETE_SUCCESS.getMessage());
    }


}
