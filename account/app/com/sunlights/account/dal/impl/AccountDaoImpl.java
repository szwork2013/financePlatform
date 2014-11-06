package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.AccountDao;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.DBHelper;
import models.BaseAccount;
import models.FundAgreement;
import models.PrdAccountConfig;
import models.SubAccount;

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
    public List<SubAccount> findSubAccountList(String customerId){
        return super.findBy(SubAccount.class, "custId", customerId);
    }

    @Override
    public boolean findFundAgreementExist(String customerId, String fundCompanyCode){
        String sql = "select 1 from f_fund_Agreement fa where fa.customer_id = ?0 and company_code = ?1";
        Query query = em.createNativeQuery(sql);
        query.setParameter(0, customerId);
        query.setParameter(1, fundCompanyCode);
        int count = query.getMaxResults();//TODO 可否？
        if (count > 0) {
            return true;
        }
        return false;
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
}
