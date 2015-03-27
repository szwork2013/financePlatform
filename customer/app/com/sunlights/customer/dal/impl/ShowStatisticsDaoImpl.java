package com.sunlights.customer.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.customer.dal.ShowStatisticsDao;
import models.ShowStatistics;

import java.util.Date;
import java.util.List;

/**
 * Created by tangweiqun on 2015/3/26.
 */
public class ShowStatisticsDaoImpl extends EntityBaseDao implements ShowStatisticsDao {

    @Override
    public List<ShowStatistics> getByType(String type) {
        return super.findBy(ShowStatistics.class, "statType", type);
    }

    @Override
    public void doInsert(ShowStatistics showStatistics) {
        showStatistics.setCreateTime(new Date());
        super.create(showStatistics);
    }

    @Override
    public void doUpdate(ShowStatistics showStatistics) {
        showStatistics.setUpdateTime(new Date());
        super.update(showStatistics);
    }
}
