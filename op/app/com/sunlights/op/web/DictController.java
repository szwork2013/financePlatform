package com.sunlights.op.web;

import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.DictService;
import com.sunlights.op.service.impl.DictServiceImpl;
import com.sunlights.op.vo.DictManageVo;
import com.wordnik.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: DictController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Api(value = "/op", description = "字典表")
@Transactional
public class DictController extends Controller {

    private DictService dictService = new DictServiceImpl();

    @ApiOperation(value = "获取数据字典表", nickname = "dicts", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 2000, message = "操作失败")})
    @ApiImplicitParams({
            @ApiImplicitParam(name="params", value = "PageVo对象的JSON字符串", dataType = "PageVo", paramType = "header")
    })
    public Result findDicts() {
        PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }
        List<DictManageVo> dictManageVos = dictService.findDictsBy(pageVo);
        pageVo.setList(dictManageVos);
        return ok(Json.toJson(pageVo));
    }

    public Result deleteDict() {
		DictManageVo dictManageVo = null;
		Http.Request request = request();
		if (request.queryString() != null) {
			dictManageVo = RequestUtil.fromQueryString(request.queryString(), DictManageVo.class);
		}

		Http.RequestBody body = request.body();

        if (body.asJson() != null) {
            dictManageVo = Json.fromJson(body.asJson(), DictManageVo.class);
        }

		if (dictManageVo != null) {
			dictService.delete(dictManageVo);
			return ok("删除成功");
		}
        return badRequest("删除失败");
    }

    public Result saveDict() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            DictManageVo dictManageVo = Json.fromJson(body.asJson(), DictManageVo.class);
            if (dictManageVo.getId() == null) {
                dictService.create(dictManageVo);
                return ok("创建成功");
            } else {
                dictService.update(dictManageVo);
                return ok("更新成功");
            }
        }
        return ok("操作失败");
    }
}
