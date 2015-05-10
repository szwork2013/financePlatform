package com.sunlights.core.service.impl;

import com.sunlights.core.dal.SummaryDao;
import com.sunlights.core.dal.impl.SummaryDaoImpl;
import com.sunlights.core.service.SummaryService;
import models.SyncIncomeStat;

import java.util.List;

/**
 * Created by Edward.tian on 2015/5/7 0007.
 */
public class SummaryServiceImpl implements SummaryService {

    SummaryDao summaryDao = new SummaryDaoImpl();

    @Override
    public List<String> getBatchCount(String startDate) {
        return summaryDao.getBatchCount("");
    }

    @Override
    public List<Integer> getAllTradedCust(int batchNo) {
        return null;
    }

    @Override
    public List<String> getTradedCust(String startDate, String batchNo) {
        return summaryDao.getTradedCust(startDate,batchNo);
    }

    @Override
    public boolean saveFundIncomes(List<SyncIncomeStat> list) {
        return summaryDao.saveFundIncomes(list);
    }
}
