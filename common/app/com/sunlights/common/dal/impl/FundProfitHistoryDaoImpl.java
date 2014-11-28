package com.sunlights.common.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.FundProfitHistoryDao;
import models.FundProfitHistory;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by guxuelong on 2014/11/27.
 */
public class FundProfitHistoryDaoImpl  extends EntityBaseDao implements FundProfitHistoryDao {
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
}
