package com.sunlights.op.web.activity;

import com.sunlights.op.service.activity.ActivityShareInfoService;
import com.sunlights.op.service.activity.impl.ActivityShareInfoServiceImpl;
import com.sunlights.op.vo.activity.ShareInfoVo;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * Created by tangweiqun on 2014/12/9.
 */
@Transactional
public class ActivityShareInfoController extends Controller {
    private ActivityShareInfoService activityShareInfoService = new ActivityShareInfoServiceImpl();


    public Result getAll() {
        List<ShareInfoVo> activityShareInfos = activityShareInfoService.loadAll();

        return ok(Json.toJson(activityShareInfos));
    }

    public Result save() {

        Http.RequestBody body = request().body();


        if (body.asJson() != null) {
            ShareInfoVo shareInfoVo = Json.fromJson(body.asJson(), ShareInfoVo.class);

            if (shareInfoVo.getId() == null) {
                shareInfoVo = activityShareInfoService.add(shareInfoVo);
                return ok(Json.toJson(shareInfoVo));
            } else {
                shareInfoVo = activityShareInfoService.modify(shareInfoVo);
                return ok(Json.toJson(shareInfoVo));
            }
        }


        return ok("操作失败");
    }

    public Result delete() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            ShareInfoVo shareInfoVo = Json.fromJson(body.asJson(), ShareInfoVo.class);
            activityShareInfoService.remove(shareInfoVo.getId());
            return ok("删除成功");
        }
        return ok("删除失败");
    }

}
