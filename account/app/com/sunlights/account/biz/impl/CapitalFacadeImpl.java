package com.sunlights.account.biz.impl;

import java.util.List;

import com.sunlights.account.biz.CapitalService;
import com.sunlights.account.facade.CapitalFacade;
import com.sunlights.account.facade.model.Capital4Product;
import com.sunlights.account.facade.model.TotalCapitalInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author tangweiqun 2014/10/22
 *
 */
@Component("capitalFacade")
public class CapitalFacadeImpl implements CapitalFacade {
	
	@Autowired
	private CapitalService modelCapitalService;

	@Override
	public TotalCapitalInfo getTotalCapital(String mobile, boolean takeCapital4Prd) {
		
		TotalCapitalInfo info = modelCapitalService.getTotalCapital(mobile, takeCapital4Prd);
		
		return info;
	}

	@Override
	public List<Capital4Product> getAllCapital4Product(String mobile) {
		
		return modelCapitalService.getAllCapital4Product(mobile);
	}

}
