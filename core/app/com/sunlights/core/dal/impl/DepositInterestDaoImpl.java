package com.sunlights.core.dal.impl;

import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.core.dal.DepositInterestDao;
import com.sunlights.core.vo.DepositInterestVo;
import models.DepositInterest;

import java.util.List;

/**
 * Created by Administrator on 2014/12/15.
 */
public class DepositInterestDaoImpl extends EntityBaseDao implements DepositInterestDao {


    @Override
    public DepositInterestVo findLatestDepositInterest() {
        List<DepositInterest> depositInterests = super.findAll(DepositInterest.class, "date", false);
		return depositInterests.isEmpty() ? null : new DepositInterestVo(depositInterests.get(0));
    }
}
