package com.sunlights.core.dal;

import com.sunlights.core.vo.DepositInterestVo;

/**
 * Created by Administrator on 2014/12/15.
 */
public interface DepositInterestDao {
    public DepositInterestVo findLatestDepositInterest();
}
