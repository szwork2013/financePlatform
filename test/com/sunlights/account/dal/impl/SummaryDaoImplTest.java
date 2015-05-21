package com.sunlights.account.dal.impl;

import com.sunlights.core.dal.SummaryDao;
import com.sunlights.core.dal.impl.SummaryDaoImpl;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.test.WithApplication;

/**
 * Created by edward.tian on 2015/5/8 0008.
 */
public class SummaryDaoImplTest extends WithApplication {

    @Test
    public void testGetBatchCount() throws Exception {

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                SummaryDao summaryDao = new SummaryDaoImpl();
                Logger.info("total trade batch is"+summaryDao.getBatchCount("")+"");

            }
        });

    }

    @Test
    public void testGetTradedCustomer() throws Exception {

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                SummaryDao summaryDao = new SummaryDaoImpl();
                Logger.info("customers in 132980 are"+summaryDao.getTradedCust("132980")+"");

            }
        });

    }
}
