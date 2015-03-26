package com.sunlights.customer.service.impl;

import com.sunlights.customer.dal.ShowStatisticsDao;
import com.sunlights.customer.dal.impl.ShowStatisticsDaoImpl;
import com.sunlights.customer.service.ShowStatisticsService;
import models.ShowStatistics;
import play.Configuration;

import java.util.List;
import java.util.Random;

/**
 * Created by tangweiqun on 2015/3/26.
 */
public class ShowStatisticsServiceImpl implements ShowStatisticsService {

    private ShowStatisticsDao showStatisticsDao = new ShowStatisticsDaoImpl();

    @Override
    public List<ShowStatistics> getShowStatisticsByType(String type) {
        return showStatisticsDao.getByType(type);
    }

    @Override
    public void addRegisterShowStatistics() {
        List<ShowStatistics> list = getShowStatisticsByType(ShowStatistics.StatType.REGISTE_STAT.getType());
        if(list == null || list.isEmpty()) {
            ShowStatistics showStatistics = new ShowStatistics();
            showStatistics.setStatType(ShowStatistics.StatType.REGISTE_STAT.getType());
            showStatistics.setStatCount(Configuration.root().getLong("register.number.activity", 1320L));
            showStatisticsDao.doInsert(showStatistics);
        } else {
            ShowStatistics showStatistics = list.get(0);
            Random random = new Random();
            int incrNum = random.nextInt(30);
            showStatistics.setStatCount(showStatistics.getStatCount() + incrNum);
            showStatisticsDao.doUpdate(showStatistics);
        }
    }
}
