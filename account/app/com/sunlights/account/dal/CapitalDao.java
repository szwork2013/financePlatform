package com.sunlights.account.dal;

import com.sunlights.account.vo.Capital4Product;
import com.sunlights.common.vo.PageVo;
import models.HoldCapital;
import com.sunlights.account.vo.CapitalFormVo;
import com.sunlights.account.vo.HoldCapitalVo;

import java.util.List;

/**
 * 资产Dao接口
 * @author tangweiqun 2014/10/22
 *
 */
public interface CapitalDao {
	
	public List<Capital4Product> findHoldCapitalsByCustId(String custId, PageVo pageVo);

    /**
     * 产品详情
     * @param prdType
     * @param prdCode
     * @return
     */
    public HoldCapitalVo findCapitalProductDetail(String prdType, String prdCode);

    public HoldCapital findHoldCapitalsById(Long id);

    /**
     * 累计收益查询
     * @return
     */
    public List<HoldCapital> findHoldCapitalsByProductCode(String customerId, CapitalFormVo capitalFormVo);

}
