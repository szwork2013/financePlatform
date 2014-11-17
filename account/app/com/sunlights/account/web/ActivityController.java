package com.sunlights.account.web;

import antlr.LexerSharedInputState;
import com.sunlights.account.service.ActivityService;
import com.sunlights.account.service.impl.ActivityServiceImpl;
import com.sunlights.account.vo.ActivityParamter;
import com.sunlights.account.vo.ActivityVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/13.
 */
@Transactional
public class ActivityController extends Controller  {
    private Form<ActivityParamter> activityParameterForm = Form.form(ActivityParamter.class);

    private ActivityService activityService = new ActivityServiceImpl();

    private MessageUtil messageUtil = MessageUtil.getInstance();

    public Result getActivityList() {
        ActivityParamter activityParamter = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            activityParamter = Json.fromJson(body.asJson(), ActivityParamter.class);
        }

        if (body.asFormUrlEncoded() != null) {
            activityParamter = activityParameterForm.bindFromRequest().get();
        }

        PageVo pageVo = new PageVo();
        pageVo.setIndex(0);
        pageVo.setPageSize(10);
        pageVo.setCount(4);

        ActivityVo vo = new ActivityVo();
        vo.setId(1L);
        vo.setImage("http://i.yingyinglicai.com/frontweb/theme/imagesnew/banner/1.jpg");
        vo.setUrl("http://licai.yingyinglicai.com/huodong/1111.html");
        vo.setName("送积分1");

        List<ActivityVo> vos = new ArrayList<ActivityVo>();
        vos.add(vo);

        vo = new ActivityVo();
        vo.setId(2L);
        vo.setImage("http://i.yingyinglicai.com/frontweb/theme/imagesnew/banner/2.jpg");
        vo.setUrl("http://licai.yingyinglicai.com/index.htm#");
        vo.setName("送积分2");
        vos.add(vo);

        vo = new ActivityVo();
        vo.setId(3L);
        vo.setImage("http://i.yingyinglicai.com/frontweb/theme/imagesnew/banner/3.jpg");
        vo.setUrl("http://licai.yingyinglicai.com/else/banner2.htm");
        vo.setName("送积分3");
        vos.add(vo);

        vo = new ActivityVo();
        vo.setId(3L);
        vo.setImage("http://i.yingyinglicai.com/frontweb/theme/imagesnew/banner/4.jpg");
        vo.setUrl("http://licai.yingyinglicai.com/else/download.htm");
        vo.setName("送积分4");
        vos.add(vo);

        pageVo.setList(vos);

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);

        System.out.println(messageUtil.toJson());
        return ok(messageUtil.toJson());
    }

    public Result getActivityListTemp() {
        ActivityParamter activityParamter = null;
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            activityParamter = Json.fromJson(body.asJson(), ActivityParamter.class);
        }

        if (body.asFormUrlEncoded() != null) {
            activityParamter = activityParameterForm.bindFromRequest().get();
        }

        PageVo pageVo = new PageVo();
        pageVo.setIndex(activityParamter.getIndex());
        pageVo.setPageSize(activityParamter.getPageSize());

        List<ActivityVo> activityVos = activityService.getActivityVos(pageVo);

        pageVo.setList(activityVos);
        if(activityVos != null) {
            pageVo.setCount(activityVos.size());
        }
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);

        Logger.debug("获取活动的信息：" + messageUtil.toJson().asText());
        return ok(messageUtil.toJson());
    }
}
