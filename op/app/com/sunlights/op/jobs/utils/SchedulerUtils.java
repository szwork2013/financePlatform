package com.sunlights.op.jobs.utils;

import com.sunlights.op.jobs.JobVo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import play.Configuration;

import java.text.ParseException;
import java.util.*;
import java.util.logging.Logger;

public class SchedulerUtils {
	private static Logger logger = Logger.getLogger(SchedulerUtils.class.getName());
	private static Scheduler instance;
	private static String QUARTZ_CONFIG_FILE = "quartz.config.file";

	private static void initializeScheduler() {
		try {
			String config = Configuration.root().getString(QUARTZ_CONFIG_FILE);
			logger.info("[quartz config name ] " + config);
			SchedulerFactory sf = new StdSchedulerFactory(config);
			instance = sf.getScheduler();
			instance.start();
		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}

	public static List<JobVo> getAlljob() throws SchedulerException {
		if (instance == null)
			initializeScheduler();
		List<JobVo> jobVos = new ArrayList<JobVo>();
		Set<String> running = new HashSet<String>();
		for (JobExecutionContext jec : instance.getCurrentlyExecutingJobs()) {
			running.add(jec.getJobDetail().getKey().getName());
		}
		List<String> groups = instance.getJobGroupNames();
		for (String groupName : groups) {
			for (JobKey jobKey : instance.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
				try {

					JobDetail jd = instance.getJobDetail(jobKey);
					TriggerKey tk = new TriggerKey(jobKey.getName() + "_trigger");
					Trigger trigger = instance.getTrigger(tk);
					JobVo jobVo = new JobVo();
					JobDataMap jdm = jd.getJobDataMap();
					for (String key : jdm.getKeys()) {
						String value = jdm.getString(key);
						if (StringUtils.isNotEmpty(value)) {
							DateConverter dateConverter = new DateConverter(null);
							dateConverter.setPatterns(new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH/mm/ss" });
							ConvertUtils.register(dateConverter, Date.class);
							BeanUtils.setProperty(jobVo, key, value);

						}
					}

					Trigger.TriggerState state = instance.getTriggerState(tk);
					jobVo.setJobName(jobKey.getName());
					jobVo.setJobClass(jd.getJobClass().getCanonicalName());
					jobVo.setJobStartTime(trigger.getNextFireTime());
					jobVo.setState(running.contains(jobKey.getName()) ? "running" : (tk == null ? "normal" : state.toString().toLowerCase()));
					jobVos.add(jobVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		return jobVos;
	}

	// TODO: throw的异常过多，应该在里面使用BusinessException包装一下
	public static void scheduleJob(JobVo jobVo) throws ClassNotFoundException, NumberFormatException, ParseException, SchedulerException {
		if (instance == null)
			initializeScheduler();

		JobDetail jd = jobVo.buildJobDetail();
		// Trigger trigger = jobVo.buildJobTrigger();
		Trigger trigger = jobVo.buildJobCronTrigger();

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

	// TODO: throw的异常过多，应该在里面使用BusinessException包装一下
	public static void updateJob(JobVo jobVo) throws ClassNotFoundException, NumberFormatException, ParseException, SchedulerException {
		deleteJob(jobVo.getJobName());
		scheduleJob(jobVo);
	}

	public static JobVo findJobByName(String name) throws SchedulerException {
		if (name == null || "".equals(name.trim()))
			return null;
		for (JobVo jobVo : getAlljob()) {
			if (name.equals(jobVo.getJobName())) {
				return jobVo;
			}
		}
		return null;
	}

	public static JobVo saveJob(JobVo jobVo) throws SchedulerException, ParseException, ClassNotFoundException {
		JobVo job = findJobByName(jobVo.getJobName());
		if (job == null) {
			scheduleJob(jobVo);
		} else {
			updateJob(jobVo);
		}
		return job;
	}
}
