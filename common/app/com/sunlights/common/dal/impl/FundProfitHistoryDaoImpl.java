package com.sunlights.common.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.FundProfitHistoryDao;
import com.sunlights.common.utils.CommonUtil;
import models.FundProfitHistory;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by guxuelong on 2014/11/27.
 */
public class FundProfitHistoryDaoImpl  extends EntityBaseDao implements FundProfitHistoryDao {
    private static final String QUERY_FUND_PROFIT_HISTORY = "select t from FundProfitHistory t where t.fundCode =: fundCode and t.dateTime >= :dateTimeStart  and t.dateTime <= :dateTimeEnd";
    private static final String START_TIME = " 00:00:00";
    private static final String END_TIME = " 23:59:59";

    @Override
    public void insertFundProfitHistory(FundProfitHistory fundProfitHistory) {
        create(fundProfitHistory);
    }

    @Override
    public void updateFundProfitHistory(FundProfitHistory fundProfitHistory) {
        update(fundProfitHistory);
    }

    @Override
    public void deleteFundProfitHistory(FundProfitHistory fundProfitHistory) {
        delete(fundProfitHistory);
    }

    @Override
    public FundProfitHistory findFundProfitHistoryByDateTime(Timestamp dateTime) {
        List<FundProfitHistory> result = super.findBy(FundProfitHistory.class, "dateTime", dateTime);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<FundProfitHistory> findFundProfitHistory(FundProfitHistory fundProfitHistory) {
        return findAll(FundProfitHistory.class);
    }

    @Override
    public FundProfitHistory findFundProfitHistoryByCodeAndDate(FundProfitHistory fundProfitHistories) {
        Query query = super.createQuery(QUERY_FUND_PROFIT_HISTORY);
        query.setParameter("fundCode", fundProfitHistories.getFundCode());
        setTradeTimePara(CommonUtil.dateToString(fundProfitHistories.getDateTime()),query);
        if (query.getResultList() == null || query.getResultList().size() == 0) {
            return null;
        }
        return (FundProfitHistory) query.getResultList().get(0);
    }

    private void setTradeTimePara(String dateTime, Query query) {
        query.setParameter("dateTimeStart", getDate(dateTime, START_TIME));
        query.setParameter("dateTimeEnd", getDate(dateTime, END_TIME));
    }

    private Date getDate(String dateTime, String time) {
        try {
            return CommonUtil.stringToDate(dateTime + time, CommonUtil.DATE_FORMAT_LONG);
        } catch (ParseException e) {
            return null;
        }
    }
}
