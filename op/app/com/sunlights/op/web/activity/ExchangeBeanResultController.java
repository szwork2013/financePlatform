package com.sunlights.op.web.activity;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.MessageVo;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.common.util.ExcelUtil;
import com.sunlights.op.dto.BaseXlsDto;
import com.sunlights.op.dto.ExchangeBeanResultExportXlsDto;
import com.sunlights.op.dto.ExchangeBeanResultXlsDto;
import com.sunlights.op.service.activity.ExchangeResultService;
import com.sunlights.op.service.activity.impl.ExchangeResultServiceImpl;
import com.sunlights.op.vo.activity.ExchangeBeanResultVo;
import com.sunlights.op.vo.activity.ExchangeResultStatus;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.File;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static play.data.Form.form;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: ExchangeBeanResultController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class ExchangeBeanResultController extends Controller {
    
    private ExchangeResultService exchangeResultService = new ExchangeResultServiceImpl();
    
    public Result findBeanResultList(){
        PageVo pageVo = new PageVo();
        JsonNode jsonNode = request().body().asJson();
        if (jsonNode != null) {
            pageVo = Json.fromJson(jsonNode, PageVo.class);
        }
        resetFilter(pageVo, false);
        List<ExchangeBeanResultVo> list = exchangeResultService.findExchangeBeanList(pageVo);

        return ok(Json.toJson(list));
    }

    public Result exchangeBeanExport(){
        Logger.info("--------------export exchangeBeanExport start--------------");

        PageVo pageVo = new PageVo();
        Map<String, String[]> filterMap = request().queryString();
        for (String key : filterMap.keySet()) {
            String[] filter = filterMap.get(key);
            if (filter.length > 0 && StringUtils.isNotBlank(filter[0])) {
                pageVo.put(key, filter[0].replace("\"", ""));
            }
        }

        final List<ExchangeBeanResultVo> exchangeBeanList = exchangeResultService.findExchangeBeanList(pageVo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String time = sdf.format(DBHelper.getCurrentTime());

        response().setContentType("application/vnd.ms-excel;charset=UTF-8");
        response().setHeader("Content-Disposition", "attachment;filename=BeanExchange_" + time + ".xls");

        ExchangeBeanResultExportXlsDto exchangeBeanResultXlsDto = new ExchangeBeanResultExportXlsDto();
        exchangeBeanResultXlsDto.setData(exchangeBeanList);
        Chunks<byte[]> chunks = exchangeBeanResultXlsDto.export();

        //修改状态 等待兑换-->兑换中
        List<Long> updateList = Lists.newArrayList();
        for (ExchangeBeanResultVo exchangeBeanResultVo : exchangeBeanList) {
            if (exchangeBeanResultVo.getStatus().equals(ExchangeResultStatus.AUDIT_PASS.getStatus().toString())){
                updateList.add(exchangeBeanResultVo.getId());
            }
        }
        if (!updateList.isEmpty()) {
            exchangeResultService.updateBatchResult(updateList);
        }

        Logger.info("--------------export exchangeBeanExport end--------------");

        return ok(chunks);
    }

    public Result upload(){
        Logger.info(">> upload begin: ");
        MessageVo result = ExcelUtil.uploadExcel(request());
        if (result.getMessage().getSeverity() != 0) {
            return badRequest((String)result.getValue());
        }
        Http.MultipartFormData.FilePart file = (Http.MultipartFormData.FilePart)result.getValue();
        File excelFile = file.getFile();
        String fileName = file.getFilename();
        String fileType = Files.getFileExtension(fileName);

        Logger.info(MessageFormat.format("excelFile:{0}, fileType:{1}, fileName:{2}", excelFile, fileType, fileName));

        try {
            List<BaseXlsDto> dtoList = ExcelUtil.readExcel(excelFile, fileType, new ExchangeBeanResultXlsDto());
            Logger.info("dtoList size" + dtoList.size());
            exchangeResultService.updateExchangeBeanResult(dtoList);
        } catch (Exception e) {
            Logger.error("上传文件失败", e);
            return badRequest(MessageUtil.getInstance().toJson());
        }
        Logger.info("上传文件成功");
        Logger.info(">> upload return: " + MessageUtil.getInstance().toJson());
        return ok(MessageUtil.getInstance().toJson());
    }


    private void resetFilter(PageVo pageVo, boolean isFormSubmit) {

        if (isFormSubmit) {
            Map<String, String> params = form().bindFromRequest().data();
            Iterator iterator = params.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String)iterator.next();
                pageVo.put(key, ConvertUtils.convert(params.get(key), String.class));
            }
        }
        String begin = (String)pageVo.get("GED_beginTime");
        String end = (String)pageVo.get("LTD_endTime");
        if (StringUtils.isNotEmpty(begin)) {
            pageVo.put("GED_beginTime", plusOneDay(begin));
        }
        if (StringUtils.isNotEmpty(end)) {
            pageVo.put("LTD_endTime", plusOneDay(end));
        }
    }

    private Date plusOneDay(String time) {
        DateTime beginTime = DateTime.parse(time);
        return beginTime.plusDays(1).toDate();
    }

}
