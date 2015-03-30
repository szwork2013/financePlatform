package com.sunlights.op.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dto.ReferrerExportXlsDto;
import com.sunlights.op.service.ReferrerService;
import com.sunlights.op.service.impl.ReferrerServiceImpl;
import com.sunlights.op.vo.statistics.ReferrerDetailVo;
import com.sunlights.op.vo.statistics.ReferrerVo;
import org.apache.commons.lang3.StringUtils;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by Yuan on 2015/1/13.
 */
@Transactional
public class ReferrerController extends Controller {
	private MessageUtil messageUtil = MessageUtil.getInstance();

	private ReferrerService referrerService = new ReferrerServiceImpl();

	public Result exportReferrer() {
		PageVo pageVo = new PageVo();
		Map<String, String[]> filterMap = request().queryString();
		for (String key : filterMap.keySet()) {
			String[] filter = filterMap.get(key);
			if (filter.length > 0 && StringUtils.isNotBlank(filter[0])) {
				pageVo.put(key, filter[0].replace("\"", ""));
			}
		}

		Timestamp currentTime = DBHelper.getCurrentTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String time = sdf.format(currentTime);

		response().setContentType("application/vnd.ms-excel;charset=UTF-8");
		response().setHeader("Content-Disposition", "attachment;filename=referrer_" + time + ".xls");


		List<ReferrerVo> referrerVos = referrerService.findReferrers(pageVo);
		ReferrerExportXlsDto referrerExportXlsDto = new ReferrerExportXlsDto();
		referrerExportXlsDto.setData(referrerVos);
		Chunks<byte[]> export = referrerExportXlsDto.export();
		return ok(export);
	}

	public Result findReferrerDetails() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		List<ReferrerDetailVo> referrerDetails = referrerService.findReferrerDetails(pageVo);
		pageVo.setList(referrerDetails);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

	/**
	 * @author Yuan
	 */
	public Result findReferrers() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();

		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		List<ReferrerVo> referrers = referrerService.findReferrers(pageVo);
		pageVo.setList(referrers);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

}
