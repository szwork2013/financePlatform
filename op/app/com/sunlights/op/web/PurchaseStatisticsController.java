package com.sunlights.op.web;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dto.FirstPurchaseExportXlsDto;
import com.sunlights.op.dto.TradeSummaryExportXlsDto;
import com.sunlights.op.dto.UnPurchaseExportXlsDto;
import com.sunlights.op.service.PurchaseStatisticsService;
import com.sunlights.op.service.impl.PurchaseStatisticsServiceImpl;
import com.sunlights.op.vo.PurchaseStatisticsVo;
import com.sunlights.op.vo.TradeSummaryVo;
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
 * Created by Yuan on 2015/3/26.
 */
@Transactional
public class PurchaseStatisticsController extends Controller {
	private MessageUtil messageUtil = MessageUtil.getInstance();

	private PurchaseStatisticsService purchaseStatisticsService = new PurchaseStatisticsServiceImpl();

	public  Result findSummaries () {
		PageVo pageVo = new PageVo();
		Http.Request request = request();
		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}
		List<TradeSummaryVo> tradeSummaryVos = purchaseStatisticsService.findTradeSummaryVos(pageVo);
		pageVo.setList(tradeSummaryVos);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

	public Result exportSummary () {
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
		response().setHeader("Content-Disposition", "attachment;filename=summary_" + time + ".xls");

		List<TradeSummaryVo> tradeSummaryVos = purchaseStatisticsService.findTradeSummaryVos(pageVo);
		TradeSummaryExportXlsDto tradeSummaryExportXlsDto = new TradeSummaryExportXlsDto();
		tradeSummaryExportXlsDto.setData(tradeSummaryVos);
		Chunks<byte[]> export = tradeSummaryExportXlsDto.export();
		return ok(export);
	}

	public Result exportUnPurchase() {
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
		response().setHeader("Content-Disposition", "attachment;filename=unPurchase_" + time + ".xls");

		List<PurchaseStatisticsVo> unPurchaseVos = purchaseStatisticsService.findUnPurchaseVos(pageVo);

		UnPurchaseExportXlsDto unPurchaseExportXlsDto = new UnPurchaseExportXlsDto();
		unPurchaseExportXlsDto.setData(unPurchaseVos);
		Chunks<byte[]> export = unPurchaseExportXlsDto.export();
		return ok(export);
	}

	public Result findUnPurchases() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();
		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}
		List<PurchaseStatisticsVo> unPurchaseVos = purchaseStatisticsService.findUnPurchaseVos(pageVo);
		pageVo.setList(unPurchaseVos);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

	public Result exportFirstPurchase() {
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
		response().setHeader("Content-Disposition", "attachment;filename=purchase_" + time + ".xls");

		List<PurchaseStatisticsVo> purchaseStatisticsVos = purchaseStatisticsService.findFirstPurchaseVos(pageVo);

		FirstPurchaseExportXlsDto firstPurchaseExportXlsDto = new FirstPurchaseExportXlsDto();
		firstPurchaseExportXlsDto.setData(purchaseStatisticsVos);
		Chunks<byte[]> export = firstPurchaseExportXlsDto.export();
		return ok(export);
	}

	public Result findFirstPurchases() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();
		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}
		List<PurchaseStatisticsVo> purchaseStatisticsVos = purchaseStatisticsService.findFirstPurchaseVos(pageVo);
		pageVo.setList(purchaseStatisticsVos);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}
}
