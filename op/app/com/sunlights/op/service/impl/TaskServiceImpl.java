package com.sunlights.op.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.jobs.JobVo;
import com.sunlights.op.jobs.utils.SchedulerUtils;
import com.sunlights.op.service.TaskService;
import org.quartz.SchedulerException;
import play.Logger;

import java.text.ParseException;
import java.util.List;

/**
 * <p>Project: op</p>
 * <p>Title: TaskService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class TaskServiceImpl implements TaskService {

	@Override
	public List<JobVo> findTasksBy (PageVo pageVo) {
		List<JobVo> jobVos = null;
		try {
			jobVos = SchedulerUtils.getAlljob();
		} catch (SchedulerException e) {
			Logger.info(e.getMessage(), e);
		}
		return jobVos;
	}

	@Override
	public void create(JobVo jobVo) {
        try {
            SchedulerUtils.scheduleJob(jobVo);
        } catch (ClassNotFoundException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_CLASS_NOT_FOUND_ERROR));
        } catch (ParseException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_DATE_PARSING_ERROR));
        } catch (SchedulerException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_SCHEDULER_ERROR));
        }
    }

	@Override
    public void delete(JobVo jobVo) {
        try {
            SchedulerUtils.deleteJob(jobVo.getJobName());
        } catch (SchedulerException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_SCHEDULER_ERROR));
        }
    }

	@Override
    public void pause(JobVo jobVo) {
        try {
            SchedulerUtils.pauseJob(jobVo.getJobName());
        } catch (SchedulerException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_SCHEDULER_ERROR));
        }
    }

	@Override
    public void resume(JobVo jobVo) {
        try {
            SchedulerUtils.resumeJob(jobVo.getJobName());
        } catch (SchedulerException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_SCHEDULER_ERROR));
        }
    }


	@Override
    public void update(JobVo jobVo) {
        try {
            SchedulerUtils.deleteJob(jobVo.getJobName());
            SchedulerUtils.scheduleJob(jobVo);
        } catch (ClassNotFoundException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_CLASS_NOT_FOUND_ERROR));
        } catch (ParseException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_DATE_PARSING_ERROR));
        } catch (SchedulerException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_SCHEDULER_ERROR));
        }
    }

	@Override
     public void save(JobVo jobVo) {
        try {
             SchedulerUtils.saveJob(jobVo);
        } catch (ClassNotFoundException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_CLASS_NOT_FOUND_ERROR));
        } catch (ParseException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_DATE_PARSING_ERROR));
        } catch (SchedulerException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_SCHEDULER_ERROR));
        }
    }

    @Override
    public JobVo findJobByName(String jobName) {
        try {
            return SchedulerUtils.findJobByName(jobName);
        } catch (SchedulerException e) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.JOB_SCHEDULER_ERROR));
        }
    }
}
