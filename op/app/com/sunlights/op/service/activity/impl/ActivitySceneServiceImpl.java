package com.sunlights.op.service.activity.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.service.PageService;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.service.activity.ActivitySceneService;
import com.sunlights.op.vo.activity.ActivitySceneVo;
import models.ActivityScene;

import java.util.Date;
import java.util.List;

/**
 * <p>Project: operationPlatform</p>
 * <p>Title: ActivitySceneServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class ActivitySceneServiceImpl extends EntityBaseDao implements ActivitySceneService {

    public static final String ACTIVITY_SCENE_STATUS_FORBID = "F";
    public static final String ACTIVITY_SCENE_STATUS_NORMAL = "N";
    private PageService pageService = new PageService();

    @Override
    public List<ActivitySceneVo> findScenesBy(PageVo pageVo) {
        StringBuilder xsql = new StringBuilder();
        xsql.append(" select new com.sunlights.op.vo.activity.ActivitySceneVo(ac) from ActivityScene ac");
        xsql.append(" where 1=1");
        xsql.append(" /~ and ac.scene like {scene} ~/ ");
        xsql.append(" /~ and ac.title like {title} ~/ ");
        xsql.append(" /~ and ac.status = {status} ~/ ");
        xsql.append(" /~ and ac.activityType = {activityType} ~/ ");
        xsql.append(" /~ and ac.prdCode like {prdCode} ~/ ");
        xsql.append(" /~ and ac.prdType = {prdType} ~/ ");

        List<ActivitySceneVo> activitySceneVos = pageService.findXsqlBy(xsql.toString(), pageVo);
        return activitySceneVos;
    }

    @Override
    public void create(ActivitySceneVo activitySceneVo) {
        Date date = new Date();
        if (hasActivityScene(activitySceneVo)) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.ACTIVITY_SCENE_EXIST_ERROR));
        }
        ActivityScene activityScene = activitySceneVo.convertToActivityScene();
        activityScene.setStatus(ACTIVITY_SCENE_STATUS_NORMAL);
        activityScene.setCreateTime(date);
        activityScene.setUpdateTime(date);
        super.create(activityScene);


    }

    @Override
    public void update(ActivitySceneVo activitySceneVo) {
        if (hasActivityScene(activitySceneVo)) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.ACTIVITY_SCENE_EXIST_ERROR));
        }
        ActivityScene activityScene = activitySceneVo.convertToActivityScene();
        activityScene.setUpdateTime(new Date());
        super.update(activityScene);

    }

    @Override
    public void delete(ActivitySceneVo activitySceneVo) {
        ActivityScene activityScene = super.find(ActivityScene.class, activitySceneVo.getId());
        super.delete(activityScene);
    }

    private boolean hasActivityScene(ActivitySceneVo activitySceneVo) {
        StringBuffer jpql = new StringBuffer();
        jpql.append(" select ac from ActivityScene ac");
        jpql.append(" where ac.scene = '" + activitySceneVo.getScene() + "'");
        if (activitySceneVo.getId() != null) {
            jpql.append(" and ac.id <> ").append(activitySceneVo.getId());
        }
        List<ActivityScene> activityScenes = super.find(jpql.toString());
        return !activityScenes.isEmpty();
    }
}
