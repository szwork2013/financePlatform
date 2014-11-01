package com.sunlights.account.dal;

import com.sunlights.account.models.HoldCapital;

import java.util.List;

/**
 * 资产Dao接口
 * @author tangweiqun 2014/10/22
 *
 */
public interface CapitalDao {
	
	public List<HoldCapital> getHoldCapitalsByCustId(String custId);
	
}
