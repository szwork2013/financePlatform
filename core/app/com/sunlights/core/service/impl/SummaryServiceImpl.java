package com.sunlights.core.service.impl;

import com.sunlights.core.dal.SummaryDao;
import com.sunlights.core.dal.impl.SummaryDaoImpl;
import com.sunlights.core.service.SummaryService;
import models.SyncBatchLog;
import models.SyncIncomeStat;
import models.SyncTrade;

import java.util.List;

/**
 * Created by Edward.tian on 2015/5/7 0007.
 */
public class SummaryServiceImpl implements SummaryService {

    SummaryDao summaryDao = new SummaryDaoImpl();

    @Override
    public List<String> getBatchCount(String startDate) {
        return summaryDao.getBatchCount(startDate);
    }

    @Override
    public List<String> getBatchCountAll() {
        return summaryDao.getBatchCountAll();
    }

    @Override
    public List<String> getTradedCust(String batchNo) {
        return summaryDao.getTradedCust(batchNo);
    }

    @Override
    public boolean saveFundIncomes(List<SyncIncomeStat> list) {
        return summaryDao.saveFundIncomes(list);
    }

    @Override
    public boolean saveSyncTrade(List<SyncTrade> list) {
        return summaryDao.saveTradeRecords(list);
    }

    @Override
    public boolean saveBatchLog(SyncBatchLog batchLog) {
        return summaryDao.saveBatchLog(batchLog);
    }

    @Override
    public boolean isTaskFinished(String date, String taskName) {
        if(taskName==null||date==null){
            return false;
        }
        return summaryDao.isTaskFinished(taskName,date);
    }
}
