package com.sunlights.op.jobs;

import com.sunlights.op.service.FundEquityMonitorService;
import com.sunlights.op.task.BigTaskService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import play.Logger;

import java.util.Date;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: FundCodeJob.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:xuelong.gu@sunlights.cc">guxuelong</a>
 */
public class FundEquityMonitorJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Logger.info("[begin FundEquityMonitorJob execute] " + new Date());
        BigTaskService service = new BigTaskService(FundEquityMonitorService.class);
        try {
            service.doTask();
        } catch (Exception e) {
            Logger.error("FundEquityMonitorJob execute error");
        }
        Logger.info("[end FundEquityMonitorJob execute] " + new Date());
    }
}
