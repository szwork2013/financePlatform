package com.sunlights.account.dal;

import models.*;

import java.util.List;

/**
 * 基本账户DAO接口
 * 
 * @author tangweiqun 2014/10/23
 *
 */
public interface AccountDao {
	
	public void saveBaseAccount(BaseAccount baseAccount);
    public void updateBaseAccount(BaseAccount baseAccount);
	public BaseAccount getBaseAccount(String custId);
    
    public List<SubAccount> findSubAccountList(String customerId);
    public SubAccount findSubAccount(String customerId, String prdType);
    public SubAccount saveSubAccount(SubAccount subAccount);
    public SubAccount updateSubAccount(SubAccount subAccount);

    public boolean findFundAgreementExist(String customerId, String fundCompanyCode);

    public void saveFundAgreement(String customerId, String fundCompanyCode);

    public boolean findPrdAccountConfigExist(String prdType, String subAccountNo);

    public void savePrdAccountConfig(String subAccountNo, String prdType);

    public void saveAcctChangFlow(AcctChangFlow acctChangFlow);



}
