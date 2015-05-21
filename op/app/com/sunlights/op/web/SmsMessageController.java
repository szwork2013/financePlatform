package com.sunlights.op.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.SmsMessageService;
import com.sunlights.op.service.impl.SmsMessageServiceImpl;
import com.sunlights.op.vo.SmsMessageVo;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static play.data.Form.form;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: SmsMessageController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */

@Transactional
public class SmsMessageController extends Controller {
    private SmsMessageService smsMessageService = new SmsMessageServiceImpl();

    public Result findSmsMessageVos() {
        PageVo pageVo = new PageVo();
        Http.RequestBody body = request().body();

        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
            resetFilter(pageVo, false);
        }

        if (!StringUtils.isBlank(request().getHeader("params"))) {
            pageVo = RequestUtil.getHeaderValue("params", PageVo.class);
            resetSearchTime(pageVo);
        }

        List<SmsMessageVo> smsMessageVos = smsMessageService.findSmsMessageVos(pageVo);
        int allCount = 0;
        for (SmsMessageVo smsMessageVo : smsMessageVos) {
            allCount += smsMessageVo.getCount();
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("list", smsMessageVos);
        resultMap.put("allCount", allCount);
        JsonNode json = MessageUtil.getInstance().msgToJson(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), resultMap);

        Logger.debug("---------findSmsMessageVos-----------\n" + json.toString());
        return ok(json);
    }

    private void resetSearchTime(PageVo pageVo) {
        Object beginTime = pageVo.get("GED_beginTime");
        if (beginTime != null) {
            pageVo.put("GED_beginTime", CommonUtil.stringToDateTime(beginTime.toString()));
        }
        Object endTime = pageVo.get("LTD_endTime");
        if (endTime != null) {
            pageVo.put("LTD_endTime", CommonUtil.stringToDateTime(endTime.toString()));
        }
    }

    private void resetFilter(PageVo pageVo, boolean isFormSubmit) {

        try {
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
            String format = isFormSubmit ? "MM/dd/yyyy" : "yyyy-MM-dd'T'HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            if (StringUtils.isNotEmpty(begin)) {
                pageVo.put("GED_beginTime", simpleDateFormat.parse(begin));
            }
            if (StringUtils.isNotEmpty(end)) {
                pageVo.put("LTD_endTime", simpleDateFormat.parse(end));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Result exportSmsMessages() {
        Logger.info("--------------export SmsMessage start--------------");

        PageVo pageVo = new PageVo();

        Map<String, String[]> filterMap = request().queryString();
        for (String key : filterMap.keySet()) {
            String[] filter = filterMap.get(key);
            if (filter.length > 0 && StringUtils.isNotBlank(filter[0])) {
                pageVo.put(key, filter[0].replace("\"", ""));
            }
        }
        resetSearchTime(pageVo);

        final List<SmsMessageVo> smsMessageVos = smsMessageService.findSmsMessageVos(pageVo);
        Timestamp currentTime = DBHelper.getCurrentTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String time = sdf.format(currentTime);

        response().setContentType("application/vnd.ms-excel;charset=UTF-8");
        response().setHeader("Content-Disposition", "attachment;filename=SmsMessage_" + time + ".xls");

        Chunks<byte[]> chunks = getChunks(smsMessageVos);
        Logger.info("--------------export SmsMessage end--------------");




        return ok(chunks);
    }

    private Chunks<byte[]> getChunks(final List<SmsMessageVo> smsMessageVos) {
        return new ByteChunks() {

                public void onReady(Out<byte[]> out) {

                    ByteArrayOutputStream bao = new ByteArrayOutputStream();

                    WritableWorkbook workbook = null;
                    try {
                        workbook = Workbook.createWorkbook(bao);

                        WritableSheet sheet = workbook.createSheet("短信统计", 0);

                        Label label = new Label(0, 0, "发送时间");
                        sheet.addCell(label);
                        label = new Label(1, 0, "发送状态");
                        sheet.addCell(label);
                        label = new Label(2, 0, "总条数");
                        sheet.addCell(label);

                        for (int i = 1; i <= smsMessageVos.size(); i++) {
                            SmsMessageVo smsMessageVo = smsMessageVos.get(i - 1);

                            Label sendTimeLabel = new Label(0, i, smsMessageVo.getSendTime());
                            sheet.addCell(sendTimeLabel);
                            Label sendStatusLabel = new Label(1, i, AppConst.STATUS_VALID.equals(smsMessageVo.getSendStatus()) ? "成功" : "失败");
                            sheet.addCell(sendStatusLabel);
                            Label countLabel = new Label(2, i, smsMessageVo.getCount() + "");
                            sheet.addCell(countLabel);
                        }

                        workbook.write();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (workbook != null) {
                                workbook.close();
                            }
                            bao.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    out.write(bao.toByteArray());
                    out.close();
                }
            };
    }
}
