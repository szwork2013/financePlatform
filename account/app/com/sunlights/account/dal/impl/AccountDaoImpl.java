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
        String sql = "select sa from SubAccount sa,PrdAccountConfig pc where sa.subAccount = pc.subAccount and sa.custId = ?0 and pc.prdTypeCode = ?1";
        Query query = em.createQuery(sql);
        query.setParameter(0, customerId);
        query.setParameter(1, prdType);
        List<SubAccount> list = query.getResultList();
        if (list == null || list.isEmpty()) {
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
        if (list == null || list.isEmpty()) {
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

    public PrdAccountConfig findPrdAccountConfig(String prdType){
        List<PrdAccountConfig> list = findBy(PrdAccountConfig.class, "prdTypeCode", prdType);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
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
