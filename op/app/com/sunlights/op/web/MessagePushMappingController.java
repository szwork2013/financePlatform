package com.sunlights.op.web;


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
import java.util.Map;

import static play.data.Form.form;

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
                return ok("更新成功");
            }else{
                messagePushMappingService.save(messagePushVo);
                return ok("创建成功");
            }
        }
        return ok("保存失败");
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
        Map<String, String> activityIdMap  = form().bindFromRequest().data();
        List<Activity> activityList = messagePushMappingService.findActivityIdByScene(activityIdMap.get("value"));
        List<KeyValueVo> result = new ArrayList<KeyValueVo>();
        for(Activity type : activityList) {
            result.add(new KeyValueVo(type.getId(), type.getTitle()));
        }
        return ok(Json.toJson(result));
    }


    public Result deleteMesPushMappingById(){

        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            MessagePushMappingVo messagePushVos = Json.fromJson(body.asJson(), MessagePushMappingVo.class);
            messagePushMappingService.deleteById(Long.valueOf(messagePushVos.getId()));
            return ok("删除成功");
        }
        return ok("删除失败");

    }


}
