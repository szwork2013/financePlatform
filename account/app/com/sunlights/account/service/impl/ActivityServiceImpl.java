package com.sunlights.account.service.impl;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.dal.ActivityDao;
import com.sunlights.account.dal.impl.ActivityDaoImpl;
import com.sunlights.account.service.ActivityService;
import com.sunlights.account.vo.ActivityVo;
import com.sunlights.common.vo.PageVo;
import models.Activity;
import play.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/17.
 */
public class ActivityServiceImpl implements ActivityService{

    private ActivityDao activityDao = new ActivityDaoImpl();

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
            vo.setUrl(getFileFuleUrl(activity.getUrl(), "activity.html5Path"));
            activityVos.add(vo);
        }
        return activityVos;
    }

    private String getFileFuleUrl(String fileName, String remotDir) {
        String server = Configuration.root().getString("activity.server");
        String port = Configuration.root().getString("activity.port");
        String remoteDir = Configuration.root().getString(remotDir);
        return new StringBuilder().append("http://").append(server).append(":").append(port).append(remoteDir).append("/").append(fileName).toString();
    }

    @Override
    public List<Activity> getActivityByScene(String scene) {
        //TODO
        return activityDao.getActivityByScene(scene);
    }
}
