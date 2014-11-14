package com.sunlights.account.service;

import com.sunlights.account.vo.*;
import com.sunlights.common.vo.PageVo;
import models.HoldCapital;

import java.util.List;

/**
 * @author tangweiqun 2014/10/22
 */
public interface CapitalService {
    /**
     * 资产查询
     *
     * @param token
     * @param takeCapital4Prd 是否显示产品收益情况
     * @return
     */
    public TotalCapitalInfo getTotalCapital(String token, boolean takeCapital4Prd);

    /**
     * 产品收益情况
     *
     * @param token
     * @return
     */
    public List<Capital4Product> getAllCapital4Product(String token, PageVo pageVo);

    /**
     * 昨日收益查询
     *
     * @return
     */
    public List<CapitalVo> findYesterdayProfitList(String token, PageVo pageVo);

    public HoldCapital createHoldCapital(AcctChangeFlowVo acctChangeFlowVo);


    public HoldCapital findHoldCapital(String customerId, String productCode);

}
