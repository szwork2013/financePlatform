package com.sunlights.customer.service.impl;

import com.sunlights.common.cache.Cacheable;
import com.sunlights.customer.dal.ActivityReportDao;
import com.sunlights.customer.dal.impl.ActivityReportDaoImpl;
import com.sunlights.customer.service.ActivityReportService;
import com.sunlights.customer.vo.ActivityReportVo;
import models.ActivityReport;

import java.util.List;

/**
 * Created by tangweiqun on 2015/3/30.
 */
public class ActivityReportServiceImpl implements ActivityReportService {

    private ActivityReportDao activityReportDao = new ActivityReportDaoImpl();

    @Cacheable(key="getActivityReports", duration = 300)
    @Override
    public ActivityReportVo getActivityReports() {
        List<ActivityReport> activityReports = activityReportDao.loadAll();
        ActivityReportVo activityReportVo = new ActivityReportVo();
        for(ActivityReport activityReport : activityReports) {
            if(ActivityReport.ActivityReportType.REGISTE_TYPE.getType().equals(activityReport.getReportType())) {
                activityReportVo.addRegisterStr(activityReport.getReportDesc());
            } else if(ActivityReport.ActivityReportType.REWARD_TYPE.getType().equals(activityReport.getReportType())) {
                activityReportVo.addRewardStr(activityReport.getReportDesc());
            }
        }
        return activityReportVo;
    }
}
