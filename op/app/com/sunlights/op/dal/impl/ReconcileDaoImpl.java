package com.sunlights.op.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.op.common.enums.ReconcileStatusEnum;
import com.sunlights.op.dal.ReconcileDao;
import com.sunlights.op.vo.ReconcileVo;
import models.CheckError;
import models.CheckInfo;
import models.CheckResult;
import models.Trade;
import org.springframework.beans.BeanUtils;
import play.Logger;

import javax.persistence.Query;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by guxuelong on 2014/12/2.
 */
public class ReconcileDaoImpl extends EntityBaseDao implements ReconcileDao {
    private static final String QUERY_ORDER_INFO = "select t from Trade t where t.tradeNo=:tradeNo";
    private static final String QUERY_ORDER_LIST = "select t from Trade t where t.tradeTime >= :tradeTimeStart  and t.tradeTime <= :tradeTimeEnd";
    private static final String QUERY_CHK_INFO_BY_CHK_STATUS = "select ci from CheckInfo ci where ci.tradeDate >= :tradeTimeStart  and ci.tradeDate <= :tradeTimeEnd and ci.chkStatus = :chkStatus";
    private static final String QUERY_CHK_INFO_BY_TRADE_NO = "select ci from CheckInfo ci where ci.tradeNo = :tradeNo";
    private static final String QUERY_CHK_RESULT_LIST = "select t from CheckResult t where t.chkDate >= :tradeTimeStart  and t.chkDate <= :tradeTimeEnd order by tradeNo asc";
    public static final String START_TIME = " 00:00:00";
    public static final String END_TIME = " 23:59:59";

    /**
     * 根据交易流水号查询单笔交易流水
     *
     * @param tradeNo
     * @return
     */
    @Override
    public Trade queryOrderInfoByTradeNo(String tradeNo) {
        Query query = super.createQuery(QUERY_ORDER_INFO);
        query.setParameter("tradeNo", tradeNo);
        if (query.getResultList() == null || query.getResultList().size() == 0) {
            return null;
        }
        return (Trade) query.getResultList().get(0);
    }

    /**
     * 根据日期查询交易流水
     *
     * @param tradeTime（yyyy-MM-dd）
     * @return
     */
    @Override
    public List<Trade> queryOrderListByTradeTime(String tradeTime) {
        Query query = super.createQuery(QUERY_ORDER_LIST);
        setTradeTimePara(tradeTime, query);
        return query.getResultList();
    }

    /**
     * 根据[日期、对账状态] 查询数米交易流水
     *
     * @param tradeTime
     * @param chkStatus
     * @return
     */
    @Override
    public List<CheckInfo> queryCheckInfoByTradeTimeAndChkStatus(String tradeTime, String chkStatus) {
        Query query = super.createQuery(QUERY_CHK_INFO_BY_CHK_STATUS);
        setTradeTimePara(tradeTime, query);
        query.setParameter("chkStatus", chkStatus);
        return query.getResultList();
    }

    /**
     * 根据交易号查询数米交易流水记录
     *
     * @param tradeNo
     * @return
     */
    @Override
    public CheckInfo queryCheckInfoByTradeNo(String tradeNo) {
        Query query = super.createQuery(QUERY_CHK_INFO_BY_TRADE_NO);
        query.setParameter("tradeNo", tradeNo);
        if (query.getResultList() == null || query.getResultList().size() == 0) {
            return null;
        }
        return (CheckInfo) query.getResultList().get(0);
    }

    /**
     * 根据交易时间查询对账结果
     *
     * @param pageVo
     * @return
     * @throws Exception
     */
    @Override
    public ReconcileVo queryCheckResultByTradeTime(ReconcileVo pageVo) throws Exception {
        Query query = super.createQuery(QUERY_CHK_RESULT_LIST);
        List<CheckResult> totalList = getTotalResults(pageVo, query);
        List<CheckResult> list = getDispResults(pageVo, query);
        ReconcileVo vo = setReturnVo(pageVo, totalList.size(), list, getErrCount(totalList));
        return vo;
    }

    /**
     * 根据交易时间查询对账差错统计记录
     *
     * @param tradeTime
     * @return
     */
    @Override
    public List<CheckError> queryCheckErrorByTradeTime(String tradeTime) {
        return null;
    }

    /**
     * 合作方对账信息新增
     *
     * @param checkInfo
     */
    @Override
    public void insertCheckInfo(CheckInfo checkInfo) {
        super.create(checkInfo);
    }

    /**
     * 合作方对账信息更新
     *
     * @param checkInfo
     */
    @Override
    public void updateCheckInfo(CheckInfo checkInfo) {
        super.update(checkInfo);
    }

    /**
     * 对账结果新增
     *
     * @param checkResult
     */
    @Override
    public void insertCheckResult(CheckResult checkResult) {
        super.create(checkResult);
    }

    /**
     * 对账结果更新
     *
     * @param checkResult
     */
    @Override
    public void updateCheckResult(CheckResult checkResult) {
        super.update(checkResult);
    }

    /**
     * 差错统计新增
     *
     * @param checkError
     */
    @Override
    public void insertCheckError(CheckError checkError) {
        super.create(checkError);
        Logger.debug("对账差错统计表插入数据成功");
    }

    /**
     * 差错统计更新
     *
     * @param checkError
     */
    @Override
    public void updateCheckError(CheckError checkError) {
        super.update(checkError);
    }

    private List<CheckResult> getDispResults(ReconcileVo pageVo, Query query) {
        setPagePara(pageVo, query);
        return query.getResultList();
    }

    private List<CheckResult> getTotalResults(ReconcileVo pageVo, Query query) throws Exception {
        setTradeTimePara(getChkDateString(pageVo), query);
        return query.getResultList();
    }

    private void setPagePara(ReconcileVo pageVo, Query query) {
        query.setFirstResult((pageVo.getPageNum() - 1) * pageVo.getPageSize());
        query.setMaxResults(pageVo.getPageSize());
    }

    private int getErrCount(List<CheckResult> totalList) {
        int errorCount = 0;
        for (CheckResult result : totalList) {
            if (ReconcileStatusEnum.FAIL.getCode().equals(result.getChkStatus())) {
                errorCount++;
            }
        }
        return errorCount;
    }

    private ReconcileVo setReturnVo(ReconcileVo pageVo, int size, List<CheckResult> rtnList, int errorCount) {
        ReconcileVo vo = new ReconcileVo();
        BeanUtils.copyProperties(pageVo, vo);
        vo.setList(rtnList);
        vo.setTotalCount(rtnList.size());
        vo.setErrorCount(errorCount);
        vo.setCount(size);
        return vo;
    }

    private String getChkDateString(ReconcileVo pageVo) throws Exception {
        String chkDate = ((String) pageVo.getFilter().get("checkDate")).substring(0, 10);
        Date date = CommonUtil.stringToDate(chkDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        chkDate = CommonUtil.dateToString(cal.getTime());
        return chkDate;
    }

    private void setTradeTimePara(String tradeTime, Query query) {
        query.setParameter("tradeTimeStart", getDate(tradeTime, START_TIME));
        query.setParameter("tradeTimeEnd", getDate(tradeTime, END_TIME));
    }

    private Date getDate(String tradeTime, String time) {
        try {
            return CommonUtil.stringToDate(tradeTime + time, CommonUtil.DATE_FORMAT_LONG);
        } catch (ParseException e) {
            return null;
        }
    }
}
