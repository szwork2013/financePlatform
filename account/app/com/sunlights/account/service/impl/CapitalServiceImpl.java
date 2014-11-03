package com.sunlights.account.service.impl;

import com.sunlights.account.dal.BaseAccountDao;
import com.sunlights.account.dal.CapitalDao;
import com.sunlights.account.dal.impl.BaseAccountDaoImpl;
import com.sunlights.account.dal.impl.CapitalDaoImpl;
import models.BaseAccount;
import models.HoldCapital;
import models.SubAccount;
import com.sunlights.account.service.CapitalService;
import com.sunlights.account.vo.Capital4Product;
import com.sunlights.account.vo.CapitalFormVo;
import com.sunlights.account.vo.HoldCapitalVo;
import com.sunlights.account.vo.TotalCapitalInfo;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import models.Customer;
import com.sunlights.customer.service.impl.CustomerService;
import play.db.jpa.Transactional;

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
    private BaseAccountDao baseAccountDao = new BaseAccountDaoImpl();
    private CustomerService customerService = new CustomerService();
	
	@Transactional
	public TotalCapitalInfo getTotalCapital(String mobile, boolean takeCapital4Prd) {
        Customer customer = customerService.getCustomerByMobile(mobile);
		BigDecimal totalYesterdayProfit = BigDecimal.ZERO;
        BigDecimal totalProfit = BigDecimal.ZERO;

        BaseAccount baseAccount = baseAccountDao.getBaseAccount(customer.getCustomerId());
        BigDecimal totalCapital = baseAccount.getBalance() == null ? BigDecimal.ZERO : baseAccount.getBalance();
        List<SubAccount> subAccountList = baseAccountDao.findSubAccountList(customer.getCustomerId());//TODO
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
            List<Capital4Product> capital4Products = findCapital4ProductList(customer.getCustomerId());
            totalCapitalInfo.setCapital4Products(capital4Products);
        }

		return totalCapitalInfo;
	}

	@Override
	public List<Capital4Product> getAllCapital4Product(String mobile) {
        Customer customer = customerService.getCustomerByMobile(mobile);
        return findCapital4ProductList(customer.getCustomerId());
	}

    private List<Capital4Product> findCapital4ProductList(String customerId){
        List<HoldCapital> list = capitalDao.findHoldCapitalsByCustId(customerId);
        List<Capital4Product> capital4Products = new ArrayList<Capital4Product>();
        Capital4Product capital4Product = null;
        for(HoldCapital capital : list) {
            capital4Product = transfer(capital);
            capital4Products.add(capital4Product);
        }
        return capital4Products;
    }

    public List<HoldCapitalVo> findTotalProfitList(CapitalFormVo capitalFormVo) {
        Customer customer = customerService.getCustomerByMobile(capitalFormVo.getMobile());
        List<HoldCapital> list = capitalDao.findHoldCapitalsByProductCode(customer.getCustomerId(), capitalFormVo);
        List<HoldCapitalVo> holdCapitalVos = new ArrayList<HoldCapitalVo>();
        HoldCapitalVo holdCapitalVo = null;
        for(HoldCapital capital : list) {
            holdCapitalVo = transferTotalCapitalVo(capital);
            holdCapitalVos.add(holdCapitalVo);
        }
        return holdCapitalVos;
    }
    public HoldCapitalVo findTotalProfitDetail(String id){
        HoldCapital holdCapital = capitalDao.findHoldCapitalsById(Long.valueOf(id));
        HoldCapitalVo holdCapitalVo = transferTotalCapitalVo(holdCapital);
        return capitalDao.findTotalProfitDetail(holdCapital.getProductType(), holdCapitalVo);
    }

    private Capital4Product transfer(HoldCapital capital) {
		Capital4Product capital4Product = new Capital4Product();
		capital4Product.setPrdCode(capital.getProductCode());
        capital4Product.setPrdName(capital.getProductName());
		capital4Product.setTotalProfit(ArithUtil.bigToScale2(capital.getTotalProfit()));
		capital4Product.setMarketValue(ArithUtil.bigToScale2(capital.getHoldCapital()));

		return capital4Product;
	}

    private HoldCapitalVo transferTotalCapitalVo(HoldCapital capital) {
        HoldCapitalVo holdCapitalVo = new HoldCapitalVo();
        holdCapitalVo.setId(capital.getId().toString());
        holdCapitalVo.setPrdCode(capital.getProductCode());
        holdCapitalVo.setPrdName(capital.getProductName());
        holdCapitalVo.setTotalProfit(ArithUtil.bigToScale2(capital.getTotalProfit()));
        holdCapitalVo.setYesterdayProfit(ArithUtil.bigToScale2(capital.getYesterdayProfit()));
        holdCapitalVo.setProfitDateTime(CommonUtil.dateToString(capital.getCreateTime(), CommonUtil.PATTEN_MIDDLE_DATE_FORMAT_DATETIME));
        holdCapitalVo.setPrincipal(ArithUtil.bigToScale2(capital.getHoldCapital().subtract(capital.getTotalProfit())));//本金

        return holdCapitalVo;
    }


}
