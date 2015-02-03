package com.sunlights.customer.dal.impl;


import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.dal.PageDao;
import com.sunlights.common.dal.impl.PageDaoImpl;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.dal.ActivityDao;
import models.Activity;
import play.Logger;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

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
    public Activity findById(Long id) {
        List<Activity> activities = findBy(Activity.class, "id", id);
        if(activities == null || activities.isEmpty()) {
            return null;
        }
        return activities.get(0);
    }

    @Override
    public List<Activity> getAll() {
        return super.findAll(Activity.class, "createTime", false);
    }

    @Override
    public List<Activity> getCurrrentValidActivities() {
        String currentDate = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_SHORT);
        String jpql = " select a from Activity a where a.status = 'N'  and a.beginTime <= '" +currentDate+ "' and a.endTime >= '" + currentDate + "' order by a.createTime desc ";
        List<Activity> activities = find(jpql);
        return activities;
    }

    @Override
    public int countRegisterHasSend(Long id){
        String countSql = "select count(1) " +
                " from f_cust_activity_join rf,c_customer c,f_activity a " +
                " where rf.customer_id = c.customer_id " +
                " and rf.activity_id = a.id " +
                " and c.create_time >= a.begin_time " +
                " and a.id = :id";
        
        Query query = em.createNativeQuery(countSql);
        query.setParameter("id", id);
        return Integer.valueOf(query.getSingleResult().toString());
    }

    @Override
    public int countTradeHasSend(Long id) {
        String countSql = "select count(1) " +
                " from f_cust_activity_join rf,c_customer c,f_activity a " +
                " where rf.customer_id = c.customer_id " +
                " and rf.activity_id = a.id " +
                " and a.begin_time <= (select t.trade_time from t_trade t where t.cust_id = c.customer_id limit 1 offset 0) " +
                " and a.id = :id";

        Logger.debug(countSql);

        Query query = em.createNativeQuery(countSql);
        query.setParameter("id", id);
        return Integer.valueOf(query.getSingleResult().toString());
    }

}
