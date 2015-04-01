package com.sunlights.op.dal.activity.impl;

import com.google.common.collect.Maps;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ConverterUtil;
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
    public List<ActivityVo> findActivityWithRule(Long id,String title, String type) {
        StringBuilder sb = new StringBuilder();
        String keys = "id,title,beginTime,endTime,status,clickTime,scene,sceneName,image,url ";
        String columns = " a.id,  a.title,a.begin_time,a.end_time, a.status,a.click_time,a.scene,s.name,a.image, a.url ";
        sb.append("select ").append(columns)
                .append("from f_activity as a join f_activity_scene  s on a.scene = s.code ");
        sb.append(" where 1 = 1 ");
        sb.append("  /~and a.id = {id}~/ ");
        sb.append("  /~and a.title like {title}~/ ");
        sb.append(" /~and a.type = {type} ~/ ");
        sb.append(" order by id ");

        Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(5);

        filterMap.put("LIKES_title", title);
        filterMap.put("EQS_type", type);
        filterMap.put("EQL_id", id);

        List<Object[]> resultRows = createNativeQueryByMap(sb.toString(), filterMap).getResultList();
        List<ActivityVo> activityVos = ConverterUtil.convert(keys, resultRows, ActivityVo.class);


        Logger.debug("activityVos = " + activityVos.size());

        return activityVos;
    }

}
