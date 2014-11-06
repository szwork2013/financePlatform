package com.sunlights.trade.service.impl;

import com.sunlights.account.service.AccountService;
import com.sunlights.account.service.CapitalService;
import com.sunlights.account.service.impl.AccountServiceImpl;
import com.sunlights.account.service.impl.CapitalServiceImpl;
import com.sunlights.account.vo.HoldCapitalVo;
import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.exceptions.BusinessRuntimeException;
import com.sunlights.common.utils.CommonUtil;
import com.sunlights.common.utils.DBHelper;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.service.BankCardService;
import com.sunlights.core.service.OpenAccountPactService;
import com.sunlights.core.service.ProductService;
import com.sunlights.core.service.impl.BankCardServiceImpl;
import com.sunlights.core.service.impl.OpenAccountPactServiceImpl;
import com.sunlights.core.service.impl.ProductServiceImpl;
import com.sunlights.core.vo.BankCardVo;
import com.sunlights.customer.service.impl.CustomerService;
import com.sunlights.customer.vo.CustomerVo;
import com.sunlights.trade.dal.TradeDao;
import com.sunlights.trade.dal.impl.TradeDaoImpl;
import com.sunlights.trade.service.TradeService;
import com.sunlights.trade.vo.CapitalProductTradeVo;
import com.sunlights.trade.vo.TradeFormVo;
import com.sunlights.trade.vo.TradeSearchFormVo;
import com.sunlights.trade.vo.TradeVo;
import models.CustomerSession;
import models.Fund;
import models.Trade;

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

  @Override
  public List<TradeVo> getTradeListByToken(String token, TradeSearchFormVo tradeSearchFormVo, PageVo pageVo) throws BusinessRuntimeException {

    CustomerSession customerSession = customerService.getCustomerSession(token);
    if (customerSession == null) {
      throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.LOGIN_TIMEOUT));
    }
    List<TradeVo> list = tradeDao.getTradeListByCustomerId(customerSession.getCustomerId(), tradeSearchFormVo, pageVo);
    return list;
  }

  public CapitalProductTradeVo findCapitalProductDetailTrade(String token, TradeSearchFormVo tradeSearchFormVo) {
    CommonUtil.getInstance().validateParams(tradeSearchFormVo.getPrdType(), tradeSearchFormVo.getPrdCode());
    PageVo pageVo = new PageVo();
    pageVo.setPageSize(3);
    List<TradeVo> list = getTradeListByToken(token, tradeSearchFormVo, pageVo);
    HoldCapitalVo holdCapitalVo = capitalService.findCapitalProductDetail(tradeSearchFormVo.getPrdType(), tradeSearchFormVo.getPrdCode());

    CapitalProductTradeVo capitalProductTradeVo = new CapitalProductTradeVo();
    capitalProductTradeVo.setList(list);
    capitalProductTradeVo.setHoldCapitalVo(holdCapitalVo);
    capitalProductTradeVo.setTradeCount(pageVo.getCount());

    return capitalProductTradeVo;
  }

  @Override
  public Trade createTrade() {
    return null;
  }


  public void tradeFundOrders(TradeFormVo tradeFormVo) {
    String token = null;
    String bankCardNo = null;
    String fundCompanyCode = null;
    String prdType = null;
    String mobilePhoneNo = null;
    String deviceNo = null;

    CustomerSession customerSession = customerService.getCustomerSession(token);
    if (customerSession == null) {
      throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.LOGIN_TIMEOUT));
    }
    CustomerVo customerVo = customerService.getCustomerVoByPhoneNo(mobilePhoneNo, deviceNo);
    if ("0".equals(customerVo.getCertify())) {
      throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.BANK_NAME_CERTIFY_FAIL));
    }
    if ("0".equals(customerVo.getBankCardCount())) {
      throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.BANK_CARD_NOT_BINGING));
    }
    String customerId = customerSession.getCustomerId();

    PageVo pageVo = new PageVo();
    pageVo.put("EQS_bankCardNo", bankCardNo);
    List<BankCardVo> bankCardVoList = bankCardService.findBankCardsByToken(token, pageVo);
    if (bankCardVoList == null || bankCardVoList.isEmpty()) {
      throw new BusinessRuntimeException(new Message(Severity.ERROR, MsgCode.BANK_CARD_NOT_BINGING));
    }
    BankCardVo bankCardVo = bankCardVoList.get(0);

    //开户
    openAccountPactService.createFundOpenAccount(customerId, bankCardVo);
    //子帐号
    accountService.createSubAccount(customerId, fundCompanyCode, prdType);
    //下单记录
    tradePurchase(tradeFormVo, bankCardVo, customerId);
    //调用支付接口


  }

  private Trade tradePurchase(TradeFormVo tradeFormVo, BankCardVo bankCardVo, String customerId) {
    String tradeAmount = tradeFormVo.getTradeAmount();
    String quantity = tradeFormVo.getQuantity();
    String prdCode = tradeFormVo.getPrdCode();
    String bankName = bankCardVo.getBankName();
    String bankCardNo = bankCardVo.getBankCardNo();

    Timestamp currentTime = DBHelper.getCurrentTime();

    Trade trade = new Trade();
    trade.setTradeNo(formatTradeNo(tradeDao.getTradeNoSeq(), currentTime));
    trade.setType("1");//1:申购
    trade.setTradeStatus("1");//申购中
    trade.setConfirmStatus("1");//1-待确认
    trade.setTradeTime(currentTime);
    trade.setCreateTime(currentTime);
    trade.setCustId(customerId);
    trade.setBankCardNo(bankCardNo);
    trade.setBankName(bankName);
    trade.setPayStatus("1");//未付款
    trade.setTradeAmount(new BigDecimal(tradeAmount));
    trade.setQuantity(Integer.valueOf(quantity));

    Fund fund = productService.findFundByCode(prdCode);
    trade.setProductCode(prdCode);
    trade.setProductName(fund.getChiName());
    trade.setProductPrice(fund.getMinApplyAmount());
    if (fund.getCharge() != null) {
      trade.setFee(fund.getCharge());
    }
    return tradeDao.saveTrade(trade);
  }

  private String formatTradeNo(String tradeNoSeq, Timestamp currentTime) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssss");
    String time = sdf.format(currentTime);
    return time + tradeNoSeq;
  }

  public void tradeRedeem() {

  }

}
