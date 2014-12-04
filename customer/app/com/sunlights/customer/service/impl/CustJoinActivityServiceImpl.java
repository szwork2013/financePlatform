package com.sunlights.customer.service.impl;

import com.sunlights.customer.ActivityConstant;
import com.sunlights.customer.dal.CustJoinActivityDao;
import com.sunlights.customer.dal.impl.CustJoinActivityDaoImpl;
import com.sunlights.customer.service.CustJoinActivityService;
import models.CustJoinActivity;

import java.util.Date;
import java.util.List;

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
    public void saveShortUrl(String custId, Long activityId, String shortUrl, String scene) {
        CustJoinActivity custJoinActivity = new CustJoinActivity();
        custJoinActivity.setCustId(custId);
        custJoinActivity.setActivityId(activityId);
        custJoinActivity.setShortUrl(shortUrl);
        custJoinActivity.setScene(scene);
        custJoinActivity.setJoined(ActivityConstant.ACCOUNT_COMMON_ZERO);
        custJoinActivity.setContinued(ActivityConstant.ACCOUNT_COMMON_ONE);

        custJoinActivityDao.doInsert(custJoinActivity);
    }
}
