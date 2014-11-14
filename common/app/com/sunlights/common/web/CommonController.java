package com.sunlights.common.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.service.CommonService;
import com.sunlights.common.vo.DictVo;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: CommonController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class CommonController extends Controller {

    private CommonService commonService = new CommonService();

    public Result findDictsByCat() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            JsonNode cat = body.asJson().findValue("cat");
            List<DictVo> dicts = commonService.findDictsByCat(cat.asText());
            return ok(Json.toJson(dicts));
        }
        return badRequest("操作失败");
    }

}
