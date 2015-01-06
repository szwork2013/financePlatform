package com.sunlights.customer.service.impl;


import com.sunlights.common.cache.Cacheable;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.ActivityDao;
import com.sunlights.customer.dal.ActivitySceneDao;
import com.sunlights.customer.dal.impl.ActivityDaoImpl;
import com.sunlights.customer.dal.impl.ActivitySceneDaoImpl;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.vo.Activity4H5Vo;
import com.sunlights.customer.vo.ActivityVo;
import models.Activity;
import models.ActivityScene;
import play.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/17.
 */
public class ActivityServiceImpl implements ActivityService{

    private ActivityDao activityDao = new ActivityDaoImpl();

    private ActivitySceneDao activitySceneDao = new ActivitySceneDaoImpl();

    @Deprecated
    @Override
    public List<ActivityVo> getActivityVos(PageVo pageVo) {
        List<Activity> activities = activityDao.getActivityVos(pageVo);
        List<ActivityVo> activityVos = new ArrayList<ActivityVo>();
        if (activities == null) {
            return activityVos;
        }
        ActivityVo vo = null;
        for(Activity activity : activities) {
            vo = new ActivityVo();
            vo.setId(activity.getId());
            vo.setName(activity.getTitle());
            vo.setImage(getFileFuleUrl(activity.getImage(), "activity.imagePath"));
            vo.setUrl(getFileFuleUrl(activity.getUrl(), "activity.html5Path") + "?activityId=" + activity.getId());
            activityVos.add(vo);
        }
        return activityVos;
    }

    @Override
    public String getFileFuleUrl(String fileName, String remotDir) {
        String server = Configuration.root().getString("activity.server");
        String port = Configuration.root().getString("activity.port");
        String remoteDir = Configuration.root().getString(remotDir);
        return new StringBuilder().append("http://").append(server).append(":").append(port).append(remoteDir).append("/").append(fileName).toString();
    }

    @Cacheable(key="scene", duration = 300)
    @Override
    public List<Activity> getActivityByScene(String scene) {
        //TODO
        return activityDao.getActivityByScene(scene);
    }

    @Cacheable(key="activityTitleByPrdCode", duration = 300)
    @Override
    public List<String> getActivityTitles(String prdCode) {
        List<String> titles = new ArrayList<String>();
        if(prdCode == null) {
            return titles;
        }
        ActivityScene activityScene = new ActivityScene();
        activityScene.setActivityType(ActivityConstant.ACTIVITY_TYPE_PURCHASE);
        activityScene.setPrdCode(prdCode);

        List<ActivityScene> activityScenes = activitySceneDao.getScenes(activityScene);
        if(activityScenes == null || activityScenes.isEmpty()) {
            return titles;
        }
        activityScene = activityScenes.get(0);
        List<Activity> activities = getActivityByScene(activityScene.getScene());
        if(activities == null || activities.isEmpty()) {
            return titles;
        }

        for(Activity activity : activities) {
            titles.add(activity.getTitle());
        }
        return titles;
    }



    @Cacheable(key = "allActivities", duration = 300)
    @Override
    public List<Activity> getAllActivities() {
        return activityDao.getAll();
    }

    @Cacheable(key = "getCurrentValidActivities", duration = 300)
    @Override
    public List<Activity> getCurrentValidActivities() {

        return activityDao.getCurrrentValidActivities();
    }

    @Cacheable(key = "getH5InfoById", duration = 300)
    @Override
    public Activity4H5Vo getH5InfoById(Long id) {
        Activity4H5Vo activity4H5Vo = new Activity4H5Vo();
        Activity activity = activityDao.findById(id);
        if(activity == null) {
            return activity4H5Vo;
        }
        activity4H5Vo.setImageUrl(getFileFuleUrl(activity.getImage(), "activity.imagePath"));
        activity4H5Vo.setContent(activity.getH5Content());
        return activity4H5Vo;
    }
}
