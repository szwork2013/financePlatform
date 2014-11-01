package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.CapitalDao;
import com.sunlights.account.models.HoldCapital;
import com.sunlights.common.dal.EntityBaseDao;

import java.util.List;

/**
 * 资产Dao实现
 * @author tangweiqun 2014/10/22
 *
 */
public class CapitalDaoImpl extends EntityBaseDao implements CapitalDao {
	
	@Override
	public List<HoldCapital> getHoldCapitalsByCustId(String custId) {
		return super.findBy(HoldCapital.class, "custId", custId);
	}

}
