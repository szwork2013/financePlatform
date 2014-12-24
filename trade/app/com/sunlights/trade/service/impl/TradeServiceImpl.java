package com.sunlights.trade.service.impl;

import com.sunlights.account.service.AccountService;
import com.sunlights.account.service.CapitalService;
import com.sunlights.account.service.impl.AccountServiceImpl;
import com.sunlights.account.service.impl.CapitalServiceImpl;
import com.sunlights.account.vo.AcctChangeFlowVo;
import com.sunlights.account.vo.TotalCapitalInfo;
import com.sunlights.common.DictConst;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.utils.ArithUtil;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.service.OpenAccountPactService;
import com.sunlights.core.service.ProductService;
import com.sunlights.core.service.impl.OpenAccountPactServiceImpl;
import com.sunlights.core.service.impl.PaymentService;
import com.sunlights.core.service.impl.ProductServiceImpl;
import com.sunlights.customer.service.BankCardService;
import com.sunlights.customer.service.impl.BankCardServiceImpl;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.vo.BankCardVo;
import com.sunlights.customer.vo.CustomerVo;
import com.sunlights.trade.dal.TradeDao;
import com.sunlights.trade.dal.impl.TradeDaoImpl;
import com.sunlights.trade.service.TradeService;
import com.sunlights.trade.vo.CapitalProductTradeVo;
import com.sunlights.trade.vo.TradeFormVo;
import com.sunlights.trade.vo.TradeSearchFormVo;
import com.sunlights.trade.vo.TradeVo;
import models.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: TradeServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class TradeServiceImpl implements TradeService {
    private TradeDao tradeDao = new TradeDaoImpl();
    private CustomerService customerService = new CustomerService();
    private CapitalService capitalService = new CapitalServiceImpl();
    private OpenAccountPactService openAccountPactService = new OpenAccountPactServiceImpl();
    private AccountService accountService = new AccountServiceImpl();
    private ProductService productService = new ProductServiceImpl();
    private BankCardService bankCardService = new BankCardServiceImpl();
    private PaymentService paymentService = new PaymentService();

    @Override
    public List<TradeVo> getTradeListByToken(String token, TradeSearchFormVo tradeSearchFormVo, PageVo pageVo){
        CustomerSession customerSession = customerService.getCustomerSession(token);
        return getTradeListByCustomerId(customerSession.getCustomerId(), tradeSearchFormVo.getPrdCode() ,pageVo);
    }

    private List<TradeVo> getTradeListByCustomerId(String customerId, String prdCode, PageVo pageVo){
        List<TradeVo> list = tradeDao.getTradeListByCustomerId(customerId, prdCode ,pageVo);
        return list;
    }

    public CapitalProductTradeVo findCapitalProductDetailTrade(String token, TradeSearchFormVo tradeSearchFormVo){
        String prdCode = tradeSearchFormVo.getPrdCode();
        String prdType = tradeSearchFormVo.getPrdType();
        CommonUtil.getInstance().validateParams(prdType, prdCode);
        PageVo pageVo = new PageVo();
        pageVo.setPageSize(3);

        CustomerSession customerSession = customerService.getCustomerSession(token);
        String customerId = customerSession.getCustomerId();

        CapitalProductTradeVo capitalProductTradeVo = null;
        if (DictConst.FP_PRODUCT_TYPE_1.equals(prdType)) {//基金
            FundHistory fundHistory = productService.findFundHistoryByCode(prdCode);
            HoldCapital holdCapital = capitalService.findHoldCapital(customerId, prdCode);
            capitalProductTradeVo = transHoldCapitalVo(holdCapital, fundHistory);
        }
        List<TradeVo> list = getTradeListByCustomerId(customerId, prdCode, pageVo);
        capitalProductTradeVo.setList(list);
        capitalProductTradeVo.setTradeCount(pageVo.getCount());

        return capitalProductTradeVo;
    }

    private CapitalProductTradeVo transHoldCapitalVo(HoldCapital holdCapital, FundHistory fundHistory) {
        CapitalProductTradeVo capitalProductTradeVo = new CapitalProductTradeVo();
        capitalProductTradeVo.setPrdCode(holdCapital.getProductCode());
        capitalProductTradeVo.setPrdName(holdCapital.getProductName());
        capitalProductTradeVo.setMarketValue(ArithUtil.bigToScale2(holdCapital.getHoldCapital()));
        capitalProductTradeVo.setTotalProfit(ArithUtil.bigToScale2(holdCapital.getTotalProfit()));
        capitalProductTradeVo.setProfitLatestTime(CommonUtil.dateToString(holdCapital.getSettleDate(), CommonUtil.DATE_FORMAT_YYYY_MM_DD_HH_MM));
        capitalProductTradeVo.setHoldQuotient(holdCapital.getTradeAmount().divide(fundHistory.getMinApplyAmount()).toString());
        capitalProductTradeVo.setPrincipalValue(ArithUtil.bigToScale2(holdCapital.getHoldCapital().subtract(holdCapital.getTotalProfit())));
        capitalProductTradeVo.setMillionOfProfit(fundHistory.getMillionOfProfit().toString());
        capitalProductTradeVo.setOneWeekProfit(fundHistory.getOneWeekProfit().toString());
        if (!DictConst.FP_PRODUCT_TYPE_1_1.equals(fundHistory.getFundType())) {// 计算正在交易中的份额
            BigDecimal amount = tradeDao.getTradeRedeemAmount(holdCapital.getCustId(), fundHistory.getFundCode());
            capitalProductTradeVo.setAvailableQuotient(amount.divide(fundHistory.getMinApplyAmount()).toString());
        }
        return capitalProductTradeVo;
    }



    public TotalCapitalInfo tradeFundOrder(TradeFormVo tradeFormVo, String token){
        String bankCardNo = tradeFormVo.getBankCardNo();
        String prdType = tradeFormVo.getPrdType();
        String mobilePhoneNo = tradeFormVo.getMobilePhoneNo();
        String deviceNo = tradeFormVo.getDeviceNo();
        String prdCode = tradeFormVo.getPrdCode();
        String tradeAmount = tradeFormVo.getTradeAmount();
        String quantity = tradeFormVo.getQuantity();

        CommonUtil.getInstance().validateParams(bankCardNo, prdCode, prdType, mobilePhoneNo, deviceNo,tradeAmount,quantity);
        tradeValidate(mobilePhoneNo, deviceNo);

        CustomerSession customerSession = customerService.getCustomerSession(token);
        String customerId = customerSession.getCustomerId();

        PageVo pageVo = new PageVo();
        pageVo.put("EQS_bankCardNo", bankCardNo);
        List<BankCardVo> bankCardVoList = bankCardService.findBankCardsByToken(token, pageVo);
        if (bankCardVoList == null || bankCardVoList.isEmpty()) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.BANK_CARD_NOT_BINGING));
        }
        BankCardVo bankCardVo = bankCardVoList.get(0);
        Fund fund = productService.findFundByCode(prdCode);
        FundCompany fundCompany = productService.findFundCompanyById(fund.getFundCompanyId());

        //开户
        openAccountPactService.createFundOpenAccount(customerId, bankCardVo);
        //子帐号
        accountService.createSubAccount(customerId,fundCompany.getFundCompanyId(), prdType);
        //下单记录
        Trade trade = createTrade(tradeFormVo, bankCardVo, customerId, fund, DictConst.TRADE_TYPE_1);
        //调用支付接口
        boolean paymentFlag = paymentService.payment();
        if (!paymentFlag) {
            trade.setPayStatus(DictConst.PAYMENT_STATUS_4);//付款失败
            trade.setTradeStatus(DictConst.TRADE_STATUS_3);
            trade.setConfirmStatus(DictConst.VERIFY_STATUS_5);
            updateTrade(trade);
            return null;
        }

        //帐户变更记录
        HoldCapital holdCapital = saveAccountChangeInfo(prdType, prdCode, customerId, trade);
        //交易记录更新
        trade.setPayStatus(DictConst.PAYMENT_STATUS_3);
        trade.setTradeStatus(DictConst.TRADE_STATUS_2);
        trade.setConfirmStatus(DictConst.VERIFY_STATUS_4);
        trade.setHoldCapitalId(holdCapital.getId());
        updateTrade(trade);
        TotalCapitalInfo totalCapitalInfo = capitalService.getTotalCapital(token, true);

        return totalCapitalInfo;
    }

    private void tradeValidate(String mobilePhoneNo, String deviceNo) {
        CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo);
        if ("0".equals(customerVo.getCertify())) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.BANK_NAME_CERTIFY_FAIL));
        }
        if ("0".equals(customerVo.getBankCardCount())) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.BANK_CARD_NOT_BINGING));
        }
    }

    private HoldCapital saveAccountChangeInfo(String prdType, String prdCode, String customerId, Trade trade) {
        AcctChangeFlowVo acctChangFlowVo = new AcctChangeFlowVo();
        acctChangFlowVo.setCustomerId(customerId);
        acctChangFlowVo.setPrdCode(prdCode);
        acctChangFlowVo.setPrdName(trade.getProductName());
        acctChangFlowVo.setPrdType(prdType);
        acctChangFlowVo.setTradeType(trade.getType());
        acctChangFlowVo.setAmount(trade.getTradeAmount().abs());
        accountService.dealAccount(acctChangFlowVo);
        //持有资产，子帐号资产变更
        return capitalService.createHoldCapital(acctChangFlowVo);
    }

    private Trade updateTrade(Trade trade){
        trade.setUpdateTime(DBHelper.getCurrentTime());
        return tradeDao.updateTrade(trade);
    }

    private Trade createTrade(TradeFormVo tradeFormVo, BankCardVo bankCardVo, String customerId, Fund fund, String type){
        String tradeAmount = tradeFormVo.getTradeAmount();
        String quantity = tradeFormVo.getQuantity();
        String prdCode = tradeFormVo.getPrdCode();
        String bankName = bankCardVo.getBankName();
        String bankCardNo = bankCardVo.getBankCard();

        Timestamp currentTime = DBHelper.getCurrentTime();

        Trade trade = new Trade();
        trade.setTradeNo(generateTradeNo());
        trade.setType(type);
        if (DictConst.TRADE_TYPE_1.equals(type)) {//申购
            trade.setTradeAmount(new BigDecimal(tradeAmount));
        }else{//赎回
            trade.setTradeAmount(new BigDecimal(tradeAmount).negate());
        }
        trade.setTradeStatus(DictConst.TRADE_STATUS_1);//申购中
        trade.setConfirmStatus(DictConst.VERIFY_STATUS_1);//1-待确认
        trade.setTradeTime(currentTime);
        trade.setCreateTime(currentTime);
        trade.setCustId(customerId);
        trade.setBankCardNo(bankCardNo);
        trade.setBankName(bankName);
        trade.setPayStatus(DictConst.PAYMENT_STATUS_2);//未付款
        trade.setQuantity(Integer.valueOf(quantity));

        trade.setProductCode(prdCode);
        trade.setProductName(fund.getChiName());
        trade.setProductPrice(fund.getMinApplyAmount());
        trade.setFee(BigDecimal.valueOf(fund.getCharge()));
        return tradeDao.saveTrade(trade);
    }


    private String generateTradeNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssss");
        String time = sdf.format(DBHelper.getCurrentTime());
        return time + tradeDao.getTradeNoSeq();
    }


    public TotalCapitalInfo tradeFundRedeem(TradeFormVo tradeFormVo, String token){
        String prdType = tradeFormVo.getPrdType();
        String mobilePhoneNo = tradeFormVo.getMobilePhoneNo();
        String deviceNo = tradeFormVo.getDeviceNo();
        String prdCode = tradeFormVo.getPrdCode();
        String tradeAmount = tradeFormVo.getTradeAmount();
        String quantity = tradeFormVo.getQuantity();

        CommonUtil.getInstance().validateParams(prdCode, prdType, mobilePhoneNo, deviceNo,tradeAmount,quantity);
        tradeValidate(mobilePhoneNo, deviceNo);

        CustomerSession customerSession = customerService.getCustomerSession(token);
        String customerId = customerSession.getCustomerId();

        List<BankCardVo> bankCardVoList = bankCardService.findBankCardsByToken(token, new PageVo());//TODO 暂时只支持一张银行卡
        if (bankCardVoList == null || bankCardVoList.isEmpty()) {
            throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.BANK_CARD_NOT_BINGING));
        }
        BankCardVo bankCardVo = bankCardVoList.get(0);
        Fund fund = productService.findFundByCode(prdCode);

        //赎回记录
        Trade trade = createTrade(tradeFormVo, bankCardVo, customerId, fund, DictConst.TRADE_TYPE_2);
        //还款接口
        boolean paymentFlag = paymentService.payment();
        if (!paymentFlag) {
            trade.setPayStatus(DictConst.PAYMENT_STATUS_4);//付款失败
            trade.setTradeStatus(DictConst.TRADE_STATUS_3);
            trade.setConfirmStatus(DictConst.VERIFY_STATUS_5);
            updateTrade(trade);
            return null;
        }

        //帐户变更记录
        HoldCapital holdCapital = saveAccountChangeInfo(prdType, prdCode, customerId, trade);
        //交易记录更新
        trade.setPayStatus(DictConst.PAYMENT_STATUS_3);
        trade.setTradeStatus(DictConst.TRADE_STATUS_2);
        trade.setConfirmStatus(DictConst.VERIFY_STATUS_4);
        trade.setHoldCapitalId(holdCapital.getId());
        updateTrade(trade);
        TotalCapitalInfo totalCapitalInfo = capitalService.getTotalCapital(token, true);

        return totalCapitalInfo;
    }



}
