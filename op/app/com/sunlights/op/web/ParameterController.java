package com.sunlights.op.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.ParameterService;
import com.sunlights.op.service.impl.ParameterServiceImpl;
import com.sunlights.op.vo.ParameterVo;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: ParameterController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class ParameterController extends Controller {


    private MessageUtil messageUtil = MessageUtil.getInstance();

    private ParameterService parameterService = new ParameterServiceImpl();


    public Result findParameters() {
        PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
        }

        Logger.info(Json.toJson(pageVo).toString());

        List<ParameterVo> parameterVos = parameterService.findParametersBy(pageVo);
		pageVo.setList(parameterVos);
        messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
        return ok(messageUtil.toJson());
    }


    public Result deleteParameter() {
		ParameterVo parameterVo = null;
		Http.Request request = request();

		if (request.queryString() != null) {
			parameterVo = RequestUtil.fromQueryString(request.queryString(), ParameterVo.class);
		}

		Http.RequestBody body = request.body();

        if (body.asJson() != null) {
            parameterVo = Json.fromJson(body.asJson(), ParameterVo.class);
        }

		if(parameterVo != null) {
			parameterService.delete(parameterVo);
			messageUtil.setMessage(new Message(Severity.INFO, MsgCode.DELETE_SUCCESS));
			return ok(messageUtil.toJson());
		}
        messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.DELETE_FAILURE));
        return badRequest(messageUtil.toJson());
    }

	public Result saveParameter() {
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            ParameterVo parameterVo = Json.fromJson(body.asJson(), ParameterVo.class);
            Message message = null;
            if (parameterVo.getId() == null) {
                parameterService.create(parameterVo);
                message = new Message(Severity.INFO, MsgCode.CREATE_SUCCESS);
            } else {
                parameterService.update(parameterVo);
                message = new Message(Severity.INFO, MsgCode.UPDATE_SUCCESS);
            }
            messageUtil.setMessage(message);
            return ok(messageUtil.toJson());

        }
        messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.OPERATE_FAILURE));
        return badRequest(messageUtil.toJson());
    }

    public Result refreshParameter(){
        com.sunlights.common.service.ParameterService service = new com.sunlights.common.service.ParameterService();
        service.refresh();
        Logger.info(">>>参数缓存刷新完成");
        messageUtil.setMessage(new Message(MsgCode.OPERATE_SUCCESS));
        return ok(messageUtil.toJson());

    }



}
