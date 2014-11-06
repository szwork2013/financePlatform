package com.sunlights.account.dal;

import models.BaseAccount;
import models.PrdAccountConfig;
import models.SubAccount;

import java.util.List;

/**
 * 基本账户DAO接口
 *
 * @author tangweiqun 2014/10/23
 */
public interface AccountDao {

  public void saveBaseAccount(BaseAccount baseAccount);

  public void updateBaseAccount(BaseAccount baseAccount);

  public BaseAccount getBaseAccount(String custId);

  public List<SubAccount> findSubAccountList(String customerId);

  public SubAccount saveSubAccount(SubAccount subAccount);

  public boolean findFundAgreementExist(String customerId, String fundCompanyCode);

  public void saveFundAgreement(String customerId, String fundCompanyCode);

  public PrdAccountConfig findPrdAccountConfig(String prdType);

  public void savePrdAccountConfig(String subAccountNo, String prdType);

}
