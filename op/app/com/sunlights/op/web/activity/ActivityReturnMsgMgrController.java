package com.sunlights.op.web.activity;

import com.sunlights.common.MsgCode;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.activity.ActivityReturnMsgService;
import com.sunlights.op.service.activity.impl.ActivityReturnMsgServiceImpl;
import com.sunlights.op.vo.KeyValueVo;
import com.sunlights.op.vo.activity.ActivityReturnMsgVo;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/12.
 */
@Transactional
public class ActivityReturnMsgMgrController extends Controller {

    private ActivityReturnMsgService activityReturnMsgService = new ActivityReturnMsgServiceImpl();

    public Result findReturnMsgs() {

        Http.RequestBody body = request().body();

        PageVo pageVo = Json.fromJson(request().body().asJson(), PageVo.class);

        List<ActivityReturnMsgVo> activityReturnMsgVos = activityReturnMsgService.findByCondition(pageVo);

        pageVo.setList(activityReturnMsgVos);

        return ok(Json.toJson(pageVo));
    }

    public Result saveReturnMsg() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            ActivityReturnMsgVo activityReturnMsgVo = Json.fromJson(body.asJson(), ActivityReturnMsgVo.class);

            if(activityReturnMsgVo.getId() == null) {
                activityReturnMsgVo = activityReturnMsgService.save(activityReturnMsgVo);
            } else {

                activityReturnMsgVo = activityReturnMsgService.modify(activityReturnMsgVo);
            }

            return ok(Json.toJson(activityReturnMsgVo));
        }
        return ok("操作失败");
    }

    public Result deleteReturnMsg() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            ActivityReturnMsgVo activityReturnMsgVo = Json.fromJson(body.asJson(), ActivityReturnMsgVo.class);
            activityReturnMsgService.remove(activityReturnMsgVo.getId());
            return ok("删除成功");
        }
        return ok("删除失败");
    }

    public Result loadErrorMsg() {
        List<KeyValueVo> keyValueVos = new ArrayList<KeyValueVo>();
        for(MsgCode msgCode : MsgCode.values()) {
            String errorCode = msgCode.getCode();
            if(errorCode.startsWith("022") || errorCode.startsWith("222") || errorCode.startsWith("223") || errorCode.equals("0000")) {
                keyValueVos.add(new KeyValueVo(msgCode.getCode(), msgCode.getMessage()));
            }

        }
        return ok(Json.toJson(keyValueVos));
    }


}
