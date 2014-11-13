package com.sunlights.common.jobs;


import com.sunlights.common.jobs.data.DataService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * J
 * Created by Yuan on 2014/9/3.
 */
public class FundJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        DataService.saveFunds();
    }
}
