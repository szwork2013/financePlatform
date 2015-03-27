package com.sunlights.customer.service;

import models.ShowStatistics;

import java.util.List;

/**
 * Created by tangweiqun on 2015/3/26.
 */
public interface ShowStatisticsService {

    public List<ShowStatistics> getShowStatisticsByType(String type);

    public void addRegisterShowStatistics();

}
