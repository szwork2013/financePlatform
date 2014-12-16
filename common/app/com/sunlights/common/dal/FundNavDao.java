package com.sunlights.common.dal;

import models.FundNav;

import java.util.List;

/**
 * Created by guxuelong on 2014/12/15.
 */
public interface FundNavDao {
    List<FundNav> queryByUpdateTime(String updateTime);
}
