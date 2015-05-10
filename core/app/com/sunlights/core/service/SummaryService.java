package com.sunlights.core.service;

import models.CustBatchDetail;
import models.SyncIncomeStat;

import java.util.List;

/**
 * Created by edward.tian on 2015/5/6 0006.
 */
public interface SummaryService {

    public List<String> getBatchCount(String startDate);

    public List<String> getBatchCountAll();

    public List<String> getTradedCust(String batchNo);

    public boolean saveFundIncomes(List<SyncIncomeStat> list);
}
