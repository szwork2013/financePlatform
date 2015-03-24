package com.sunlights.customer.service;


import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.vo.Activity4H5Vo;
import com.sunlights.customer.vo.ActivityVo;
import models.Activity;
import models.ObtainRewardRule;

import java.util.List;

/**
 * Created by tangweiqun on 2014/11/17.
 */
public interface ActivityService {

    /**
     * 获取所有的活动
     * @return
     */
    public List<Activity> getAllActivities();

    /**
     * 分页获取活动列表
     * @param pageVo
     * @return
     */
    public List<ActivityVo> getActivityVos(PageVo pageVo);

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

    /**
     * 获取参加活动的产品的活动名称
     * @param prdCode
     * @return
     */
    public List<String> getActivityTitles(String prdCode);

    /**
     * 获取静态资源的全路径
     * @param fileName
     * @param remotDir
     * @return
     */
    public String getFileFuleUrl(String fileName, String remotDir);

    /**
     * 根据条件获取活动
     * @param condition  如果是数字的话表示是活动id，如果不是的话则表示活动场景哟
     * @return
     */
    public Activity getByUnknowCondition(String condition);

    /**
     * 获取当前有效的活动列表
     * @return
     */
    public List<Activity> getCurrentValidActivities();

    /**
     * 根据活动主键获取活动html5的内容
     * @param id
     * @return
     */
    public Activity4H5Vo getH5InfoById(Long id);

    /**
     * 1验证客户是否已做过首次申购   2 验证该首次申购的活动是否未结束
     *
     * @param customerId
     * @param activityId
     * @return
     */
    public boolean validateHasFirstPurchase(String customerId, Long activityId);

    /**
     * 对该活动进行 剩余数量计数
     *
     * @param id
     * @return null 无数量限制 0无剩余数
     */
    public Integer countActivityRemain(Long id);


    /**
     * 验证活动是否结束
     *
     * @param id
     * @return
     */
    public boolean validateActivityIsOver(Long id);

    /**
     * 获取这个规则还有多少个人参加活动
     * @param obtainRewardRule
     * @return
     */
    public Integer getRemainNum(ObtainRewardRule obtainRewardRule);


}
