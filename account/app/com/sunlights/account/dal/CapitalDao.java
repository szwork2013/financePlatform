package com.sunlights.account.dal;

import java.util.List;

import com.sunlights.account.models.HoldCapital;

/**
 * 资产Dao接口
 * @author tangweiqun 2014/10/22
 *
 */
public interface CapitalDao {
	
	public List<HoldCapital> getHoldCapitalsByCustId(String custId);
	
}
