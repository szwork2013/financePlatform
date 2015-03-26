package com.sunlights.customer.dal;

import models.ShowStatistics;

import java.util.List;

/**
 * Created by tangweiqun on 2015/3/26.
 */
public interface ShowStatisticsDao {

    public List<ShowStatistics> getByType(String type);

    public void doInsert(ShowStatistics showStatistics);

    public void doUpdate(ShowStatistics showStatistics);
}
