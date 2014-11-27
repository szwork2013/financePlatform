package com.sunlights.common.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.FundProfitHistoryDao;
import models.FundProfitHistory;

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
    public FundProfitHistory findFundProfitHistoryByDateTime(FundProfitHistory fundProfitHistory) {
        return null;
    }

    @Override
    public List<FundProfitHistory> findFundProfitHistory(FundProfitHistory fundProfitHistory) {
        return findAll(FundProfitHistory.class);
    }
}
