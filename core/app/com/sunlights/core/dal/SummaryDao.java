package com.sunlights.core.dal;

import models.CustBatchDetail;
import models.SyncBatchLog;
import models.SyncIncomeStat;
import models.SyncTrade;

import java.util.List;

/**
 * Created by Administrator on 2015/5/7 0007.
 */
public interface SummaryDao {

    public List<String>  getBatchCount(String startDate);

    public List<String> getBatchCountAll();

    public List<CustBatchDetail> getTradedCust(String batchNo);

    public boolean saveFundIncomes(List<SyncIncomeStat> list);

    public boolean saveTradeRecords(List<SyncTrade> list);

    public boolean saveBatchLog(SyncBatchLog batchLog);

    public boolean isTaskFinished(String taskName,String date);
}
