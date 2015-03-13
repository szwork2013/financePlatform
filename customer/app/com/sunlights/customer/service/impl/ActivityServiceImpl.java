package com.sunlights.customer.service.impl;


import com.sunlights.common.DictConst;
import com.sunlights.common.cache.Cacheable;
import com.sunlights.common.service.ParameterService;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.ActivityDao;
import com.sunlights.customer.dal.ActivitySceneDao;
import com.sunlights.customer.dal.CustomerDao;
import com.sunlights.customer.dal.ObtainRewardRuleDao;
import com.sunlights.customer.dal.impl.ActivityDaoImpl;
import com.sunlights.customer.dal.impl.ActivitySceneDaoImpl;
import com.sunlights.customer.dal.impl.CustomerDaoImpl;
import com.sunlights.customer.dal.impl.ObtainRewardRuleDaoImpl;
import com.sunlights.customer.service.ActivityService;
import com.sunlights.customer.service.CustJoinActivityService;
import com.sunlights.customer.vo.Activity4H5Vo;
import com.sunlights.customer.vo.ActivityVo;
import models.Activity;
import models.ActivityScene;
import models.CustJoinActivity;
import models.ObtainRewardRule;
import play.Configuration;
import play.Logger;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tangweiqun on 2014/11/17.
 */
public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = new ActivityDaoImpl();
    private ObtainRewardRuleDao obtainRewardRuleDao = new ObtainRewardRuleDaoImpl();
    private ActivitySceneDao activitySceneDao = new ActivitySceneDaoImpl();
    private CustJoinActivityService custJoinActivityService = new CustJoinActivityServiceImpl();
    private CustomerDao customerDao = new CustomerDaoImpl();

    private ParameterService parameterService = new ParameterService();

    @Deprecated
    @Override
    public List<ActivityVo> getActivityVos(PageVo pageVo) {
        List<Activity> activities = activityDao.getActivityVos(pageVo);
        List<ActivityVo> activityVos = new ArrayList<ActivityVo>();
        if (activities == null) {
            return activityVos;
        }
        ActivityVo vo = null;
        for (Activity activity : activities) {
            vo = new ActivityVo();
            vo.setId(activity.getId());
            vo.setName(activity.getTitle());
            vo.setImage(getFileFuleUrl(activity.getImage(), ActivityConstant.ACTIVITY_IMAGE_PATH));
            vo.setUrl(getFileFuleUrl(activity.getUrl(), ActivityConstant.ACTIVITY_HTML5_PATH) + "?activityId=" + activity.getId());
            activityVos.add(vo);
        }
        return activityVos;
    }



    @Override
    public String getFileFuleUrl(String fileName, String remotDir) {
        String server = parameterService.getParameterByName(ActivityConstant.ACTIVITY_SERVER);
        String port = parameterService.getParameterByName(ActivityConstant.ACTIVITY_SERVER_PORT);
        String remoteDir = parameterService.getParameterByName(remotDir);
        return new StringBuilder().append(server).append(":").append(port).append(remoteDir).append("/").append(fileName).toString();
    }

    @Cacheable(key = "scene", duration = 300)
    @Override
    public List<Activity> getActivityByScene(String scene) {
        //TODO
        return activityDao.getActivityByScene(scene);
    }

    @Cacheable(key = "activityTitleByPrdCode", duration = 300)
    @Override
    public List<String> getActivityTitles(String prdCode) {
        List<String> titles = new ArrayList<String>();
        if (prdCode == null) {
            return titles;
        }
        ActivityScene activityScene = new ActivityScene();
        activityScene.setActivityType(ActivityConstant.ACTIVITY_TYPE_PURCHASE);
        activityScene.setPrdCode(prdCode);

        List<ActivityScene> activityScenes = activitySceneDao.getScenes(activityScene);
        if (activityScenes == null || activityScenes.isEmpty()) {
            return titles;
        }
        activityScene = activityScenes.get(0);
        List<Activity> activities = getActivityByScene(activityScene.getScene());
        if (activities == null || activities.isEmpty()) {
            return titles;
        }

        for (Activity activity : activities) {
            titles.add(activity.getTitle());
        }
        return titles;
    }


    @Cacheable(key = "allActivities", duration = 300)
    @Override
    public List<Activity> getAllActivities() {
        return activityDao.getAll();
    }

    @Cacheable(key = "getCurrentValidActivities", duration = 300)
    @Override
    public List<Activity> getCurrentValidActivities() {

        return activityDao.getCurrrentValidActivities();
    }

    @Cacheable(key = "getH5InfoById", duration = 300)
    @Override
    public Activity4H5Vo getH5InfoById(Long id) {
        Activity4H5Vo activity4H5Vo = new Activity4H5Vo();
        Activity activity = activityDao.findById(id);
        if (activity == null) {
            return activity4H5Vo;
        }
        activity4H5Vo.setImageUrl(getFileFuleUrl(activity.getImage(), ActivityConstant.ACTIVITY_IMAGE_PATH));
        activity4H5Vo.setContent(activity.getH5Content());
        return activity4H5Vo;
    }

    @Cacheable(key = "getByUnknowCondition", duration = 300)
    @Override
    public Activity getByUnknowCondition(String condition) {
        Activity activity = new Activity();
        try {
            activity = activityDao.findById(Long.valueOf(condition));
        } catch (Exception e) {
            List<Activity> activities = activityDao.getActivityByScene(condition);
            if (activities != null && !activities.isEmpty()) {
                activity = activities.get(0);
            }
        }
        return activity;
    }

    @Override
    public Integer countActivityRemain(Long id) {
        ObtainRewardRule obtainRewardRule = obtainRewardRuleDao.findRewardRuleByActivityId(id);
        Logger.info(">>countActivityRemain obtainRewardRule: " + obtainRewardRule);
        if (obtainRewardRule == null) {
            return null;
        }
        return getRemainNum(obtainRewardRule);
    }

    private synchronized Integer getRemainNum(ObtainRewardRule obtainRewardRule) {

        Integer totalCount = obtainRewardRule.getTotalCount();
        Integer outTotalCount = obtainRewardRule.getOutTotalCount();
        if (totalCount == null || outTotalCount == null) {
            return null;
        }

        String activityCondition = obtainRewardRule.getActivityCondition();
        Long activityId = obtainRewardRule.getActivityId();

        int hasSendCount = 0;
        if (DictConst.ACTIVITY_NEW_REGISTER_TRADE.equals(activityCondition)) {
            hasSendCount = activityDao.countRegisterHasSend(activityId);
        } else if (DictConst.ACTIVITY_NEW_TRADE.equals(activityCondition)) {
            hasSendCount = activityDao.countTradeHasSend(activityId);
        }

        BigDecimal every = ArithUtil.bigUpScale0(new BigDecimal(outTotalCount / (double) totalCount));
        BigDecimal showRemainCount = new BigDecimal(outTotalCount).subtract(every.multiply(new BigDecimal(hasSendCount)));

        BigDecimal remainCount = BigDecimal.ZERO.compareTo(showRemainCount) >= 0 ? BigDecimal.ZERO : showRemainCount;

        return remainCount.intValue();
    }

    @Override
    public boolean validateActivityIsOver(Long id) {
        Activity activity = activityDao.findById(id);

        if (!ActivityConstant.ACTIVITY_CUSTONER_STATUS_NOMAL.equals(activity.getStatus())) {
            return true;
        }

        Date currentTime = DBHelper.getCurrentTime();
        try {
            currentTime = CommonUtil.stringToDate(CommonUtil.dateToString(currentTime, CommonUtil.DATE_FORMAT_SHORT), CommonUtil.DATE_FORMAT_SHORT);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (activity.getEndTime() != null && activity.getEndTime().before(currentTime)) {
            return true;
        }

        Integer remain = countActivityRemain(id);
        if (remain != null && remain == 0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean validateHasFirstPurchase(String customerId, Long activityId) {
        //活动内首次申购 验证
        CustJoinActivity custJoinActivity = custJoinActivityService.getByCustAndActivity(customerId, activityId, ActivityConstant.ACTIVITY_FIRST_PURCHASE_SCENE_CODE);
        if (custJoinActivity != null) {
            return true;
        }

        List<ObtainRewardRule> obtainRewardRuleList = obtainRewardRuleDao.findFirstPurchaseEffective(activityId);
        if (!obtainRewardRuleList.isEmpty() && obtainRewardRuleList.size() == 1) {
            ObtainRewardRule obtainRewardRule = obtainRewardRuleList.get(0);
            String activityCondition = obtainRewardRule.getActivityCondition();
            if (activityCondition != null) {
                if (DictConst.ACTIVITY_NEW_TRADE.equals(activityCondition)) {//首次申购 验证
                    boolean isFirstPurchase = customerDao.validateHasFirstPurchase(customerId);
                    if (isFirstPurchase) {
                        return true;
                    }

                    Integer nowRemainCount = getRemainNum(obtainRewardRule);
                    if (nowRemainCount != null && nowRemainCount == 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
