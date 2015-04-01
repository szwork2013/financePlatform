package com.sunlights.op.web.activity;

import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.op.service.activity.ObtainRewardRuleService;
import com.sunlights.op.service.activity.impl.ObtainRewardRuleServiceImpl;
import com.sunlights.op.vo.activity.ObtainRewardRuleVo;
import models.ObtainRewardRule;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.Map;

import static play.data.Form.form;

/**
 * Created by Administrator on 2014/11/13.
 */
@Transactional
public class ObtainRewardRuleController extends Controller {

    private ObtainRewardRuleService obtainRewardRuleService = new ObtainRewardRuleServiceImpl();

    public Result findRulesByActivityId() {

        Map<String, String> activityIdMap  = form().bindFromRequest().data();
        List<ObtainRewardRuleVo> list = obtainRewardRuleService.findVosByActivityId(Long.valueOf(activityIdMap.get("value")));
        return ok(Json.toJson(list));
    }

    public Result saveRule() {
        try {
            Http.RequestBody body = request().body();
            if (body.asJson() != null) {
                ObtainRewardRuleVo rule = Json.fromJson(body.asJson(), ObtainRewardRuleVo.class);

                ObtainRewardRuleVo newRule = null;
                ObtainRewardRule obtainRewardRule = new ObtainRewardRule();
                obtainRewardRule = ConverterUtil.toEntity(obtainRewardRule, rule);
                if(rule.getId() == null) {

                    newRule = obtainRewardRuleService.add(obtainRewardRule);
                } else {
                    obtainRewardRuleService.modify(obtainRewardRule);
                }
                if(newRule != null) {
                    return ok(Json.toJson(newRule));
                }
                return ok("操作成功");
            }
        } catch (Exception e) {
            Logger.error("保存失败", e);
        }

        return ok("操作失败");
    }

    public Result updateRule() {

        return ok("更新失败");
    }

    public Result deleteRule() {
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            ObtainRewardRuleVo rule = Json.fromJson(body.asJson(), ObtainRewardRuleVo.class);
            obtainRewardRuleService.remove(rule.getId());
            return ok("删除成功");
        }
        return ok("删除失败");
    }
}
