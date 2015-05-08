package com.sunlights.core.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.dal.SummaryDao;
import models.CustBatchDetail;
import models.CustomerBatch;
import models.SyncIncomeStat;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Edward.tian on 2015/5/8 0008.
 */
public class SummaryDaoImpl extends EntityBaseDao implements SummaryDao {

    @Override
    public List<String> getBatchCount(String startDate) {
        List<String> results = new ArrayList<String>();
        String sql = "select t.cust_id from t_trade t,c_customer c where t.cust_id = c.customer_id";
        Query query = em.createNativeQuery(sql);
        List<String> list = query.getResultList();
        int mod = 10;
        int batchTotal = list.size()/mod;
        int batchLeft = list.size()%mod;
        if(batchLeft!=0){
            batchTotal++;
        }
        for(int i=1;i<=batchTotal;i++){
            CustomerBatch custBatch = new CustomerBatch();
            custBatch.setCustomerTotal(mod);
            custBatch.setCreateTime(new Date());
            create(custBatch);
            results.add(custBatch.getId().toString());
            for(int k=0;k<mod;k++) {
                CustBatchDetail batchDetail = new CustBatchDetail();
                batchDetail.setCustomerBatchId(custBatch.getId());
                batchDetail.setCustomerId(list.get(k*i));
                create(batchDetail);
            }
        }
        return results;
    }

    @Override
    public List<Integer> getAllTradedCust(int batchNo) {
        return null;
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
        return false;
    }
}
