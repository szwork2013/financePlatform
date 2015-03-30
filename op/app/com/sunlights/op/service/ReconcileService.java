package com.sunlights.op.service;

import com.sunlights.op.vo.ReconcileVo;

import java.util.List;
import java.util.Map;

/**
 * Created by guxuelong on 2014/12/2.
 */
public interface ReconcileService {
    /**
     * 根据对账日期（交易日期）查询该日期交易对账结果
     *
     * @param pageVo
     * @return
     * @throws Exception
     */
    ReconcileVo findReconcileResult(ReconcileVo pageVo) throws Exception;

    /**
     * 根据交易流水号查询对账双方的详细信息
     *
     * @param tradeNo
     * @return
     * @throws Exception
     */
    Map<String, Object> findReconcileDetail(String tradeNo) throws Exception;

    /**
     * 对交易流水号列表进行对账
     *
     * @param tradeNoList
     * @throws Exception
     */
    void reconcileByTradeNo(List<String> tradeNoList) throws Exception;

    /**
     * 对相应交易日期进行对账
     *
     * @param tradeTime
     */
    ReconcileVo reconcileByTradeTime(String tradeTime);
}