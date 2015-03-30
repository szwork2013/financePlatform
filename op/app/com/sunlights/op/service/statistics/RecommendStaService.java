package com.sunlights.op.service.statistics;

import com.sunlights.common.vo.PageVo;
import com.sunlights.op.vo.statistics.CustomerInOutSummaryVo;
import com.sunlights.op.vo.statistics.PurchaseItemsVo;
import com.sunlights.op.vo.statistics.RecommenderStaVo;
import com.sunlights.op.vo.statistics.Register4NotPurchaseVo;

import java.util.List;

/**
 * Created by Administrator on 2015/1/13.
 */
public interface RecommendStaService {

    public List<RecommenderStaVo> countRecommend(PageVo pageVo);

    public List<PurchaseItemsVo> countPurchases(PageVo pageVo);

    public List<Register4NotPurchaseVo> countRegisterNotPurchases(PageVo pageVo);
    public List<CustomerInOutSummaryVo> countCustomerInOutSummary(PageVo pageVo);

}
