package com.sunlights.op.web.activity;

import com.google.common.io.Files;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.common.util.ExcelUtil;
import com.sunlights.op.dto.BaseXlsDto;
import com.sunlights.op.dto.ExchangeResultExportXlsDto;
import com.sunlights.op.dto.ExchangeResultXlsDto;
import com.sunlights.op.service.activity.ExchangeResultService;
import com.sunlights.op.service.activity.impl.ExchangeResultServiceImpl;
import com.sunlights.op.vo.activity.ExchangeResultVo;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/12/11.
 */
@Transactional
public class ExchangeResultController extends Controller {

	private MessageUtil messageUtil = MessageUtil.getInstance();

	private ExchangeResultService exchangeResultService = new ExchangeResultServiceImpl();

	protected File exlcelFile;
	protected String fileType;
	private final static List<String> types = new ArrayList<>();
	{
		types.add("XLS");
		types.add("XLSX");
		types.add("xlsx");
		types.add("xls");
	}

	// =================================================Yuan===============================================//

	public Result uploadRedPacket() {
		Http.RequestBody body = request().body();
		if (body.asMultipartFormData() != null) {
			Http.MultipartFormData multipartFormData = body.asMultipartFormData();
			List<Http.MultipartFormData.FilePart> files = multipartFormData.getFiles();
			for (Http.MultipartFormData.FilePart file : files) {
				File orFile = file.getFile();
				String fileName = file.getFilename();
				Logger.info("[file name]" + fileName);
				Logger.info("[key]" + file.getKey());
				Logger.info("[file type]" + Files.getFileExtension(fileName));
				String type = Files.getFileExtension(fileName);
				if (!types.contains(type)) {
					messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.UPLOAD_FILE_TYPE_ERROR));
					return badRequest(messageUtil.toJson());
				}
				ExchangeResultXlsDto baseXlsDto = new ExchangeResultXlsDto();
				try {
					List<BaseXlsDto> dtoList = ExcelUtil.readExcel(orFile, type, baseXlsDto);
					int i = exchangeResultService.checkExchangeResults(dtoList);
					messageUtil.setMessage(new Message(Severity.INFO, MsgCode.EXCHANGE_CHECK_INFO, i, dtoList.size() - i));
					return ok(messageUtil.toJson());
				} catch (Exception e) {
					e.printStackTrace();
					messageUtil.setMessage(new Message(Severity.ERROR, MsgCode.UPLOAD_FILE_ERROR));
					return badRequest(messageUtil.toJson());
				}
			}
		}
		return badRequest("上传失败");
	}

	public Result exportRedPacket() {
		PageVo pageVo = new PageVo();
		Map<String, String[]> filterMap = request().queryString();
		for (String key : filterMap.keySet()) {
			String[] filter = filterMap.get(key);
			if (filter.length > 0 && StringUtils.isNotBlank(filter[0])) {
				pageVo.put(key, filter[0].replace("\"", ""));
			}
		}
		List<ExchangeResultVo> exchangeResultVos = exchangeResultService.findRedPacketExchangeBy(pageVo);
		Timestamp currentTime = DBHelper.getCurrentTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String time = sdf.format(currentTime);

		response().setContentType("application/vnd.ms-excel;charset=UTF-8");
		response().setHeader("Content-Disposition", "attachment;filename=exchangeResults_" + time + ".xls");

		ExchangeResultExportXlsDto exchangeResultExportXlsDto = new ExchangeResultExportXlsDto();
		exchangeResultExportXlsDto.setData(exchangeResultVos);
		Chunks<byte[]> export = exchangeResultExportXlsDto.export();
		Logger.info("--------------export exchangeResults end--------------");

		exchangeResultService.exportSuccessfully(exchangeResultVos);
		return ok(export);
	}

	public Result findRedPackets() {
		PageVo pageVo = new PageVo();
		Http.Request request = request();
		if (!StringUtils.isBlank(request.getHeader("params"))) {
			pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
		}

		Http.RequestBody body = request.body();
		if (body.asJson() != null) {
			pageVo = Json.fromJson(body.asJson(), PageVo.class);
		}
		List<ExchangeResultVo> redPacketExchanges = exchangeResultService.findRedPacketExchangeBy(pageVo);
		pageVo.setList(redPacketExchanges);
		messageUtil.setMessage(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), pageVo);
		return ok(messageUtil.toJson());
	}

	// =================================================Yuan===============================================//

}
