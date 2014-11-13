package com.sunlights.common.jobs.utils;

import com.sunlights.common.jobs.JobVo;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SchedulerUtils {
    private static Scheduler instance;

    private static void initializeScheduler() {
        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            instance = sf.getScheduler();
            instance.start();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public static List<JobVo> getAlljob() throws SchedulerException {
        if (instance == null)
            initializeScheduler();
        List<JobVo> retVal = new ArrayList<JobVo>();
        List<String> groups = instance.getJobGroupNames();
        Set<String> running = new HashSet<String>();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        for (JobExecutionContext jec : instance.getCurrentlyExecutingJobs()) {
            running.add(jec.getJobDetail().getKey().getName());
        }
        for (String groupName : groups) {
            for (JobKey jobKey : instance.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                JobDetail jd = instance.getJobDetail(jobKey);
                TriggerKey tk = new TriggerKey(jobKey.getName() + "_trigger");
                Trigger trigger = instance.getTrigger(tk);
                String paramStr = "";
                JobDataMap jdm = jd.getJobDataMap();
                for (String key : jdm.getKeys()) {
                    paramStr += key + "=" + jdm.getString(key) + "|";
                }
                if (paramStr.length() > 1) {
                    paramStr = paramStr.substring(0, paramStr.length() - 1);
                }
                Trigger.TriggerState state = instance.getTriggerState(tk);
                retVal.add(new JobVo(
                        jobKey.getName(),
                        jd.getJobClass().getCanonicalName(),
                        paramStr,
                        df.format(trigger.getNextFireTime()),
                        "",
                        running.contains(jobKey.getName()) ?
                                "running" :
                                (tk == null ? "normal" : state.toString().toLowerCase())));
            }

        }
        return retVal;
    }

    //TODO: throw的异常过多，应该在里面使用BusinessException包装一下
    public static void scheduleJob(JobVo jobVo) throws ClassNotFoundException, NumberFormatException, ParseException, SchedulerException {
        if (instance == null)
            initializeScheduler();

        JobDetail jd = jobVo.buildJobDetail();
        Trigger trigger = jobVo.buildJobTrigger();

        instance.scheduleJob(jd, trigger);
    }

    public static void deleteJob(String name) throws SchedulerException {
        if (instance == null)
            initializeScheduler();
        instance.deleteJob(new JobKey(name));
    }

    public static void pauseJob(String name) throws SchedulerException {
        if (instance == null)
            initializeScheduler();
        instance.pauseJob(new JobKey(name));
    }

    public static void resumeJob(String name) throws SchedulerException {
        if (instance == null)
            initializeScheduler();
        instance.resumeJob(new JobKey(name));
    }

    //TODO: throw的异常过多，应该在里面使用BusinessException包装一下
    public static void updateJob(JobVo jobVo) throws ClassNotFoundException, NumberFormatException, ParseException, SchedulerException {
        deleteJob(jobVo.jobName);
        scheduleJob(jobVo);
    }

    public static JobVo findJobByName(String name) throws SchedulerException {
        if (name == null || "".equals(name.trim())) return null;
        for (JobVo jobVo : getAlljob()) {
            if (name.equals(jobVo.jobName)) {
                return jobVo;
            }
        }
        return null;
    }
}
