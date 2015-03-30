package com.sunlights.op.service.impl;

import com.sunlights.common.DictConst;
import com.sunlights.common.service.CommonService;
import com.sunlights.op.common.constants.ReconcileConstants;
import com.sunlights.op.common.enums.AccountAdjustmentStatusEnum;
import com.sunlights.op.common.enums.ReconcileStatusEnum;
import com.sunlights.op.common.enums.ReconciledErrorEnum;
import com.sunlights.op.common.enums.SettleStatusEnum;
import com.sunlights.op.dal.ReconcileDao;
import com.sunlights.op.dal.impl.ReconcileDaoImpl;
import com.sunlights.op.service.ReconcileService;
import com.sunlights.op.vo.ReconcileResultVo;
import com.sunlights.op.vo.ReconcileVo;
import models.CheckError;
import models.CheckInfo;
import models.CheckResult;
import models.Trade;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import play.Logger;

import java.util.*;

/**
 * Created by guxuelong on 2014/12/2.
 */
public class ReconcileServiceImpl implements ReconcileService {

    public static final String PARTNER = "partner";
    public static final String YI_YUN = "yiYun";
    private ReconcileDao dao = new ReconcileDaoImpl();
    private CommonService commonService = new CommonService();

    /**
     * 根据对账日期（交易日期）查询该日期交易对账结果
     *
     * @param pageVo
     * @return
     * @throws Exception
     */
    @Override
    public ReconcileVo findReconcileResult(ReconcileVo pageVo) throws Exception {
        ReconcileVo vo = dao.queryCheckResultByTradeTime(pageVo);
        vo.setList(transferData(vo.getList()));
        return vo;
    }

    /**
     * 根据交易流水号查询对账双方的详细信息
     *
     * @param tradeNo
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, Object> findReconcileDetail(String tradeNo) throws Exception {
        Logger.info("start to ReconcileServiceImpl.findReconcileDetail; tradeNo:" + tradeNo);
        Map<String, Object> rtnMap = new HashMap<>();
        Trade trade = dao.queryOrderInfoByTradeNo(tradeNo);
        CheckInfo checkInfo = dao.queryCheckInfoByTradeNo(tradeNo);
        Map<String, String> dictMap = loadValues(DictConst.TRADE_TYPE);
        setYiYun(rtnMap, trade, dictMap);
        setPartner(rtnMap, checkInfo, dictMap);
        return rtnMap;
    }

    /**
     * 对交易流水号列表进行对账
     *
     * @param tradeNoList
     * @throws Exception
     */
    @Override
    public void reconcileByTradeNo(List<String> tradeNoList) throws Exception {
        List<Trade> tradeList = getTradeList(tradeNoList);
        reconcileByTradeList(tradeList);
    }

    /**
     * 对相应交易日期进行对账
     *
     * @param tradeTime
     */
    @Override
    public ReconcileVo reconcileByTradeTime(String tradeTime) {
        ReconcileVo vo = new ReconcileVo();
        List<Trade> tradeList = dao.queryOrderListByTradeTime(tradeTime);
        checkTradeList(tradeList,vo);
        reconcileByTradeList(tradeList);
        setChkStatusOfMore(tradeTime);
        return vo;
    }

    private void checkTradeList(List<Trade> tradeList,ReconcileVo vo){
        if(tradeList==null || tradeList.size() == 0 )
            vo.setReturnCode(ReconcileConstants.RTN_CODE_FAIL);
            vo.setReturnMessage(ReconcileConstants.RTN_MSE_QUERY_TRADE_ERROR);
    }

    private void setPartner(Map<String, Object> rtnMap, CheckInfo checkInfo, Map<String, String> dictMap) {
        if (checkInfo == null) {
            return;
        }
        ReconcileResultVo resultVo = new ReconcileResultVo();
        BeanUtils.copyProperties(checkInfo, resultVo);
        resultVo.setTradeType(transferTradeType(dictMap, resultVo.getTradeType()));
        rtnMap.put(PARTNER, resultVo);
    }

    private void setYiYun(Map<String, Object> rtnMap, Trade trade, Map<String, String> dictMap) {
        if (trade == null) {
            return;
        }
        ReconcileResultVo resultVo = new ReconcileResultVo();
        BeanUtils.copyProperties(trade, resultVo);
        resultVo.setTradeDate(trade.getTradeTime());
        resultVo.setTradeType(transferTradeType(dictMap, trade.getType()));
        rtnMap.put(YI_YUN, resultVo);
    }

    private List<ReconcileResultVo> transferData(List<CheckResult> list) {
        return transfer(list, loadValues(DictConst.TRADE_TYPE));
    }

    private Map<String, String> loadValues(String type) {
        Map<String, String> dictMap = commonService.findDictMapByCat(type);
        return dictMap;
    }

    private List<ReconcileResultVo> transfer(List<CheckResult> list, Map<String, String> tradeTypes) {
        List<ReconcileResultVo> rtnList = new ArrayList<>();
        for (CheckResult result : list) {
            ReconcileResultVo vo = new ReconcileResultVo();
            BeanUtils.copyProperties(result, vo);
            vo.setTradeType(transferTradeType(tradeTypes, result.getTradeType()));
            vo.setChkStatus(transferChkStat(result.getChkStatus()));
            vo.setErrDetail(transferErrDetail(result.getErrDetail()));
            rtnList.add(vo);
        }
        return rtnList;
    }

    private String transferTradeType(Map<String, String> tradeTypes, String code) {
        return tradeTypes.get(DictConst.TRADE_TYPE + DictConst.DOT + code);
    }

    private String transferChkStat(String code) {
        return ReconcileStatusEnum.getValueByCode(code);
    }

    private String transferErrDetail(String code) {
        return ReconciledErrorEnum.getValueByCode(code);
    }

    private List<Trade> getTradeList(List<String> tradeNoList) throws Exception {
        List<Trade> tradeList = new ArrayList<>();
        for (String tradeNo : tradeNoList) {
            Trade trade = dao.queryOrderInfoByTradeNo(tradeNo);
            if (trade == null) {
                throw new Exception("未查询到相应的交易流水");
            }
            tradeList.add(trade);
        }
        return tradeList;
    }

    private void reconcileByTradeList(List<Trade> tradeList) {
        for (Trade trade : tradeList) {
            reconcile(trade);
        }
    }

    private void setChkStatusOfMore(String time) {
        List<CheckInfo> checkInfoLst = dao.queryCheckInfoByTradeTimeAndChkStatus(time, ReconcileStatusEnum.NO_ACTIVE.getCode());
        for (CheckInfo checkInfo : checkInfoLst) {
            saveData(checkInfo, ReconciledErrorEnum.YI_YUE_NO_DATA_ERROR.getCode());
        }
    }

    private void reconcile(Trade trade) {
        CheckInfo checkInfo = dao.queryCheckInfoByTradeNo(trade.getTradeNo());
        String errorCode;
        if (checkInfo != null) {
            errorCode = checkOrder(trade, checkInfo);
        } else {
            errorCode = ReconciledErrorEnum.PARTNER_NO_DATA_ERROR.getCode();
            checkInfo = setCheckInfo(trade);
        }
        saveData(checkInfo, errorCode);
    }

    private CheckInfo setCheckInfo(Trade trade) {

        CheckInfo checkInfo = new CheckInfo();
        checkInfo.setTradeDate(trade.getTradeTime());
        checkInfo.setTradeType(trade.getType());
        BeanUtils.copyProperties(trade, checkInfo);
        return checkInfo;
    }

    private void saveData(CheckInfo checkInfo, String errorCode) {
        if (StringUtils.isEmpty(errorCode)) {
            checkInfo.setChkStatus(ReconcileStatusEnum.SUCCESS.getCode());
            dao.updateCheckInfo(checkInfo);
        } else {
            checkInfo.setChkStatus(ReconcileStatusEnum.FAIL.getCode());
            dao.insertCheckError(getErrPara(checkInfo, errorCode));
        }
        dao.insertCheckResult(getResultPara(checkInfo, errorCode));

    }

    private CheckResult getResultPara(CheckInfo checkInfo, String errorCode) {
        CheckResult result = new CheckResult();
        result.setChkDate(checkInfo.getTradeDate());
        result.setTradeNo(checkInfo.getTradeNo());
        result.setPartnerId(checkInfo.getPartnerId());
        result.setTradeDate(checkInfo.getTradeDate());
        result.setProductName(checkInfo.getProductName());
        result.setProductType(checkInfo.getProductType());
        result.setTradeType(checkInfo.getTradeType());
        result.setTradeAmount(checkInfo.getTradeAmount());
        result.setChkStatus(checkInfo.getChkStatus());
        result.setStlStatus(SettleStatusEnum.NO_ACTIVE.getCode());
        result.setErrDetail(errorCode);
        result.setCreateTime(new Date());
        result.setUpdateTime(new Date());

        return result;
    }

    private CheckError getErrPara(CheckInfo checkInfo, String errorCode) {
        CheckError error = new CheckError();
        error.setChkDate(checkInfo.getTradeDate());
        error.setTradeNo(checkInfo.getTradeNo());
        error.setPartnerId(checkInfo.getPartnerId());
        error.setTradeType(checkInfo.getTradeType());
        error.setDealStatus(AccountAdjustmentStatusEnum.NO_ACTIVE.getCode());
        error.setErrDetail(errorCode);
        error.setCreateTime(new Date());
        error.setUpdateTime(new Date());
        return error;
    }

    private String checkOrder(Trade trade, CheckInfo checkInfo) {
        if (!trade.getTradeTime().equals(checkInfo.getTradeDate())) {
            return ReconciledErrorEnum.TRADE_DATE_ERROR.getCode();
        }
        if (!trade.getProductCode().equals(checkInfo.getProductCode())) {
            return ReconciledErrorEnum.PRODUCT_CODE_ERROR.getCode();
        }
        if (!trade.getType().equals(checkInfo.getTradeType())) {
            return ReconciledErrorEnum.TRADE_TYPE_ERROR.getCode();
        }
        if (!trade.getTradeAmount().equals(checkInfo.getTradeAmount())) {
            return ReconciledErrorEnum.TRADE_AMOUNT_ERROR.getCode();
        }
        return ReconcileConstants.EMPTY;
    }
}