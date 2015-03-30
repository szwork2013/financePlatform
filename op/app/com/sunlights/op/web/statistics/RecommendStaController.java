package com.sunlights.op.web.statistics;

import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.common.util.Data2ExcelUtil;
import com.sunlights.op.service.statistics.RecommendStaService;
import com.sunlights.op.service.statistics.impl.RecommendStaServiceImpl;
import com.sunlights.op.vo.statistics.CustomerInOutSummaryVo;
import com.sunlights.op.vo.statistics.PurchaseItemsVo;
import com.sunlights.op.vo.statistics.RecommenderStaVo;
import com.sunlights.op.vo.statistics.Register4NotPurchaseVo;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static play.data.Form.form;

/**
 * Created by Administrator on 2015/1/13.
 */
@Transactional
public class RecommendStaController extends Controller {

    private RecommendStaService recommendStaService = new RecommendStaServiceImpl();

        public Result countSummary(){

            try {
                PageVo pageVo = getQueryPageVoParam();
                Logger.info("countsummary 开始参数为："+Json.toJson(pageVo).asText());
                List<CustomerInOutSummaryVo> CustomerInOutSummaryVos = recommendStaService.countCustomerInOutSummary(pageVo);
                pageVo.setList(CustomerInOutSummaryVos);
                return ok(Json.toJson(pageVo));
            } catch (ParseException e) {
                Logger.error("fail", e);
            }
           return ok("fail");
        }


       public Result exportCustomerInOutSummaryStaResult(){
           Logger.info("--------------export exportCustomerInOutSummaryStaResult start--------------");

           PageVo pageVo = new PageVo();
           resetFilter(pageVo, true);

           List<CustomerInOutSummaryVo> CustomerInOutSummaryVos = recommendStaService.countCustomerInOutSummary(pageVo);
           Timestamp currentTime = DBHelper.getCurrentTime();
           SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
           String time = sdf.format(currentTime);

           response().setContentType("application/vnd.ms-excel;charset=UTF-8");
           response().setHeader("Content-Disposition", "attachment;filename=customerInOutSummarySta_" + time + ".xls");

           String sheetNames =  "日期 ,累计转入人数,累计转入金额,累计转出人数,"+
                   "累计转出金额,日新增人数,日增转入金额,日增转出金额,注册人数";
           String filedNames = "eventTime,cumulativeInCustomer,totalCumulativeInCash,cumulativeOutCustomer,"+
                   "totalCumulativeOutCash,dayAddCustomer,dayAddInCash,dayAddOutCash,registeredCustomer";
           Chunks<byte[]> chunks = Data2ExcelUtil.getChunks("汇总统计", sheetNames, filedNames, CustomerInOutSummaryVos);
           Logger.info("--------------export exportCustomerInOutSummaryStaResult end--------------");
           return ok(chunks);
       }
    public Result countRecommender() {
        try {
            PageVo pageVo = getQueryPageVoParam();
            List<RecommenderStaVo> recommenderStaVos = recommendStaService.countRecommend(pageVo);
            pageVo.setList(recommenderStaVos);
            return ok(Json.toJson(pageVo));
        } catch (Exception e) {
            Logger.error("fail", e);
        }
        return ok("fail");

    }

    public Result exportRecommenderStaResult(){
        Logger.info("--------------export exportRecommenderStaResult start--------------");

        PageVo pageVo = new PageVo();
        resetFilter(pageVo, true);

        List<RecommenderStaVo> recommenderStaVos = recommendStaService.countRecommend(pageVo);
        Timestamp currentTime = DBHelper.getCurrentTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String time = sdf.format(currentTime);

        response().setContentType("application/vnd.ms-excel;charset=UTF-8");
        response().setHeader("Content-Disposition", "attachment;filename=recommenderSta_" + time + ".xls");

        String sheetNames = "推荐人,推荐成功人数,推荐购买金额（元）,推荐注册人数,注册未购买人数 ";
        String filedNames = "recommenderName,recommendSucc,purchaseAmt,registerPeoples,unPurchasePeoples";
        Chunks<byte[]> chunks = Data2ExcelUtil.getChunks("推荐人统计", sheetNames, filedNames, recommenderStaVos);
        Logger.info("--------------export exchangeResults end--------------");
        return ok(chunks);
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

            packagePageParam(pageVo, isFormSubmit);

        } catch (ParseException e) {
            Logger.error("时间解析错误", e);
        }
    }

    private void packagePageParam(PageVo pageVo, boolean isForm) throws ParseException {
        String begin = (String)pageVo.get("GED_beginTime");
        String end = (String)pageVo.get("LTD_endTime");
        String recommender = (String)pageVo.get("LIKES_recommender");

        if (StringUtils.isNotEmpty(recommender)) {
            pageVo.put("LIKES_recommender", recommender);
        } else {
            pageVo.put("LIKES_recommender", null);
        }

        if (StringUtils.isNotEmpty(begin)) {
            Date beginTime = CommonUtil.stringToDate(begin, CommonUtil.DATE_FORMAT_SHORT);
            if(!isForm) {
                beginTime = getNextDayDate(beginTime, 1);
            }

            pageVo.put("GED_beginTime", beginTime);
        } else {
            pageVo.put("GED_beginTime", null);
        }

        if (StringUtils.isNotEmpty(end)) {
            Date endTime = CommonUtil.stringToDate(end, CommonUtil.DATE_FORMAT_SHORT);
            if(!isForm) {
                endTime = getNextDayDate(endTime, 2);
            } else {
                endTime = getNextDayDate(endTime, 1);
            }
            pageVo.put("LTD_endTime", endTime);
        } else {
            pageVo.put("LTD_endTime", null);
        }
    }

    private Date getNextDayDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    public Result countPurchaseItems() {
        try {
            PageVo pageVo = getQueryPageVoParam();
            List<PurchaseItemsVo> purchaseItemsVos = recommendStaService.countPurchases(pageVo);
            pageVo.setList(purchaseItemsVos);
            return ok(Json.toJson(pageVo));
        } catch (Exception e) {
            Logger.error("fail", e);
        }
        return ok("fail");
    }

    private PageVo getQueryPageVoParam() throws ParseException {
        PageVo pageVo = Json.fromJson(request().body().asJson(), PageVo.class);
        String begin = (String)pageVo.get("GED_beginTime");
        String end = (String)pageVo.get("LTD_endTime");

        if (StringUtils.isNotEmpty(begin)) {
            Date beginTime = CommonUtil.stringToDate(begin, CommonUtil.DATE_FORMAT_SHORT);
            beginTime = getNextDayDate(beginTime, 1);
            pageVo.put("GED_beginTime", beginTime);
        } else {
            pageVo.put("GED_beginTime", null);
        }

        if (StringUtils.isNotEmpty(end)) {
            Date endTime = CommonUtil.stringToDate(end, CommonUtil.DATE_FORMAT_SHORT);
            endTime = getNextDayDate(endTime, 2);
            pageVo.put("LTD_endTime", endTime);
        } else {
            pageVo.put("LTD_endTime", null);
        }
        return pageVo;
    }

    public Result exportPurchaseItemsResult(){
        PageVo pageVo = new PageVo();
        resetFilter(pageVo, true);

        List<PurchaseItemsVo> PurchaseItemsVos = recommendStaService.countPurchases(pageVo);
        Timestamp currentTime = DBHelper.getCurrentTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String time = sdf.format(currentTime);

        response().setContentType("application/vnd.ms-excel;charset=UTF-8");
        response().setHeader("Content-Disposition", "attachment;filename=purchaseItems_" + time + ".xls");

        String sheetNames = "日期,推荐人,推荐人手机号,新增购买人姓名,手机号,转入金额,银行卡张数,具体银行";
        String filedNames = "date,recommenderName,mobile,purchaseName,cbMobile,inAmt,bankCardNum,bankNames";
        Chunks<byte[]> chunks = Data2ExcelUtil.getChunks("购买人明细", sheetNames, filedNames, PurchaseItemsVos);
        Logger.info("--------------export exchangeResults end--------------");

        return ok(chunks);
    }

    public Result countNotPurchases() {
        try {
            PageVo pageVo = getQueryPageVoParam();
            List<Register4NotPurchaseVo> register4NotPurchaseVos = recommendStaService.countRegisterNotPurchases(pageVo);
            pageVo.setList(register4NotPurchaseVos);
            return ok(Json.toJson(pageVo));
        } catch (Exception e) {
            Logger.error("fail", e);
        }
        return ok("fail");
    }

    public Result exportNotPurchasesResult(){
        Logger.info("--------------export exportRecommenderStaResult start--------------");

        PageVo pageVo = new PageVo();
        resetFilter(pageVo, true);

        List<Register4NotPurchaseVo> register4NotPurchaseVos = recommendStaService.countRegisterNotPurchases(pageVo);
        Timestamp currentTime = DBHelper.getCurrentTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String time = sdf.format(currentTime);

        response().setContentType("application/vnd.ms-excel;charset=UTF-8");
        response().setHeader("Content-Disposition", "attachment;filename=NotPurchaseSta_" + time + ".xls");

        String sheetNames = "推荐人,注册未购买姓名,注册未购买手机 ";
        String filedNames = "recommenderName,customerName,mobile";
        Chunks<byte[]> chunks = Data2ExcelUtil.getChunks("推荐人统计", sheetNames, filedNames, register4NotPurchaseVos);
        Logger.info("--------------export exchangeResults end--------------");
        return ok(chunks);
    }

}
