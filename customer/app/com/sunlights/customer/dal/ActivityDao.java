package com.sunlights.customer.dal;

import com.sunlights.common.vo.PageVo;
import models.Activity;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/17.
 */
public interface ActivityDao {

    public List<Activity> getActivityVos(PageVo pageVo);

    /**
     * 根据活动的应用场景查处这个场景下的所有活动
     *
     * @param scene 应用场景
     * @return
     */
    public List<Activity> getActivityByScene(String scene);

    /**
     * 获取指定场景下可以参加的活动
     * @param scene
     * @return
     */
    public List<Activity> canAttendActivities(String scene);


    public Activity findById(Long id);

    public List<Activity> getAll();

    public List<Activity> getCurrrentValidActivities();


    /**
     * 活动开始后  新注册用户且参加活动 满足条件发送  已发送数量查询
     *
     * @param id
     * @return
     */
    public int countRegisterHasSend(Long id);

    /**
     * 活动开始后  首次购买用户 满足条件发送  已发送数量查询
     *
     * @param id
     * @return
     */
    public int countTradeHasSend(Long id);

}
