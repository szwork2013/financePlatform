package com.sunlights.op.web.activity;

import com.sunlights.op.service.activity.ExchangeRewardRuleService;
import com.sunlights.op.service.activity.impl.ExchangeRewardRuleServiceImpl;
import com.sunlights.op.vo.activity.RewardTypeVo;
import models.ExchangeRewardRule;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static play.data.Form.form;

/**
 * Created by Administrator on 2014/11/13.
 */
@Transactional
public class ExchangeRewardRuleController extends Controller {

    private ExchangeRewardRuleService exchangeRewardRuleService = new ExchangeRewardRuleServiceImpl();

    public Result findRulesByRewardType() {

        Map<String, String> params = form().bindFromRequest().data();
        String rewardType = params.get("rewardType");

        List<ExchangeRewardRule> list = exchangeRewardRuleService.findRulesByRewardType(rewardType);

        return ok(Json.toJson(list));
    }

    public Result saveRule() {
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            RewardTypeVo rewardTypeVo = Json.fromJson(body.asJson(), RewardTypeVo.class);
            ExchangeRewardRule rule = new ExchangeRewardRule();
            rule.setId(rewardTypeVo.getRuleId());
            rule.setRewardType(rewardTypeVo.getCode());
            rule.setRate(new BigDecimal(rewardTypeVo.getRate()));
            rule.setLimitTime(Integer.valueOf(rewardTypeVo.getLimitTime()));

            if(rule.getId() == null) {
                exchangeRewardRuleService.add(rule);
            } else {
                exchangeRewardRuleService.modify(rule);
            }
            return ok("操作成功");
        }
        return ok("操作失败");
    }



    public Result deleteRule(Long id) {

        exchangeRewardRuleService.remove(id);
        return ok("删除成功");

    }
}
