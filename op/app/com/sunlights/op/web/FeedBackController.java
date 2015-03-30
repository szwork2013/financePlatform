package com.sunlights.op.web;

import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.FeedBackService;
import com.sunlights.op.service.impl.FeedBackServiceImpl;
import com.sunlights.op.vo.FeedBackVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.ok;

/**
 * <p>Project: OperationPlatform</p>
 * <p>Title: FeedBackController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
@Transactional
public class FeedBackController {

	private FeedBackService feedBackService = new FeedBackServiceImpl();

	public Result findFeedBacks() {
		PageVo pageVo = new PageVo();

		Http.Request request = request();
		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();

		if (body.asJson() != null) {
			pageVo = Json.fromJson(body.asJson(), PageVo.class);
		}

		List<FeedBackVo> feedBackVos = feedBackService.findFeedBacksBy(pageVo);
		pageVo.setList(feedBackVos);
		return ok(Json.toJson(pageVo));
	}

	public Result approveFeedBack () {
		Http.RequestBody body = request().body();
		FeedBackVo feedBackVo = null;
		if (body.asJson() != null) {
			feedBackVo = Json.fromJson(body.asJson(), FeedBackVo.class);
		}

		if (feedBackVo != null) {
			feedBackService.updateFeedBack(feedBackVo);
			return ok("审核成功");
		}
		return ok("审核失败");
	}

	public Result deleteFeedBacks() {
		Http.RequestBody body = request().body();
		FeedBackVo feedBackVo = null;
		if (body.asJson() != null) {
			feedBackVo = Json.fromJson(body.asJson(), FeedBackVo.class);
		}

		if (feedBackVo != null) {
			feedBackService.deleteFeedBack(feedBackVo);
			return ok("删除成功");
		}
		return ok("删除失败");
	}
}
