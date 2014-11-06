package com.sunlights.account.service;

import models.BaseAccount;
import com.sunlights.customer.vo.CustomerFormVo;

import java.math.BigDecimal;

/**
 * @author tangweiqun 2014/10/23
 */
public interface AccountService {
  /**
   * 客户注册基本账户
   *
   * @param custId        客户号
   * @param tradePassword 交易密码
   * @return true 表示注册成功    false表示注册失败
   */
  public boolean createBaseAccount(String custId, String tradePassword);


  /**
   * 注册子账户
   *
   * @param custId          客户号
   * @param fundCompanyCode 基金公司编码
   * @param prdType         产品类型
   */
  public void createSubAccount(String custId, String fundCompanyCode, String prdType);

  /**
   * 记账
   *
   * @param custId    客户号
   * @param prdCode   产品编码
   * @param tradeNo   交易流水号
   * @param tradeType 交易类型
   * @param amount    变动金额
   */
  public void dealAccount(String custId, String prdCode, String tradeNo, String tradeType, BigDecimal amount);

  /**
   * 修改交易密码验证
   *
   * @param vo
   * @return
   */
  public void resetTradePwdCertify(CustomerFormVo vo);

  /**
   * 修改交易密码
   *
   * @return
   */
  public BaseAccount resetTradePwd(CustomerFormVo customerFormVo, String token);
}
