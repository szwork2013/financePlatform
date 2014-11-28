package com.sunlights.common.dal;

import models.FundProfitHistory;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by guxuelong on 2014/11/27.
 */
public interface FundProfitHistoryDao {
    void insertFundProfitHistory(FundProfitHistory fundProfitHistory);
    void updateFundProfitHistory(FundProfitHistory fundProfitHistory);
    void deleteFundProfitHistory(FundProfitHistory fundProfitHistory);
    FundProfitHistory findFundProfitHistoryByDateTime(Timestamp dateTime);
    List<FundProfitHistory> findFundProfitHistory(FundProfitHistory fundProfitHistory);
}
