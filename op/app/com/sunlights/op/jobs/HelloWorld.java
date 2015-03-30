package com.sunlights.op.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by Yuan on 2014/8/31.
 */
// TODO: 这个类最后需要删掉
public class HelloWorld implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("HelloWorld.execute" + jobExecutionContext.getJobDetail().getKey() + " " +new Date());
    }
}
