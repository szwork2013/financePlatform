package com.sunlights.op.service;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.jobs.JobVo;

import java.util.List;

/**
 * Created by Yuan on 2015/3/11.
 */
public interface TaskService {

	public List<JobVo> findTasksBy(PageVo pageVo);

	public void create(JobVo jobVo);

	public void delete(JobVo jobVo);

	public void pause(JobVo jobVo);

	public void resume(JobVo jobVo);

	public void update(JobVo jobVo);

	public void save(JobVo jobVo);

	public JobVo findJobByName(String jobName);

}
