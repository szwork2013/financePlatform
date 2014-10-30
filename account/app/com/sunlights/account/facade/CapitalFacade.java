package com.sunlights.account.facade;

import java.util.List;

import com.sunlights.account.facade.model.Capital4Product;
import com.sunlights.account.facade.model.TotalCapitalInfo;

/**
 * 资产管理
 * 
 * @author tangweiqun	2014/10/22
 *
 */
public interface CapitalFacade {
	
	public TotalCapitalInfo getTotalCapital(String mobile, boolean takeCapital4Prd);

	public List<Capital4Product> getAllCapital4Product(String mobile);
	
}
