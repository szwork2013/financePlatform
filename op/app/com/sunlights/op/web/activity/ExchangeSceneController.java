package com.sunlights.op.web.activity;

import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.activity.ExchangeSceneService;
import com.sunlights.op.service.activity.impl.ExchangeSceneServiceImpl;
import com.sunlights.op.vo.activity.ExchangeSceneVo;
import models.ExchangeScene;
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

    public Result findAllScenes() {
        List<ExchangeSceneVo> exchangeSceneVos = exchangeSceneService.findAllScenes();


        PageVo pageVo = new PageVo();//为了分页
        pageVo.setList(exchangeSceneVos);
        return ok(Json.toJson(pageVo));
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

    public Result delete() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            ExchangeSceneVo exchangeScene = Json.fromJson(body.asJson(), ExchangeSceneVo.class);
            exchangeSceneService.remove(exchangeScene.getId());
            return ok("删除成功");
        }
        return ok("删除失败");
    }
}
