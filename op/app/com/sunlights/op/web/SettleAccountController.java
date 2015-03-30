package com.sunlights.op.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.utils.RequestUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.common.util.Data2ExcelUtil;
import com.sunlights.op.service.SettleAccountService;
import com.sunlights.op.service.impl.SettleAccountServiceImpl;
import com.sunlights.op.vo.SettleAccountVo;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static play.data.Form.form;

/**
 * <p>Project: operationplatform</p>
 * <p>Title: SettleAccountController.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Transactional
public class SettleAccountController extends Controller {

    private SettleAccountService settleAccountService = new SettleAccountServiceImpl();

    public Result findSettleAccounts(){
        Logger.debug("---------findSettleAccounts start----------");
        PageVo pageVo = new PageVo();
		Http.Request request = request();

		if(request.queryString() != null) {
			pageVo = RequestUtil.fromQueryString(request.queryString(), PageVo.class);
		}

		Http.RequestBody body = request.body();

        if (body.asJson() != null) {
            pageVo = Json.fromJson(body.asJson(), PageVo.class);
            resetFilter(pageVo, false);
        }

        List<SettleAccountVo> settleAccountVos = settleAccountService.findSettleAccountVos(pageVo);
        BigDecimal allTradeAmount = BigDecimal.ZERO;
        for (SettleAccountVo settleAccountVo : settleAccountVos) {
            allTradeAmount = allTradeAmount.add(new BigDecimal(settleAccountVo.getTradeAmount()));
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("list", settleAccountVos);
        resultMap.put("allTradeAmount", allTradeAmount);
        JsonNode json = MessageUtil.getInstance().msgToJson(new Message(Severity.INFO, MsgCode.OPERATE_SUCCESS), resultMap);

        Logger.debug("---------findSettleAccounts end-----------\n" + json.toString());
        return ok(json);
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
            String fundType = (String)pageVo.get("EQS_productType");
            String begin = (String)pageVo.get("GED_beginTime");
            String end = (String)pageVo.get("LTD_endTime");
            String format = isFormSubmit ? "MM/dd/yyyy" : "yyyy-MM-dd'T'HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            if (StringUtils.isNotEmpty(begin)) {
                pageVo.put("GED_beginTime", simpleDateFormat.parse(begin));
            }
            if (StringUtils.isNotEmpty(end)) {
                Date endDate = simpleDateFormat.parse(end);
                pageVo.put("LTD_endTime", DateUtils.addDays(endDate, 1));
            }
            if (StringUtils.isNotEmpty(fundType)) {
                pageVo.put("EQS_productType", fundType.substring(0, 1));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Result exportSettleAccounts(){
        Logger.info("--------------export SettleAccounts start--------------");

        PageVo pageVo = new PageVo();
        resetFilter(pageVo, true);

        List<SettleAccountVo> settleAccountVos = settleAccountService.findSettleAccountVos(pageVo);
        Timestamp currentTime = DBHelper.getCurrentTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String time = sdf.format(currentTime);

        response().setContentType("application/vnd.ms-excel;charset=UTF-8");
        response().setHeader("Content-Disposition", "attachment;filename=SettleAccount_" + time + ".xls");

        String sheetNames = "交易日期,交易单号,基金代码,基金类型,基金公司,交易状态,支付状态,交易类型,交易总金额（元）";
        String filedNames = "tradeTime,tradeNo,productCode,productTypeDesc,companyName,tradeTypeDesc,payStatusDesc,tradeTypeDesc,tradeAmount";
        Chunks<byte[]> chunks = Data2ExcelUtil.getChunks("清算统计", sheetNames, filedNames, settleAccountVos);
        Logger.info("--------------export SettleAccounts end--------------");
        return ok(chunks);
    }




}
