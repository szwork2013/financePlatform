package com.sunlights.op.jobs;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.op.common.DateDeserializer;
import com.sunlights.op.common.DateSerializer;
import org.apache.commons.beanutils.BeanUtils;
import org.quartz.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * Created by Yuan on 2014/8/31.
 */

public class JobVo {

	private static final long serialVersionUID = 710846763634438062L;

	private String jobName;
	private String isEdit;
	private String jobClass;
	private String jobParams;
	private Date jobStartTime;
	private Integer jobInterval;
	private String state;

	private String seconds;
	private String minutes;
	private String hours;
	private String dayOfMonth;
	private String month;
	private String dayOfWeek;
	private String year;

	// 0,停止状态，1，运行状态，2：未知
	private int status;

	private static final String SPACE = " ";

	public JobVo() {
		super();
	}

	public JobVo(String jobName, String jobClass, String jobParams, Date jobStartTime, Integer jobInterval) {
		this(jobName, jobClass, jobParams, jobStartTime, jobInterval, null);
	}

	// TODO: 参数过多，可以考虑使用builder模式
	public JobVo(String jobName, String jobClass, String jobParams, Date jobStartTime, Integer jobInterval, String state) {
		this.jobName = jobName;
		this.jobClass = jobClass;
		this.jobParams = jobParams;
		this.jobStartTime = jobStartTime;
		this.jobInterval = jobInterval;
		this.state = state;
	}

	public JobDetail buildJobDetail() throws ClassNotFoundException {
		Class classObject = Class.forName(jobClass);
		JobDataMap jobDataMap = new JobDataMap();

		Map<String, String> map = new HashMap<String, String>();
		try {
			map = BeanUtils.describe(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (String key : map.keySet()) {
			jobDataMap.put(key, map.get(key));
		}

		JobDetail jd = JobBuilder.newJob(classObject).withIdentity(jobName).storeDurably().usingJobData(jobDataMap).build();
		return jd;
	}

	public Trigger buildJobTrigger() throws NumberFormatException, ParseException {
		TriggerBuilder<Trigger> trigger = TriggerBuilder.newTrigger().withIdentity(jobName + "_trigger").startAt(jobStartTime);

		if (jobInterval > 0) {
			trigger.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(jobInterval).repeatForever());
		}

		return trigger.build();

	}

	public Trigger buildJobCronTrigger() throws NumberFormatException, ParseException {
		Date date = CommonUtil.stringToDate(CommonUtil.dateToString(jobStartTime));
		String cronExpression = seconds + SPACE + minutes + SPACE + hours + SPACE + dayOfMonth + SPACE + month + SPACE + "?";
		TriggerBuilder<Trigger> trigger = TriggerBuilder.newTrigger().withIdentity(jobName + "_trigger").startAt(date);
		trigger.withSchedule(cronSchedule(cronExpression));

		return trigger.build();

	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public String getJobParams() {
		return jobParams;
	}

	public void setJobParams(String jobParams) {
		this.jobParams = jobParams;
	}


	@JsonSerialize(using = DateSerializer.class)
	public Date getJobStartTime() {
		return jobStartTime;
	}

	@JsonDeserialize(using = DateDeserializer.class)
	public void setJobStartTime(Date jobStartTime) {
		this.jobStartTime = jobStartTime;
	}

	public Integer getJobInterval() {
		return jobInterval;
	}

	public void setJobInterval(Integer jobInterval) {
		this.jobInterval = jobInterval;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSeconds() {
		return seconds;
	}

	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getStatus () {
		return status;
	}

	public void setStatus (int status) {
		this.status = status;
	}
}
