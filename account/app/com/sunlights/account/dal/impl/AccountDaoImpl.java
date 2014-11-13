package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.AccountDao;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.DBHelper;
import models.*;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;
public class AccountDaoImpl extends EntityBaseDao implements AccountDao {

	@Override
	public void saveBaseAccount(BaseAccount baseAccount) {
		super.create(baseAccount);
	}

    @Override
    public void updateBaseAccount(BaseAccount baseAccount) {
        update(baseAccount);
    }

    @Override
	public BaseAccount getBaseAccount(String custId) {
		return super.findUniqueBy(BaseAccount.class, "custId", custId);
	}

    public SubAccount saveSubAccount(SubAccount subAccount){
        return create(subAccount);
    }

    @Override
    public SubAccount updateSubAccount(SubAccount subAccount) {
        return update(subAccount);
    }


    @Override
    public List<SubAccount> findSubAccountList(String customerId){
        return super.findBy(SubAccount.class, "custId", customerId);
    }

    @Override
    public SubAccount findSubAccount(String customerId, String prdType) {
        Query query =  createNameQuery("findSubAccount", customerId, prdType);
        List<SubAccount> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public boolean findFundAgreementExist(String customerId, String fundCompanyCode){
        String sql = "select 1 from f_fund_Agreement fa where fa.customer_id = ?0 and company_code = ?1";
        Query query = em.createNativeQuery(sql);
        query.setParameter(0, customerId);
        query.setParameter(1, fundCompanyCode);
        List list = query.getResultList();
        if (list.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void saveFundAgreement(String customerId, String fundCompanyCode){
        FundAgreement fundAgreement = new FundAgreement();
        fundAgreement.setCompanyCode(fundCompanyCode);
        fundAgreement.setCustomerId(customerId);
        Timestamp currentTime = DBHelper.getCurrentTime();
        fundAgreement.setCreateTime(currentTime);
        fundAgreement.setUpdateTime(currentTime);
        create(fundAgreement);
    }

    public boolean findPrdAccountConfigExist(String prdType, String subAccountNo){
        String sql = "select 1 from prd_account_config fa where fa.prd_type_code = ?0 and sub_account = ?1";
        Query query = em.createNativeQuery(sql);
        query.setParameter(0, prdType);
        query.setParameter(1, subAccountNo);
        List list = query.getResultList();
        if (list.isEmpty()) {
            return false;
        }
        return true;
    }

    public void savePrdAccountConfig(String subAccountNo, String prdType){
        PrdAccountConfig prdAccountConfig = new PrdAccountConfig();
        prdAccountConfig.setPrdTypeCode(prdType);
        prdAccountConfig.setSubAccount(subAccountNo);
        Timestamp currentTime = DBHelper.getCurrentTime();
        prdAccountConfig.setCreateTime(currentTime);
        prdAccountConfig.setUpdateTime(currentTime);
        create(prdAccountConfig);
    }

    @Override
    public void saveAcctChangFlow(AcctChangFlow acctChangFlow) {
        create(acctChangFlow);
    }


}
