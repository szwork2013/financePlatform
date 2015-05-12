package com.sunlights.core.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.dal.SummaryDao;
import models.*;
import play.Logger;

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
    public List<String> getBatchCount(String startDate) {
        StringBuffer sql = new StringBuffer();
        sql.append("select distinct t.cust_id from t_trade t,c_customer c where t.cust_id = c.customer_id");
        sql.append(" and t.trade_time >'");
        sql.append(startDate);
        sql.append("'  and  t.trade_time<'");
        String nextDay = addDay(startDate, 1);
        sql.append(nextDay);
        sql.append("'");
        return caculateBatch(sql.toString());
    }

    @Override
    public List<String> getBatchCountAll() {
        StringBuffer sql = new StringBuffer();
        sql.append("select distinct t.cust_id from t_trade t,c_customer c where t.cust_id = c.customer_id");
        return caculateBatch(sql.toString());
    }

    @Override
    public List<String> getTradedCust(String batchNo) {
        String sql = "select t.customer_id from t_cust_batch_detail t where t.customer_batch_id= ?0 ";
        Query query = em.createNativeQuery(sql);
        query.setParameter(0, new Long(batchNo));
        return query.getResultList();
    }

    @Override
    public boolean saveFundIncomes(List<SyncIncomeStat> list) {
        if (list == null || list.size() == 0) {
            Logger.info("The SyncIncomeStat list should not be empty or null");
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

    @Override
    public boolean saveTradeRecords(List<SyncTrade> list) {
        if (list == null || list.size() == 0) {
            Logger.info("The SyncIncomeStat list should not be empty or null");
            return false;
        }
        try {
            for (SyncTrade trade : list) {
                create(trade);
            }
        } catch (Exception ex) {
            Logger.info("Exception when saving the SyncIncomeStat data" + ex.getStackTrace());
            return false;
        }
        return true;
    }

    @Override
    public boolean saveBatchLog(SyncBatchLog batchLog) {
        if(batchLog==null) {
            return false;
        }
        try {
            create(batchLog);
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isTaskFinished(String date,String taskName) {
        if(taskName==null||date==null){
            return false;
        }
        StringBuilder sql = new StringBuilder();
        sql.append("select id from t_sync_batch_log t where t.task_status=0 and t.create_time='");
        sql.append(date);
        sql.append("' and task_name='");
        sql.append(taskName);
        sql.append("'");
        Query query = em.createNativeQuery(sql.toString());
        if(query.getMaxResults()>0){
            return true;
        }
        return false;
    }


    private List<String> caculateBatch(String sql) {
        List<String> results = new ArrayList<String>();
        Query query = em.createNativeQuery(sql.toString());
        List<String> list = query.getResultList();
        int mod = 10;
        int batchTotal = list.size() / mod;
        int batchLeft = list.size() % mod;
        if (batchLeft != 0) {
            batchTotal++;
        }
        for (int i = 0; i < batchTotal; i++) {
            CustomerBatch customerBatch = new CustomerBatch();
            customerBatch.setCustomerTotal(mod);
            customerBatch.setCreateTime(new Date());
            create(customerBatch);
            results.add(customerBatch.getId().toString());
            for (int k = 0; k < mod; k++) {
                CustBatchDetail batchDetail = new CustBatchDetail();
                batchDetail.setCustomerBatchId(customerBatch.getId());
                batchDetail.setCustomerId(list.get(k * i));
                create(batchDetail);
            }
        }
        return results;
    }

    private String addDay(String s, int n) {
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