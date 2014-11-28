package com.sunlights.common.dal.impl;

import com.sunlights.common.dal.FundProfitHistoryDao;
import models.FundProfitHistory;
import org.junit.Test;
import play.db.jpa.JPA;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static play.test.Helpers.*;

public class FundProfitHistoryDaoImplTest {

    @Test
    public void testInsertFundProfitHistory() throws Exception {

        running(fakeApplication(), new Runnable() {

            public void run() {
                JPA.withTransaction(new play.libs.F.Callback0() {
                    public void invoke() {
                        FundProfitHistoryDao dao = new FundProfitHistoryDaoImpl();
                        FundProfitHistory data = new FundProfitHistory();
                        data.setFundcode("000009");
                        Timestamp ts = new Timestamp(20130308);
                        data.setDateTime(ts);
                        data.setIncomePerTenThousand(new BigDecimal(0.95999999999999996));
                        data.setPercentSevenDays(new BigDecimal(0.036400000000000002));
                        dao.insertFundProfitHistory(data);
                    }
                });
            }
        });


    }

    @Test
    public void testUpdateFundProfitHistory() throws Exception {

    }

    @Test
    public void testDeleteFundProfitHistory() throws Exception {

    }

    @Test
    public void testFindFundProfitHistoryByDateTime() throws Exception {

    }

    @Test
    public void testFindFundProfitHistory() throws Exception {

    }
}