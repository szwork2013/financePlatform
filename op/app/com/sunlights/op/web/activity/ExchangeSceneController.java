package com.sunlights.op.web.activity;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.activity.ExchangeSceneService;
import com.sunlights.op.service.activity.impl.ExchangeSceneServiceImpl;
import com.sunlights.op.vo.activity.ExchangeSceneVo;
import models.ExchangeScene;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Administrator on 2014/12/11.
 */
@Transactional
public class ExchangeSceneController extends Controller {

    private ExchangeSceneService exchangeSceneService = new ExchangeSceneServiceImpl();

    private MessageUtil messageUtil = MessageUtil.getInstance();

    public Result findAllScenes() {

        PageVo pageVo = new PageVo();
        Http.Request request = request();

        if (!StringUtils.isBlank(request.getHeader("params"))) {
            pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
        }
        List<ExchangeSceneVo> exchangeSceneVos = exchangeSceneService.findAllScenes(pageVo);
        pageVo.setList(exchangeSceneVos);

        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
        return ok(messageUtil.toJson());
    }

    public Result save() {
        try {
            Http.RequestBody body = request().body();
            if (body.asJson() != null) {
                ExchangeSceneVo exchangeSceneVo = Json.fromJson(body.asJson(), ExchangeSceneVo.class);

                ExchangeScene exchangeScene = ConverterUtil.toEntity(new ExchangeScene(), exchangeSceneVo);
                if(exchangeScene.getId() == null) {
                    exchangeSceneVo = exchangeSceneService.save(exchangeScene);
                } else {
                    exchangeSceneVo = exchangeSceneService.modify(exchangeScene);
                }
                return ok(Json.toJson(exchangeSceneVo));
            }
        } catch (Exception e) {

        }
        return ok("操作失败");
    }

    public Result delete(Long id) {

        exchangeSceneService.remove(id);
        return ok("删除成功");

    }
}
