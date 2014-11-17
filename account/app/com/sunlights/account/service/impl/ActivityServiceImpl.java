package com.sunlights.account.service.impl;

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
            vo.setImage(getFileFuleUrl(activity.getImage()));
            vo.setUrl(getFileFuleUrl(activity.getUrl()));
            activityVos.add(vo);
        }
        return activityVos;
    }

    private String getFileFuleUrl(String fileName) {
        String server = Configuration.root().getString("nginx.server");
        String remoteDir = Configuration.root().getString("nginx.imagePath");
        return new StringBuilder().append("http://").append(server).append(remoteDir).append("/").append(fileName).toString();
    }
}
