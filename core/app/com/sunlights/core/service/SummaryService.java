package com.sunlights.core.service;

import models.CustBatchDetail;
import models.SyncBatchLog;
import models.SyncIncomeStat;
import models.SyncTrade;

import java.util.List;

/**
 * Created by edward.tian on 2015/5/6 0006.
 */
public interface SummaryService {

    public List<String> getBatchCount(String startDate);

    public List<String> getBatchCountAll();

    public List<CustBatchDetail> getTradedCust(String batchNo);

    public boolean saveFundIncomes(List<SyncIncomeStat> list);

    public boolean saveSyncTrade(List<SyncTrade> list);

    public boolean saveBatchLog(SyncBatchLog batchLog);

    public boolean isTaskFinished(String taskName, String date);
}
