package com.sunlights.account.dal;

import com.sunlights.account.vo.Capital4Product;
import com.sunlights.account.vo.CapitalVo;
import com.sunlights.common.vo.PageVo;
import models.HoldCapital;

import java.util.List;

/**
 * 资产Dao接口
 *
 * @author tangweiqun 2014/10/22
 */
public interface CapitalDao {

    /**
     * 持有产品信息
     *
     * @param custId
     * @param pageVo
     * @return
     */
    public List<Capital4Product> findHoldCapitalsByCustId(String custId, PageVo pageVo);

    /**
     * 昨日收益历史信息
     *
     * @return
     */
    public List<CapitalVo> findCapitalProfitListByCustId(String customerId, PageVo pageVo);


    public HoldCapital findHoldCapital(String customerId, String productCode);

    public HoldCapital saveHoldCapital(HoldCapital holdCapital);

    public HoldCapital updateHoldCapital(HoldCapital holdCapital);

}
