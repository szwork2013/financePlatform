package com.sunlights.customer.service.impl;

import com.sunlights.common.utils.CommonUtil;
import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.CustJoinActivityDao;
import com.sunlights.customer.dal.impl.CustJoinActivityDaoImpl;
import com.sunlights.customer.service.CustJoinActivityService;
import models.CustJoinActivity;
import play.Logger;

import java.util.*;

/**
 * Created by tangweiqun on 2014/12/2.
 */
public class CustJoinActivityServiceImpl implements CustJoinActivityService {

    private CustJoinActivityDao custJoinActivityDao = new CustJoinActivityDaoImpl();

    @Override
    public CustJoinActivity getByCustAndActivity(String custId, Long activityId, String scene) {
        CustJoinActivity custJoinActivity = new CustJoinActivity();
        custJoinActivity.setCustId(custId);
        custJoinActivity.setActivityId(activityId);
        custJoinActivity.setScene(scene);
        List<CustJoinActivity> custJoinActivityList = custJoinActivityDao.queryByCondition(custJoinActivity);
        if(custJoinActivityList != null && !custJoinActivityList.isEmpty()) {
            return custJoinActivityList.get(0);
        }
        return null;
    }

    @Override
    public String getShortUrl(String custId, Long activityId, String scene) {
        CustJoinActivity custJoinActivity = getByCustAndActivity(custId, activityId, scene);
        if(custJoinActivity != null) {
            return custJoinActivity.getShortUrl();
        }
        return null;
    }

    @Override
    public void saveCustJoinActivity(CustJoinActivity custJoinActivity) {
        custJoinActivityDao.doInsert(custJoinActivity);
    }

    @Override
    public void saveShortUrl(String custId, Long activityId, String scene, String shortUrl) {
        CustJoinActivity custJoinActivity = new CustJoinActivity();
        custJoinActivity.setCustId(custId);
        custJoinActivity.setActivityId(activityId);
        custJoinActivity.setShortUrl(shortUrl);
        custJoinActivity.setScene(scene);
        custJoinActivity.setJoined(ActivityConstant.ACCOUNT_COMMON_ZERO);
        custJoinActivity.setContinued(ActivityConstant.ACCOUNT_COMMON_ONE);

        custJoinActivityDao.doInsert(custJoinActivity);
    }

    @Override
    public CustJoinActivity getTodayRecordByCustAndActivity(String custId, Long activityId, String scene) {
        CustJoinActivity custJoinActivity = new CustJoinActivity();
        custJoinActivity.setCustId(custId);
        custJoinActivity.setActivityId(activityId);
        custJoinActivity.setScene(scene);

        String dateStr = CommonUtil.dateToString(new Date(), CommonUtil.DATE_FORMAT_SHORT);
        String startDate = dateStr + " 00:00:00";
        String endDate = dateStr + " 23:59:59";
        Logger.debug("startDate = " + startDate + " endDate = " + endDate);
        List<CustJoinActivity> custJoinActivityList = custJoinActivityDao.queryByCondition(custJoinActivity, startDate, endDate);
        if(custJoinActivityList != null && !custJoinActivityList.isEmpty()) {
            return custJoinActivityList.get(0);
        }
        return null;
    }

    @Override
    public Map<String, List<CustJoinActivity>> mapWithScene(String custNo) {
        CustJoinActivity custJoinActivity = new CustJoinActivity();
        custJoinActivity.setCustId(custNo);
        List<CustJoinActivity> custJoinActivityList = custJoinActivityDao.queryByCondition(custJoinActivity);
        Map<String, List<CustJoinActivity>> mapResult = new HashMap<String, List<CustJoinActivity>>();
        for(CustJoinActivity temp : custJoinActivityList) {
            String scene = temp.getScene();
            if(mapResult.containsKey(scene)) {
                mapResult.get(scene).add(temp);
            } else {
                List<CustJoinActivity> list = new ArrayList<CustJoinActivity>();
                list.add(temp);
                mapResult.put(scene, list);
            }
        }
        return mapResult;
    }
}
