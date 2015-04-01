package com.sunlights.op.service.activity;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.activity.ActivitySceneVo;

import java.util.List;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: ActivitySceneService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface ActivitySceneService {

    public List<ActivitySceneVo> findScenesBy(PageVo pageVo);

    public void create(ActivitySceneVo activitySceneVo);

    public void update(ActivitySceneVo activitySceneVo);

    public void delete(ActivitySceneVo activitySceneVo);
}
