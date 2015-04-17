package com.sunlights.op.web.activity;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.SCPService;
import com.sunlights.op.service.activity.ActivityService;
import com.sunlights.op.service.activity.ActivityShareInfoService;
import com.sunlights.op.service.activity.ObtainRewardRuleService;
import com.sunlights.op.service.activity.impl.ActivityServiceImpl;
import com.sunlights.op.service.activity.impl.ActivityShareInfoServiceImpl;
import com.sunlights.op.service.activity.impl.ObtainRewardRuleServiceImpl;
import com.sunlights.op.vo.activity.ActivityVo;
import models.Activity;
import org.apache.commons.lang3.StringUtils;
import play.Configuration;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.File;
import java.util.List;
import java.util.Map;

import static play.data.Form.form;

/**
 * Created by tangweiqun on 2014/11/12.
 */
@Transactional
public class ActivityController extends Controller {

    private ActivityService activityService = new ActivityServiceImpl();

    private MessageUtil messageUtil = MessageUtil.getInstance();

    private ObtainRewardRuleService obtainRewardRuleService = new ObtainRewardRuleServiceImpl();

    private ActivityShareInfoService activityShareInfoService = new ActivityShareInfoServiceImpl();

    public Result findActivities() {

        PageVo pageVo = new PageVo();
        Http.Request request = request();

        if (!StringUtils.isBlank(request.getHeader("params"))) {
            pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
        }

        List<ActivityVo> activityVos = activityService.findActivityWithRule(pageVo);
        pageVo.setList(activityVos);

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
        return ok(messageUtil.toJson());
    }

    public Result saveActivity() {
        Http.RequestBody body = request().body();
        Activity activity = null;

        if (body.asJson() != null) {
            ActivityVo activityVo = Json.fromJson(body.asJson(), ActivityVo.class);

            if(activityVo.getId() == null) {
                activity = activityService.add(activityVo);
            } else {

                activity = activityService.modifyActivity(activityVo);
            }

            return ok(Json.toJson(activity));
        }
        return ok("操作失败");
    }



    public Result deleteActivity(Long id) {
        obtainRewardRuleService.deleteByActivityId(id);
        activityService.deleteActivity(id);
        return ok("删除成功");

    }

    public Result uploadFiles() {
        Logger.info("上传文件");
        String server = Configuration.root().getString("nginx.server");
        String user = Configuration.root().getString("nginx.user");
        String password = Configuration.root().getString("nginx.password");
        String imagePath = Configuration.root().getString("nginx.imagePath");
        String html5Path = Configuration.root().getString("nginx.html5Path");
        Http.RequestBody body = request().body();
        try {
            if (body.asMultipartFormData() != null) {

                Http.MultipartFormData multipartFormData = body.asMultipartFormData();
                List<Http.MultipartFormData.FilePart> files = multipartFormData.getFiles();

                if (files != null) {

                    String remoteDir;
                    for (Http.MultipartFormData.FilePart file : files) {
                        File orFile = file.getFile();
                        String key = file.getKey();
                        String fileName = file.getFilename();
                        System.out.println("[fileFile.getName()]" + file.getFilename() + " key = " + file.getKey());
                        if("image".equals(key)) {
                            remoteDir = imagePath;
                        } else {
                            remoteDir = html5Path;
                        }
                        SCPService.uploadFile(server, user, password, remoteDir, orFile, file.getFilename());

                    }
                }
            }
        } catch (Exception e) {
            Logger.error("上传文件失败", e);
        }
        return ok("File uploaded succ");

    }

}
