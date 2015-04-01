package com.sunlights.op.web.activity;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.common.ActivityType;
import com.sunlights.op.service.activity.ActivitySceneService;
import com.sunlights.op.service.activity.impl.ActivitySceneServiceImpl;
import com.sunlights.op.vo.KeyValueVo;
import com.sunlights.op.vo.activity.ActivitySceneVo;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: ActivitySceneController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class ActivitySceneController extends Controller {

    private MessageUtil messageUtil = MessageUtil.getInstance();

    private ActivitySceneService activitySceneService = new ActivitySceneServiceImpl();

    public Result findScenes() {
        PageVo pageVo = new PageVo();
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }

        List<ActivitySceneVo> sceneVos = activitySceneService.findScenesBy(pageVo);
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), sceneVos);
        return ok(messageUtil.toJson());
    }

    public Result loadSceneKeyValue() {
        List<ActivitySceneVo> sceneVos = activitySceneService.findScenesBy(new PageVo());
        List<KeyValueVo> keyValueVos = new ArrayList<KeyValueVo>();
        for(ActivitySceneVo activitySceneVo : sceneVos) {
            keyValueVos.add(new KeyValueVo(activitySceneVo.getScene(), activitySceneVo.getTitle()));
        }
        return ok(Json.toJson(keyValueVos));
    }

    public Result createScene() {

        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            ActivitySceneVo sceneVo = Json.fromJson(body.asJson(), ActivitySceneVo.class);
            activitySceneService.create(sceneVo);
            messageUtil.setMessage(new Message(Severity.INFO, MsgCode.CREATE_SUCCESS));
            return ok(messageUtil.toJson());
        }

        messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.CREATE_FAILURE));
        return badRequest(messageUtil.toJson());
    }

    public Result updateScene() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            ActivitySceneVo sceneVo = Json.fromJson(body.asJson(), ActivitySceneVo.class);
            activitySceneService.update(sceneVo);
            messageUtil.setMessage(new Message(Severity.INFO, MsgCode.UPDATE_SUCCESS));
            return ok(messageUtil.toJson());
        }

        messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.UPDATE_FAILURE));
        return badRequest(messageUtil.toJson());
    }

    public Result deleteScene() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            ActivitySceneVo sceneVo = Json.fromJson(body.asJson(), ActivitySceneVo.class);
            activitySceneService.delete(sceneVo);
            messageUtil.setMessage(new Message(Severity.INFO, MsgCode.DELETE_SUCCESS));
            return ok(messageUtil.toJson());
        }
        messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.DELETE_FAILURE));
        return badRequest(messageUtil.toJson());
    }

    public Result saveScene() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            ActivitySceneVo sceneVo = Json.fromJson(body.asJson(), ActivitySceneVo.class);
            Message message = null;
            if (sceneVo.getId() == null) {
                activitySceneService.create(sceneVo);
                message = new Message(Severity.INFO, MsgCode.CREATE_SUCCESS);
            } else {
                activitySceneService.update(sceneVo);
                message = new Message(Severity.INFO, MsgCode.UPDATE_SUCCESS);
            }
            messageUtil.setMessage(message);
            return ok(messageUtil.toJson());

        }
        messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
        return badRequest(messageUtil.toJson());
    }

    public Result findActivityTypes() {
        return ok(Json.toJson(ActivityType.findActivityTypes()));
    }
}
