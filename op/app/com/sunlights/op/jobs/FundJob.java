package com.sunlights.op.jobs;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import play.Logger;
import play.db.jpa.JPA;

import java.util.Date;

/**
 * Created by Yuan on 2014/9/3.
 */

public class FundJob implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JPA.withTransaction(new play.libs.F.Callback0() {
            public void invoke() {
                Logger.info("[begin FundJob execute] " + new Date());
                FundGrabService fundGrabService = new FundGrabService();
                fundGrabService.grabFund();
                Logger.info("[end FundJob execute] " + new Date());
            }
        });

    }
}
