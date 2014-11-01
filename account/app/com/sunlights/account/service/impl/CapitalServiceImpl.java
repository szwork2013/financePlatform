package com.sunlights.account.service.impl;

import com.sunlights.account.service.CapitalService;
import com.sunlights.account.dal.CapitalDao;
import com.sunlights.account.models.HoldCapital;
import com.sunlights.account.vo.Capital4Product;
import com.sunlights.account.vo.TotalCapitalInfo;
import com.sunlights.common.utils.ArithUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author tangweiqun 2014/10/22
 *
 */
public class CapitalServiceImpl implements CapitalService {
	

	private CapitalDao capitalDao;
	
	@Override
	public TotalCapitalInfo getTotalCapital(String custId, boolean takeCapital4Prd) {
		List<HoldCapital> list = capitalDao.getHoldCapitalsByCustId(custId);
		BigDecimal totalYesterdayProfit = BigDecimal.ZERO;
		BigDecimal totalProfit = BigDecimal.ZERO;
		BigDecimal totalCapital = BigDecimal.ZERO;
		
		List<Capital4Product> capital4Products = new ArrayList<Capital4Product>();
		Capital4Product capital4Product = null;
		for(HoldCapital capital : list) {
			totalYesterdayProfit = totalYesterdayProfit.add(capital.getYesterdayProfit());
			totalProfit = totalProfit.add(capital.getTotalProfit());
			totalCapital = totalCapital.add(capital.getHoldCapital());
			if(takeCapital4Prd) {
				capital4Product = transfer(capital);
				capital4Products.add(capital4Product);
			}
		}
		TotalCapitalInfo totalCapitalInfo = new TotalCapitalInfo();
		totalCapitalInfo.setRewardProfit("0.00");
		totalCapitalInfo.setTotalCapital(ArithUtil.bigToScale2(totalCapital));
		totalCapitalInfo.setTotalProfit(ArithUtil.bigToScale2(totalProfit));
		totalCapitalInfo.setYesterdayProfit(ArithUtil.bigToScale2(totalYesterdayProfit));
		totalCapitalInfo.setCapital4Products(capital4Products);
		
		return totalCapitalInfo;
	}

	@Override
	public List<Capital4Product> getAllCapital4Product(String custId) {
		List<HoldCapital> list = capitalDao.getHoldCapitalsByCustId(custId);
		List<Capital4Product> capital4Products = new ArrayList<Capital4Product>();
		Capital4Product capital4Product = null;
		for(HoldCapital capital : list) {
			capital4Product = transfer(capital);
			capital4Products.add(capital4Product);
		}
		return capital4Products;
	}

	private Capital4Product transfer(HoldCapital capital) {
		Capital4Product capital4Product = new Capital4Product();
		capital4Product.setPrdCode(capital.getPrdCode());
		//TODO 产品名称从哪获取？？？
		capital4Product.setPrdName(null);
		capital4Product.setTotalProfit(ArithUtil.bigToScale2(capital.getTotalProfit()));
		capital4Product.setMarketValue(ArithUtil.bigToScale2(capital.getTotalProfit().add(capital.getHoldCapital())));
		return capital4Product;
	}

}
