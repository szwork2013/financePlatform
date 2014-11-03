package com.sunlights.account.dal.impl;

import com.sunlights.account.dal.CapitalDao;
import com.sunlights.account.models.HoldCapital;
import com.sunlights.account.vo.CapitalFormVo;
import com.sunlights.account.vo.HoldCapitalVo;
import com.sunlights.common.dal.EntityBaseDao;
import com.sunlights.common.utils.ArithUtil;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * 资产Dao实现
 * @author tangweiqun 2014/10/22
 *
 */

public class CapitalDaoImpl extends EntityBaseDao implements CapitalDao {
	
	@Override
	public List<HoldCapital> findHoldCapitalsByCustId(String custId) {
        Query query = em.createQuery("select c FROM HoldCapital c where c.custId = ?0 and c.status = ?1 order by c.createTime desc", HoldCapital.class);
        query.setParameter(0, custId);
        query.setParameter(1, "N");
        List<HoldCapital> list = query.getResultList();
        return list;
	}
    public HoldCapital findHoldCapitalsById(Long id){
        return super.find(HoldCapital.class, id);
    }

    public List<HoldCapital> findHoldCapitalsByProductCode(String customerId, CapitalFormVo capitalFormVo){
        Query query = em.createQuery("select c FROM HoldCapital c where c.custId = ?0 and c.productCode = ?1 order by c.createTime desc", HoldCapital.class);
        query.setParameter(0, customerId);
        query.setParameter(1, capitalFormVo.getPrdCode());
        query.setFirstResult(capitalFormVo.getIndex());
        query.setMaxResults(capitalFormVo.getPageSize());
        List<HoldCapital> list = query.getResultList();
        return list;
    }

    public HoldCapitalVo findTotalProfitDetail(String type, HoldCapitalVo holdCapitalVo){
        if ("0".equals(type)) {
            Query fundQuery = em.createQuery("select f.oneWeekProfit from Fund f where f.fundCode = ?0 order by f.createdDatetime desc", BigDecimal.class);
            fundQuery.setParameter(0, holdCapitalVo.getPrdCode());
            BigDecimal oneWeekProfit = (BigDecimal)fundQuery.getSingleResult();
            if (oneWeekProfit != null) {
                String rate = ArithUtil.bigToScale2(oneWeekProfit.multiply(new BigDecimal(100))) + "%";
                holdCapitalVo.setProfitRate(rate);
            }
        }

        return holdCapitalVo;
    }

}
