package com.sunlights.core.dal;

import models.CustBatchDetail;
import models.SyncIncomeStat;

import java.util.List;

/**
 * Created by Administrator on 2015/5/7 0007.
 */
public interface SummaryDao {

    public List<String>  getBatchCount(String startDate,boolean isAll);

    public List<String> getTradedCust(String startDate, String batchNo);

    public boolean saveFundIncomes(List<SyncIncomeStat> list);
}
