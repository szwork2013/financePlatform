package com.sunlights.op.dal;

import com.sunlights.op.vo.ReconcileVo;
import models.CheckError;
import models.CheckInfo;
import models.CheckResult;
import models.Trade;

import java.util.List;

/**
 * Created by guxuelong on 2014/12/2.
 */
public interface ReconcileDao {
    /**
     * 根据交易流水号查询单笔交易流水
     *
     * @param tradeNo
     * @return
     */
    Trade queryOrderInfoByTradeNo(String tradeNo);

    /**
     * 根据日期查询交易流水
     *
     * @param tradeTime（yyyy-MM-dd）
     * @return
     */
    List<Trade> queryOrderListByTradeTime(String tradeTime);

    /**
     * 根据[日期、对账状态] 查询数米交易流水
     *
     * @param tradeTime
     * @param chkStatus
     * @return
     */
    List<CheckInfo> queryCheckInfoByTradeTimeAndChkStatus(String tradeTime, String chkStatus);

    /**
     * 根据交易号查询数米交易流水记录
     *
     * @param tradeNo
     * @return
     */
    CheckInfo queryCheckInfoByTradeNo(String tradeNo);

    /**
     * 根据交易时间查询对账结果
     *
     * @param pageVo
     * @return
     * @throws Exception
     */
    ReconcileVo queryCheckResultByTradeTime(ReconcileVo pageVo) throws Exception;

    /**
     * 根据交易时间查询对账差错统计记录
     *
     * @param tradeTime
     * @return
     */
    List<CheckError> queryCheckErrorByTradeTime(String tradeTime);

    /**
     * 合作方对账信息新增
     *
     * @param checkInfo
     */
    void insertCheckInfo(CheckInfo checkInfo);

    /**
     * 合作方对账信息更新
     *
     * @param checkInfo
     */
    void updateCheckInfo(CheckInfo checkInfo);

    /**
     * 对账结果新增
     *
     * @param checkResult
     */
    void insertCheckResult(CheckResult checkResult);

    /**
     * 对账结果更新
     *
     * @param checkResult
     */
    void updateCheckResult(CheckResult checkResult);

    /**
     * 差错统计新增
     *
     * @param checkError
     */
    void insertCheckError(CheckError checkError);

    /**
     * 差错统计更新
     *
     * @param checkError
     */
    void updateCheckError(CheckError checkError);
}
