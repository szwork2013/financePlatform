package com.sunlights.account.dal;

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
	
	public List<HoldCapital> findHoldCapitalsByCustId(String custId);

    public HoldCapital findHoldCapitalsById(Long id);

    /**
     * 累计收益查询
     * @return
     */
    public List<HoldCapital> findHoldCapitalsByProductCode(String customerId, CapitalFormVo capitalFormVo);
    /**
     * 累计收益明细查询
     * @return
     */
    public HoldCapitalVo findTotalProfitDetail(String type, HoldCapitalVo holdCapitalVo);
	
}
