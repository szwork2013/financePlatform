package com.sunlights.account.dal.impl;

import java.util.List;

import com.sunlights.account.dal.CapitalDao;
import com.sunlights.account.models.HoldCapital;
import com.sunlights.common.dal.EntityBaseDao;

import org.springframework.stereotype.Repository;

/**
 * 资产Dao实现
 * @author tangweiqun 2014/10/22
 *
 */

@Repository("capitalDao")
public class CapitalDaoImpl extends EntityBaseDao implements CapitalDao {
	
	@Override
	public List<HoldCapital> getHoldCapitalsByCustId(String custId) {
		return super.findBy(HoldCapital.class, "custId", custId);
	}

}
