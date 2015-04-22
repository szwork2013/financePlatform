package com.sunlights.op.web.activity;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.activity.ActivityReturnMsgService;
import com.sunlights.op.service.activity.impl.ActivityReturnMsgServiceImpl;
import com.sunlights.op.vo.KeyValueVo;
import com.sunlights.op.vo.activity.ActivityReturnMsgVo;
import org.apache.commons.lang3.StringUtils;
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

    private MessageUtil messageUtil = MessageUtil.getInstance();

    public Result findReturnMsgs() {

        PageVo pageVo = new PageVo();
        Http.Request request = request();

        if (!StringUtils.isBlank(request.getHeader("params"))) {
            pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
        }

        List<ActivityReturnMsgVo> activityReturnMsgVos = activityReturnMsgService.findByCondition(pageVo);

        pageVo.setList(activityReturnMsgVos);

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
        return ok(messageUtil.toJson());
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

    public Result deleteReturnMsg(Long id) {
        activityReturnMsgService.remove(id);
        return ok("删除成功");

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
