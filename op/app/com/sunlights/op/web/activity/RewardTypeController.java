package com.sunlights.op.web.activity;

import com.google.common.collect.Lists;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.activity.ExchangeRewardRuleService;
import com.sunlights.op.service.activity.RewardTypeService;
import com.sunlights.op.service.activity.impl.ExchangeRewardRuleServiceImpl;
import com.sunlights.op.service.activity.impl.RewardTypeServiceImpl;
import com.sunlights.op.vo.KeyValueVo;
import com.sunlights.op.vo.activity.RewardTypeVo;
import models.ExchangeRewardRule;
import models.RewardType;
import org.apache.commons.lang3.StringUtils;
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
    private MessageUtil messageUtil = MessageUtil.getInstance();

    private RewardTypeService rewardTypeService = new RewardTypeServiceImpl();

    private ExchangeRewardRuleService exchangeRewardRuleService;

    public RewardTypeController() {
        exchangeRewardRuleService = new ExchangeRewardRuleServiceImpl();
    }

    public Result findRewardTypes() {
        PageVo pageVo = new PageVo();
        Http.Request request = request();

        if (!StringUtils.isBlank(request.getHeader("params"))) {
            pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
        }

        List<RewardTypeVo> rewardTypeVos = rewardTypeService.findAllTypeWithRule(pageVo);

        if(rewardTypeVos == null) {
            rewardTypeVos = Lists.newArrayList();
        }
        Logger.debug("rewardTypeVos = " + rewardTypeVos);

        pageVo.setList(rewardTypeVos);

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
        return ok(messageUtil.toJson());
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
        result.add(new KeyValueVo("", "请选择"));
        for(RewardType type : rewardTypes) {
            result.add(new KeyValueVo(type.getCode(), type.getName()));
        }
        return ok(Json.toJson(result));
    }
}
