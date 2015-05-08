package com.sunlights.account.dal.impl;

import com.sunlights.core.dal.SummaryDao;
import com.sunlights.core.dal.impl.SummaryDaoImpl;
import org.junit.Test;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;
import play.test.WithApplication;

/**
 * Created by Administrator on 2015/5/8 0008.
 */
public class SummaryDaoImplTest extends WithApplication {

    @Test
    public void testGetBatchCount() throws Exception {

        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                SummaryDao summaryDao = new SummaryDaoImpl();
                Logger.info(summaryDao.getBatchCount("")+"");

            }
        });

    }
}
