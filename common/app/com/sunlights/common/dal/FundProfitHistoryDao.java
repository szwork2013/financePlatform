package com.sunlights.common.dal;

import models.FundProfitHistory;

import java.util.List;

/**
 * Created by guxuelong on 2014/11/27.
 */
public interface FundProfitHistoryDao {
    void insertFundProfitHistory(FundProfitHistory fundProfitHistory);
    void updateFundProfitHistory(FundProfitHistory fundProfitHistory);
    void deleteFundProfitHistory(FundProfitHistory fundProfitHistory);
    FundProfitHistory findFundProfitHistoryByDateTime(FundProfitHistory fundProfitHistory);
    List<FundProfitHistory> findFundProfitHistory(FundProfitHistory fundProfitHistory);
}
