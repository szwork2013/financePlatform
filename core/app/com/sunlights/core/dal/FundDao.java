package com.sunlights.core.dal;

import models.Fund;
import models.FundHistory;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: FundDao.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public interface FundDao {
  public Fund findFundByCode(String code);

    public FundHistory findFundHistoryByCode(String code);

  public List<FundHistory> findFundHistoriesByDays(String fundCode, int days);
}
