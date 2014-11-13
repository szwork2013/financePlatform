package com.sunlights.account.service.impl;

import com.sunlights.account.dal.AccountDao;
import com.sunlights.account.dal.CapitalDao;
import com.sunlights.account.dal.impl.AccountDaoImpl;
import com.sunlights.account.dal.impl.CapitalDaoImpl;
import com.sunlights.account.service.CapitalService;
import com.sunlights.account.vo.*;
import com.sunlights.common.AppConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.customer.service.impl.CustomerService;
import models.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tangweiqun 2014/10/22
 */

public class CapitalServiceImpl implements CapitalService {

    private CapitalDao capitalDao = new CapitalDaoImpl();
    private AccountDao accountDao = new AccountDaoImpl();
    private CustomerService customerService = new CustomerService();

    public TotalCapitalInfo getTotalCapital(String token, boolean takeCapital4Prd) {
        CustomerSession customerSession = customerService.getCustomerSession(token);
        String customerId = customerSession.getCustomerId();
        BigDecimal totalYesterdayProfit = BigDecimal.ZERO;
        BigDecimal totalProfit = BigDecimal.ZERO;

        BaseAccount baseAccount = accountDao.getBaseAccount(customerId);
        BigDecimal rewardProfit = baseAccount.getBalance() == null ? BigDecimal.ZERO : baseAccount.getBalance();
        BigDecimal totalCapital = rewardProfit;
        List<SubAccount> subAccountList = accountDao.findSubAccountList(customerId);
        for (SubAccount subAccount : subAccountList) {
            totalYesterdayProfit = totalYesterdayProfit.add(subAccount.getYesterdayProfit());
            totalProfit = totalProfit.add(subAccount.getProfit());
            totalCapital = totalCapital.add(subAccount.getBalance());
        }
        TotalCapitalInfo totalCapitalInfo = new TotalCapitalInfo();
        totalCapitalInfo.setRewardProfit(ArithUtil.bigToScale2(rewardProfit));
        totalCapitalInfo.setTotalCapital(ArithUtil.bigToScale2(totalCapital));
        totalCapitalInfo.setTotalProfit(ArithUtil.bigToScale2(totalProfit));
        totalCapitalInfo.setYesterdayProfit(ArithUtil.bigToScale2(totalYesterdayProfit));

        if (takeCapital4Prd) {
            PageVo pageVo = new PageVo();
//            pageVo.setPageSize(3);
            List<Capital4Product> capital4Products = findCapital4ProductList(customerId, pageVo);
            totalCapitalInfo.setCapital4Products(capital4Products);
//            totalCapitalInfo.setCount(pageVo.getCount() + "");
        }

        return totalCapitalInfo;
    }

    @Override
    public List<Capital4Product> getAllCapital4Product(String token, PageVo pageVo) {
        CustomerSession customerSession = customerService.getCustomerSession(token);
        return findCapital4ProductList(customerSession.getCustomerId(), pageVo);
    }

    @Override
    public HoldCapitalVo findCapitalProductDetail(String prdType, String prdCode) {
        return capitalDao.findCapitalProductDetail(prdType, prdCode);
    }

    private List<Capital4Product> findCapital4ProductList(String customerId, PageVo pageVo) {
        List<Capital4Product> list = capitalDao.findHoldCapitalsByCustId(customerId, pageVo);
        return list;
    }

    @Override
    public HoldCapital createHoldCapital(AcctChangeFlowVo acctChangeFlowVo) {
        String customerId = acctChangeFlowVo.getCustomerId();
        String productCode = acctChangeFlowVo.getPrdCode();

        BigDecimal tradeAmount = BigDecimal.ZERO;
        BigDecimal holdCapitalAmount = BigDecimal.ZERO;
        BigDecimal yesterdayProfit = BigDecimal.ZERO;
        BigDecimal totalProfit = BigDecimal.ZERO;

        HoldCapital preHoldCapital = capitalDao.findHoldCapital(customerId, productCode);
        if (preHoldCapital != null) {
            tradeAmount = preHoldCapital.getTradeAmount();
            holdCapitalAmount = preHoldCapital.getHoldCapital();
            yesterdayProfit = preHoldCapital.getYesterdayProfit();
            totalProfit = preHoldCapital.getTotalProfit();

            preHoldCapital.setStatus(AppConst.STATUS_INVALID);
            preHoldCapital.setDeleteTime(DBHelper.getCurrentTime());
            capitalDao.updateHoldCapital(preHoldCapital);
        }

        HoldCapital holdCapital = saveHoldCapital(acctChangeFlowVo, tradeAmount, holdCapitalAmount, yesterdayProfit, totalProfit);

        return holdCapital;
    }

    private HoldCapital saveHoldCapital(AcctChangeFlowVo acctChangeFlowVo, BigDecimal tradeAmount, BigDecimal holdCapitalAmount, BigDecimal yesterdayProfit, BigDecimal totalProfit) {
        BigDecimal amount = acctChangeFlowVo.getAmount();
        if ("2".equals(acctChangeFlowVo.getTradeType())) {//TODO
            if (holdCapitalAmount.compareTo(amount) < 0){
                throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.TRADE_AMOUNT_VALIDATE));
            }
            amount = acctChangeFlowVo.getAmount().negate();
        }
        String productType = acctChangeFlowVo.getPrdType();
        String customerId = acctChangeFlowVo.getCustomerId();
        Timestamp currentTime = DBHelper.getCurrentTime();

        HoldCapital holdCapital = new HoldCapital();
        holdCapital.setCustId(customerId);
        holdCapital.setProductCode(acctChangeFlowVo.getPrdCode());
        holdCapital.setProductName(acctChangeFlowVo.getPrdName());
        holdCapital.setProductType(acctChangeFlowVo.getPrdType());
        holdCapital.setTradeAmount(tradeAmount.add(amount));
        holdCapital.setHoldCapital(holdCapitalAmount.add(amount));
        holdCapital.setYesterdayProfit(yesterdayProfit);
        holdCapital.setTotalProfit(totalProfit);
        holdCapital.setStatus(AppConst.STATUS_VALID);
        holdCapital.setCreateTime(currentTime);
        holdCapital.setUpdateTime(currentTime);
        holdCapital.setSettleDate(currentTime);
        capitalDao.saveHoldCapital(holdCapital);

        SubAccount subAccount = accountDao.findSubAccount(customerId, productType);
        subAccount.setBalance(subAccount.getBalance().add(amount));
        subAccount.setUpdateTime(currentTime);
        accountDao.updateSubAccount(subAccount);
        return holdCapital;
    }

    public List<HoldCapitalVo> findTotalProfitList(CapitalFormVo capitalFormVo) {
        Customer customer = customerService.getCustomerByMobile(capitalFormVo.getMobile());
        List<HoldCapital> list = capitalDao.findHoldCapitalsByProductCode(customer.getCustomerId(), capitalFormVo);
        List<HoldCapitalVo> holdCapitalVos = new ArrayList<HoldCapitalVo>();
        HoldCapitalVo holdCapitalVo = null;
        for (HoldCapital capital : list) {
//            holdCapitalVo = transferTotalCapitalVo(capital);//TODO
            holdCapitalVos.add(holdCapitalVo);
        }
        return holdCapitalVos;
    }



}
