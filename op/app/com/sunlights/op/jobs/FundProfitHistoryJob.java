package com.sunlights.op.jobs;

import com.sunlights.crawler.service.FundProfitHistoryService;
import com.sunlights.crawler.service.impl.FundProfitHistoryServiceImpl;
import com.sunlights.op.service.ProductManageService;
import com.sunlights.op.service.impl.ProductManageServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import play.Logger;
import play.db.jpa.JPA;

import java.util.Date;
import java.util.List;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: FundCodeJob.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:xuelong.gu@sunlights.cc">guxuelong</a>
 */
public class FundProfitHistoryJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JPA.withTransaction(new play.libs.F.Callback0() {
            public void invoke() {
                Logger.info("[begin FundProfitHistoryJob execute] " + new Date());
                ProductManageService productManageService = new ProductManageServiceImpl();
                List<String> productCodes = productManageService.findSynchronousProductCodes();
                FundProfitHistoryService service = new FundProfitHistoryServiceImpl();
                service.saveTodayFundProfitHistory(productCodes);
                Logger.info("[end FundProfitHistoryJob execute] " + new Date());
            }
        });
    }
}
