package com.sunlights.core.service.impl;

import com.sunlights.core.dal.DepositInterestDao;
import com.sunlights.core.dal.impl.DepositInterestDaoImpl;
import com.sunlights.core.service.DepositInterestService;
import com.sunlights.core.vo.DepositInterestVo;

import java.math.BigDecimal;

/**
 * Created by Yuan on 2014/12/15.
 */
public class DepositInterestServiceImpl implements DepositInterestService {

    private DepositInterestDao depositInterestDao = new DepositInterestDaoImpl();


    @Override
    public BigDecimal findCurrent() {
        DepositInterestVo depositInterest = depositInterestDao.findLatestDepositInterest();
        return depositInterest == null ?  null : depositInterest.getCurrent();
    }
}
