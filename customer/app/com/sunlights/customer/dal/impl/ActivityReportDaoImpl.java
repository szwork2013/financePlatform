package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ActivityReportDao;
import models.ActivityReport;

import java.util.List;

/**
 * Created by Administrator on 2015/3/30.
 */
public class ActivityReportDaoImpl extends EntityBaseDao implements ActivityReportDao {

    @Override
    public List<ActivityReport> loadAll() {
        return super.findAll(ActivityReport.class);
    }
}
