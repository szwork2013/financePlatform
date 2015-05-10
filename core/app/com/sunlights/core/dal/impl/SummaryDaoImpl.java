package com.sunlights.core.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.dal.SummaryDao;
import models.CustBatchDetail;
import models.CustomerBatch;
import models.SyncIncomeStat;
import play.Logger;
import play.libs.Json;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Edward.tian on 2015/5/8 0008.
 */
public class SummaryDaoImpl extends EntityBaseDao implements SummaryDao {

    @Override
    public List<String> getBatchCount(String startDate,boolean isAll) {
        List<String> results = new ArrayList<String>();
        String nextDay = addDay(startDate,1);
        String sql = "select t.cust_id from t_trade t,c_customer c where t.cust_id = c.customer_id and t.trade_time > ?0 and  t.trade_time< ?1";
        Query query = em.createNativeQuery(sql);
        query.setParameter(0,startDate);
        query.setParameter(1,nextDay);
        List<String> list = query.getResultList();
        int mod = 10;
        int batchTotal = list.size() / mod;
        int batchLeft = list.size() % mod;
        if (batchLeft != 0) {
            batchTotal++;
        }
        for (int i = 1; i <= batchTotal; i++) {
            CustomerBatch custBatch = new CustomerBatch();
            custBatch.setCustomerTotal(mod);
            custBatch.setCreateTime(new Date());
            create(custBatch);
            results.add(custBatch.getId().toString());
            for (int k = 0; k < mod; k++) {
                CustBatchDetail batchDetail = new CustBatchDetail();
                batchDetail.setCustomerBatchId(custBatch.getId());
                batchDetail.setCustomerId(list.get(k * i));
                create(batchDetail);
            }
        }
        return results;
    }

    @Override
    public List<String> getTradedCust(String startDate, String batchNo) {
        String sql = "select t.customer_id from t_cust_batch_detail t where t.customer_batch_id= ?0 ";
        Query query = em.createNativeQuery(sql);
        query.setParameter(0, new Long(batchNo));
        return query.getResultList();
    }

    @Override
    public boolean saveFundIncomes(List<SyncIncomeStat> list) {
        if (list == null || list.size() == 0) {
            Logger.info("The SyncIncomeStat should not be empty or null");
            return false;
        }
        try {
            for (SyncIncomeStat income : list) {
                create(income);
            }
        } catch (Exception ex) {
            Logger.info("Exception when saving the SyncIncomeStat data" + ex.getStackTrace());
            return false;
        }
        return true;
    }

    public static String addDay(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(Calendar.DATE, n);//增加一天
            return sdf.format(cd.getTime());
        } catch (Exception e) {
            return null;
        }
    }

}