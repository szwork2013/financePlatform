package com.sunlights.op.dal.activity.impl;

import com.google.common.collect.Maps;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.op.dal.activity.ActivityDao;
import com.sunlights.op.vo.activity.ActivityVo;
import models.Activity;
import play.Logger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/11/13.
 */
public class ActivityDaoImpl extends EntityBaseDao implements ActivityDao {

    private PageDao pageDao = new PageDaoImpl();


    @Override
    public List<Activity> findActicities() {
        return findAll(Activity.class, "id", true);
    }

    @Override
    public Activity add(Activity vo) {
        vo.setClickTime(0L);
        vo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        create(vo);
        return vo;
    }

    @Override
    public boolean deleteActivity(Long id) {
        Activity activity = findById(id);
        if(activity == null) {
            return false;
        }
        activity.setStatus("F");
        activity.setUpdateTime(new Date());
        delete(activity);
        return true;
    }

    @Override
    public Activity modifyActivity(Activity vo) {
        Activity activity = findById(vo.getId());
        System.out.println(activity);
        if(activity == null) {
            return null;
        }
        vo.setCreateTime(activity.getCreateTime());
        vo.setUpdateTime(new Date());
        update(vo);
        return vo;
    }

    @Override
    public Activity findById(Long id) {
        List<Activity> activities = findBy(Activity.class, "id", id);
        if ( activities.isEmpty() ) {
            return null;
        }else{
            return activities.get(0);
        }
    }

    @Override
    public List<ActivityVo> findActivityWithRule(PageVo pageVo) {
        StringBuilder selectSql = new StringBuilder();
        selectSql.append(" from f_activity as a join f_activity_scene  s on a.scene = s.code ");

        StringBuilder condition = new StringBuilder();
        condition.append(" where 1 = 1 ");
        condition.append("  /~and a.id = {id}~/ ");
        condition.append("  /~and a.title like {title}~/ ");


        StringBuilder sb = new StringBuilder();
        String keys = "id,title,beginTime,endTime,status,clickTime,scene,sceneName,image,url ";
        String columns = " a.id,  a.title,a.begin_time,a.end_time, a.status,a.click_time,a.scene,s.name,a.image, a.url ";
        sb.append("select ").append(columns)
                .append(selectSql);
        sb.append(condition);

        StringBuilder countSb = new StringBuilder();
        countSb.append("select count(*) ")
                .append(selectSql);
        countSb.append(condition);

        List list = pageDao.findNativeComplexBy(sb.toString(), countSb.toString(), pageVo);

        List<ActivityVo> activityVos = ConverterUtil.convert(keys, list, ActivityVo.class);


        Logger.debug("activityVos = " + activityVos.size());

        return activityVos;
    }

}
