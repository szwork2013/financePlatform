package com.sunlights.op.jobs;

import com.sunlights.crawler.service.FundArchiveService;
import com.sunlights.crawler.service.impl.FundArchiveServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import play.Logger;
import play.db.jpa.JPA;

import java.util.Date;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: FundCodeJob.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class FundArchiveexJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JPA.withTransaction(new play.libs.F.Callback0() {
            public void invoke() {
                Logger.info("[begin FundArchiveexJob execute] " + new Date());
                FundArchiveService fundArchiveService = new FundArchiveServiceImpl();
                fundArchiveService.saveFundArchive();
                Logger.info("[end FundArchiveexJob execute] " + new Date());
            }
        });
    }
}
