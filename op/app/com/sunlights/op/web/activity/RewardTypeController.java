package com.sunlights.op.web.activity;

import com.google.common.collect.Lists;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.activity.ExchangeRewardRuleService;
import com.sunlights.op.service.activity.RewardTypeService;
import com.sunlights.op.service.activity.impl.ExchangeRewardRuleServiceImpl;
import com.sunlights.op.service.activity.impl.RewardTypeServiceImpl;
import com.sunlights.op.vo.KeyValueVo;
import com.sunlights.op.vo.activity.RewardTypeVo;
import models.ExchangeRewardRule;
import models.RewardType;
import play.Logger;
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
public class RewardTypeController extends Controller {

    private RewardTypeService rewardTypeService = new RewardTypeServiceImpl();

    private ExchangeRewardRuleService exchangeRewardRuleService;

    public RewardTypeController() {
        exchangeRewardRuleService = new ExchangeRewardRuleServiceImpl();
    }

    public Result findRewardTypes() {
        List<RewardTypeVo> rewardTypeVos = rewardTypeService.findAllTypeWithRule();

        if(rewardTypeVos == null) {
            rewardTypeVos = Lists.newArrayList();
        }
        Logger.debug("rewardTypeVos = " + rewardTypeVos);

        PageVo pageVo = new PageVo();//为了分页
        pageVo.setList(rewardTypeVos);
        return ok(Json.toJson(pageVo));
    }

    public Result saveRewardType() {
        Http.RequestBody body = request().body();
        if (body.asJson() != null) {
            RewardTypeVo rewardTypeVo = Json.fromJson(body.asJson(), RewardTypeVo.class);
            RewardType type = new RewardType();
            type.setId(rewardTypeVo.getTypeId());
            type.setCode(rewardTypeVo.getCode());
            type.setName(rewardTypeVo.getName());
            type.setUnit(rewardTypeVo.getUnit());
            type.setRuleUrl(rewardTypeVo.getRuleUrl());
            if(type.getId() == null) {
                rewardTypeService.add(type);
            } else {
                rewardTypeService.modify(type);
            }
            return ok("操作成功");
        }
        return ok("操作失败");
    }

    public Result deleteRewardType(String code) {
        List<ExchangeRewardRule> exchangeRewardRules = exchangeRewardRuleService.findRulesByRewardType(code);
        if(exchangeRewardRules != null && !exchangeRewardRules.isEmpty()) {
            exchangeRewardRuleService.removeByTypeId(code);            }
        rewardTypeService.removeByCode(code);

        return ok("删除成功");
    }

    public Result loadTypeKv() {
        List<RewardType> rewardTypes = rewardTypeService.findAllTypes();
        List<KeyValueVo> result = new ArrayList<KeyValueVo>();
        for(RewardType type : rewardTypes) {
            result.add(new KeyValueVo(type.getCode(), type.getName()));
        }
        return ok(Json.toJson(result));
    }
}
