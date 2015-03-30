package com.sunlights.customer.dal;

import models.ActivityReport;

import java.util.List;

/**
 * Created by tangweiqun on 2015/3/30.
 */
public interface ActivityReportDao {

    public List<ActivityReport> loadAll();
}
