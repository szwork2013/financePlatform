package com.sunlights.account.service.impl;

import com.sunlights.account.dal.AccountDao;
import com.sunlights.account.dal.CapitalDao;
import com.sunlights.account.dal.impl.AccountDaoImpl;
import com.sunlights.account.dal.impl.CapitalDaoImpl;
import com.sunlights.account.service.CapitalService;
import com.sunlights.account.vo.Capital4Product;
import com.sunlights.account.vo.CapitalFormVo;
import com.sunlights.account.vo.HoldCapitalVo;
import com.sunlights.account.vo.TotalCapitalInfo;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.service.impl.CustomerService;
import models.BaseAccount;
import models.Customer;
import models.HoldCapital;
import models.SubAccount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author tangweiqun 2014/10/22
 *
 */

public class CapitalServiceImpl implements CapitalService {
	
	private CapitalDao capitalDao = new CapitalDaoImpl();
    private AccountDao accountDao = new AccountDaoImpl();
    private CustomerService customerService = new CustomerService();
	
	public TotalCapitalInfo getTotalCapital(String mobile, boolean takeCapital4Prd) {
        Customer customer = customerService.getCustomerByMobile(mobile);
		BigDecimal totalYesterdayProfit = BigDecimal.ZERO;
        BigDecimal totalProfit = BigDecimal.ZERO;

        BaseAccount baseAccount = accountDao.getBaseAccount(customer.getCustomerId());
        BigDecimal totalCapital = baseAccount.getBalance() == null ? BigDecimal.ZERO : baseAccount.getBalance();
        List<SubAccount> subAccountList = accountDao.findSubAccountList(customer.getCustomerId());
        for (SubAccount subAccount : subAccountList) {
            totalYesterdayProfit = totalYesterdayProfit.add(subAccount.getYesterdayProfit());
            totalProfit = totalProfit.add(subAccount.getProfit());
            totalCapital = totalCapital.add(subAccount.getBalance());
        }
        TotalCapitalInfo totalCapitalInfo = new TotalCapitalInfo();
        totalCapitalInfo.setRewardProfit("0.00");
        totalCapitalInfo.setTotalCapital(ArithUtil.bigToScale2(totalCapital));
        totalCapitalInfo.setTotalProfit(ArithUtil.bigToScale2(totalProfit));
        totalCapitalInfo.setYesterdayProfit(ArithUtil.bigToScale2(totalYesterdayProfit));

        if (takeCapital4Prd) {
            PageVo pageVo = new PageVo();
//            pageVo.setPageSize(3);
            List<Capital4Product> capital4Products = findCapital4ProductList(customer.getCustomerId(), pageVo);
            totalCapitalInfo.setCapital4Products(capital4Products);
//            totalCapitalInfo.setCount(pageVo.getCount() + "");
        }

		return totalCapitalInfo;
	}

	@Override
	public List<Capital4Product> getAllCapital4Product(String mobile, PageVo pageVo) {
        Customer customer = customerService.getCustomerByMobile(mobile);
        return findCapital4ProductList(customer.getCustomerId(), pageVo);
	}

    @Override
    public HoldCapitalVo findCapitalProductDetail(String prdType, String prdCode) {
        return capitalDao.findCapitalProductDetail(prdType, prdCode);
    }

    private List<Capital4Product> findCapital4ProductList(String customerId, PageVo pageVo){
        List<Capital4Product> list = capitalDao.findHoldCapitalsByCustId(customerId, pageVo);
        return list;
    }

    public List<HoldCapitalVo> findTotalProfitList(CapitalFormVo capitalFormVo) {
        Customer customer = customerService.getCustomerByMobile(capitalFormVo.getMobile());
        List<HoldCapital> list = capitalDao.findHoldCapitalsByProductCode(customer.getCustomerId(), capitalFormVo);
        List<HoldCapitalVo> holdCapitalVos = new ArrayList<HoldCapitalVo>();
        HoldCapitalVo holdCapitalVo = null;
        for(HoldCapital capital : list) {
//            holdCapitalVo = transferTotalCapitalVo(capital);//TODO
            holdCapitalVos.add(holdCapitalVo);
        }
        return holdCapitalVos;
    }




}
