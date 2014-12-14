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
    FundProfitHistory findFundProfitHistoryByCodeAndDate(FundProfitHistory fundProfitHistories);

    /**
     * 根据更新日期查询基金净值更新记录
     *
     * @param updateDate("yyyy-MM-dd")
     * @return
     */
    List<FundProfitHistory> findByUpdateDate(String updateDate);
}
