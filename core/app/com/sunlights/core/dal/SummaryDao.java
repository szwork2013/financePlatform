package com.sunlights.core.dal;

import models.CustBatchDetail;
import models.SyncIncomeStat;

import java.util.List;

/**
 * Created by Administrator on 2015/5/7 0007.
 */
public interface SummaryDao {

    public List<String>  getBatchCount(String startDate);

    public List<String> getBatchCountAll();

    public List<String> getTradedCust(String batchNo);

    public boolean saveFundIncomes(List<SyncIncomeStat> list);
}
