package com.sunlights.account.dal.impl;

import com.sunlights.account.AccountConstant;
import com.sunlights.account.dal.ActivityDao;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import models.Activity;

import java.util.Date;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/17.
 */
public class ActivityDaoImpl extends EntityBaseDao implements ActivityDao{

    private PageDao pageDao = new PageDaoImpl();

    @Override
    public List<Activity> getActivityVos(PageVo pageVo) {
        String currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_SHORT);
        String jpql = " select a from Activity a where a.status = 'N' and a.scene = '"+ AccountConstant.ACTIVITY_PICTURE_SCENE_CODE +"'  and a.beginTime <= '" +currentDate+ "' and a.endTime >= '" + currentDate + "' order by a.createTime desc ";
        List<Activity> activities = pageDao.findXsqlBy(jpql, pageVo);
        return activities;
    }

    @Override
    public List<Activity> getActivityByScene(String scene) {
        return findBy(Activity.class, "scene", scene);
    }
}
