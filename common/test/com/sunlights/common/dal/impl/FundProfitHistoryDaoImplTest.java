package com.sunlights.common.dal.impl;

import com.sunlights.common.dal.FundProfitHistoryDao;
import models.FundProfitHistory;
import org.junit.Test;
import play.db.jpa.JPA;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

public class FundProfitHistoryDaoImplTest {

    @Test
    public void testInsertFundProfitHistory() throws Exception {

        running(fakeApplication(inMemoryDatabase("test")), new Runnable() {

            public void run() {

                JPA.withTransaction(new play.libs.F.Callback0() {
                    public void invoke() {
                        FundProfitHistoryDao dao = new FundProfitHistoryDaoImpl();
                        FundProfitHistory data = new FundProfitHistory();
                        data.setFundCode("519501");
                        System.out.print(data.getFundCode());
                        Timestamp ts = new Timestamp(20130816);
                        data.setDateTime(ts);
                        data.setIncomePerTenThousand(new BigDecimal(1.22));
                        data.setPercentSevenDays(new BigDecimal(0.044600000000000001));
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