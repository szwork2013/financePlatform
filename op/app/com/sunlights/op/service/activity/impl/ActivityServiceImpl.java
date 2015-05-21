package com.sunlights.op.service.activity.impl;


import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.activity.ActivityDao;
import com.sunlights.op.dal.activity.ObtainRewardRuleDao;
import com.sunlights.op.dal.activity.impl.ActivityDaoImpl;
import com.sunlights.op.dal.activity.impl.ObtainRewardRuleDaoImpl;
import com.sunlights.op.service.activity.ActivityService;
import com.sunlights.op.vo.activity.ActivityVo;
import models.Activity;
import play.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/11/13.
 */
public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = new ActivityDaoImpl();

    private ObtainRewardRuleDao obtainRewardRuleDao = new ObtainRewardRuleDaoImpl();
    @Override
    public List<ActivityVo> findActicities() {
        List<Activity> activities = activityDao.findActicities();
        List<ActivityVo> vos = new ArrayList<ActivityVo>();
        for(Activity activity : activities) {
            vos.add(new ActivityVo(activity));
        }
        return vos;
    }

    @Override
    public Activity add(ActivityVo vo) {
        Activity activity = vo.convert2Model(vo);
        //if(StringUtils.isEmpty(activity.getShareUrl())) {
           // activity.setShareUrl(getFileFuleUrl(activity.getUrl(), "activity.html5Path"));
       // }
        return activityDao.add(activity);
    }

    public String getFileFuleUrl(String fileName, String remotDir) {
        String server = Configuration.root().getString("nginx.server");
        String port = Configuration.root().getString("activity.port");
        String remoteDir = Configuration.root().getString(remotDir);
        return new StringBuilder().append("http://").append(server).append(":").append(port).append(remoteDir).append("/").append(fileName).toString();
    }

    @Override
    public Activity modifyActivity(ActivityVo vo) {
        return activityDao.modifyActivity(vo.convert2Model(vo));
    }

    @Override
    public void deleteActivity(Long id) {
        activityDao.deleteActivity(id);
    }

    @Override
    public List<ActivityVo> findActivityWithRule(PageVo pageVo) {

        return activityDao.findActivityWithRule(pageVo);
    }

    @Override
    public String getH5Content(Long activityId) {
        Activity activity = activityDao.findById(activityId);
        if(activity == null) {
            return "";
        }
        return activity.getH5Content();
    }

    @Override
    public void saveH5Content(Long activityId, String h5Content) {
        Activity activity = activityDao.findById(activityId);
        if(activity == null) {
            return;
        }
        activity.setH5Content(h5Content);
        activityDao.modifyActivity(activity);
    }
}
