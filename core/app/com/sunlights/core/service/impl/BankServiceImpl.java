package com.sunlights.core.service.impl;

import com.sunlights.common.MsgCode;
import com.sunlights.common.Severity;
import com.sunlights.common.service.PageService;
import com.sunlights.common.utils.MessageUtil;
import com.sunlights.common.vo.Message;
import com.sunlights.common.vo.PageVo;
import com.sunlights.core.dal.BankDao;
import com.sunlights.core.dal.impl.BankDaoImpl;
import com.sunlights.core.integration.BankClient;
import com.sunlights.core.integration.BankClientImpl;
import models.Bank;
import com.sunlights.core.service.BankService;
import com.sunlights.core.vo.BankCardVo;
import com.sunlights.core.vo.BankVo;
import com.sunlights.customer.service.impl.CustomerService;
import models.Customer;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <p>Project: fsp</p>
 * <p>Title: BankServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:zhencai.yuan@sunlights.cc">yuanzhencai</a>
 */
public class BankServiceImpl implements BankService {
  private BankDao bankDao = new BankDaoImpl();

  private PageService pageService = new PageService();

  private BankClient bankClient = new BankClientImpl();

  private CustomerService customerService = new CustomerService();

  @Override
  public List<BankVo> findBanksBy(PageVo pageVo) {
    StringBuilder xsql = new StringBuilder();
    xsql.append(" select new com.sunlights.core.vo.BankVo(b)");
    xsql.append(" from Bank b");
    xsql.append(" where 1=1");
    xsql.append(" /~ and b.bankName like {bankName} ~/ ");
    xsql.append(" /~ and b.bankCode like {bankCode} ~/ ");
    xsql.append(" /~ and b.status = {status} ~/ ");
    List<BankVo> bankVos = pageService.findXsqlBy(xsql.toString(), pageVo);
    pageVo.getList().addAll(bankVos);
    return bankVos;
  }

  @Override
  public BankVo findBankByBankCardNo(String bankCardNo) {
    BankVo bankVo = bankClient.findBankByBankCardNo(bankCardNo);
    if (StringUtils.isNotEmpty(bankVo.bankCode)) {
      Bank bank = bankDao.findBankByBankCode(bankVo.bankCode);
      return new BankVo(bank);
    }
    return bankVo;
  }

  @Override
  public Bank findBankByBankCode(String bankCode) {
    return bankDao.findBankByBankCode(bankCode);
  }

  @Override
  public boolean validateBankCard(String token, BankCardVo bankCardVo) {
    Customer customer = customerService.getCustomerByToken(token);
    if (customer == null || StringUtils.isEmpty(customer.getIdentityNumber())) {
      MessageUtil.getInstance().setMessage(new Message(Severity.ERROR, MsgCode.BANK_NAME_CERTIFY_FAIL, "验证失败", "请先实名认证。"));
      return false;
    }
    return bankClient.validateBankCard(customer.getIdentityNumber(), bankCardVo.getNo());
  }
}
