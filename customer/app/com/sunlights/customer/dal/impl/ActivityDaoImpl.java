package com.sunlights.customer.dal.impl;


import com.google.common.collect.Maps;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.ConverterUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.dal.ActivityDao;
import models.Activity;
import models.ActivityShareInfo;
import play.Logger;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tangweiqun on 2014/11/17.
 */
public class ActivityDaoImpl extends EntityBaseDao implements ActivityDao {

    private PageDao pageDao = new PageDaoImpl();

    @Override
    public List<Activity> getActivityVos(PageVo pageVo) {
        String currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_SHORT);
        String jpql = " select a from Activity a where a.status = 'N' and a.image is not null and a.url is not null  and a.beginTime <= '" +currentDate+ "' and a.endTime >= '" + currentDate + "' order by a.createTime desc ";
        List<Activity> activities = pageDao.findXsqlBy(jpql, pageVo);
        return activities;
    }

    @Override
    public List<Activity> getActivityByScene(String scene) {
        return findBy(Activity.class, "scene", scene);
    }

    @Override
    public List<ActivityShareInfo> getShareInfoByScene(String scene) {
        StringBuilder sb = new StringBuilder();
        String keys = "activityId,title,content,shareUrl,iocnUrl";
        String columns = "  a.id, g.title, g.content, g.share_url,g.icon_url  ";
        sb.append("select ").append(columns)
                .append("from f_activity as a join f_activity_share_info as g on a.id=g.activity_id ");
        sb.append(" where 1 = 1 ");
        sb.append("  /~and a.scene = {scene}~/ ");


        Map<String, Object> filterMap = Maps.newHashMapWithExpectedSize(5);


        filterMap.put("EQS_scene", scene);


        List<Object[]> resultRows = createNativeQueryByMap(sb.toString(), filterMap).getResultList();
        List<ActivityShareInfo> activityShareInfos = ConverterUtil.convert(keys, resultRows, ActivityShareInfo.class);
        return activityShareInfos;
    }
}
