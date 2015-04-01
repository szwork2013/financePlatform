package com.sunlights.op.web;

import com.sunlights.op.common.util.ExcelUtil;
import com.sunlights.op.dto.BaseXlsDto;
import com.sunlights.op.dto.ReconcileFroXlsDto;
import com.sunlights.op.service.ReconcileService;
import com.sunlights.op.service.impl.ReconcileServiceImpl;
import com.sunlights.op.vo.ReconcileVo;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by guxuelong on 2014/12/4.
 */
@Transactional
public class ReconcileController extends UploadController {
    private static final String SYSTEM_ERROR = "系统异常，请联系后台服务维护人员！";

    private ReconcileService service = new ReconcileServiceImpl();

    /**
     * 对账信息展示首页（分页第一页）
     *
     * @return <Result>返回首页页面
     */
    public Result findReconcileResult() {
        try {
            ReconcileVo pageVo = Json.fromJson(request().body().asJson(), ReconcileVo.class);
            Logger.info("对账首页跳转；[checkDate]:" + pageVo.get("checkDate"));
            return ok(Json.toJson(service.findReconcileResult(pageVo)));
        } catch (Exception e) {
            Logger.error("对账首页跳转异常；错误信息：" + e.getMessage());
            return Controller.badRequest(SYSTEM_ERROR);
        }
    }

    /**
     * 对账详情展示
     *
     * @return <Result>返回首页页面
     */
    public Result findReconcileDetail() {
        try {
            String tradeNo = Json.fromJson(request().body().asJson(), String.class);
            Logger.info("对账详情页跳转；[tradeNo]:" + tradeNo);
            return ok(Json.toJson(service.findReconcileDetail(tradeNo)));
        } catch (Exception e) {
            Logger.error("对账详情页跳转异常；错误信息：" + e.getMessage());
            return Controller.badRequest(SYSTEM_ERROR);
        }
    }

    /**
     * 对单个日期对账
     *
     * @return <Result>返回首页页面
     */
    public Result reconcile() {
        try {
            String tradeTimeStr = Json.fromJson(request().body().asJson(), String.class);
            String tradeTime = tradeTimeStr.substring(0, 10);
            Logger.info("对账功能跳转；[tradeTime]:" + tradeTime);
            ReconcileVo vo = service.reconcileByTradeTime(tradeTime);
            return ok(Json.toJson(vo));
        } catch (Exception e) {
            Logger.error("对账功能异常；错误信息：" + e.getMessage());
            return Controller.badRequest(SYSTEM_ERROR);
        }
    }

    public Result upload() {
        String result = uploadExcel();
        if (!"ok".equals(result)) {
            return badRequest(result);
        }
        List<BaseXlsDto> dtoList = null;
        try {
            ReconcileFroXlsDto baseXlsDto = new ReconcileFroXlsDto();
                dtoList = ExcelUtil.readExcel(exlcelFile, fileType, baseXlsDto);
        } catch (Exception e) {
            return badRequest("上传文件失败");
        }
        Logger.info("上传文件成功");
        return ok();
    }
}
